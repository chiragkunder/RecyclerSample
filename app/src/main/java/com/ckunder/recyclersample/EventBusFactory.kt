package com.ckunder.recyclersample

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class EventBusFactory private constructor(val owner: LifecycleOwner) {

    companion object {

        private val buses = mutableMapOf<LifecycleOwner, EventBusFactory>()

        /**
         * Return the [EventBusFactory] associated to the [LifecycleOwner]. It there is no bus it will create one.
         * If the [LifecycleOwner] used is a fragment it use [Fragment#getViewLifecycleOwner()]
         */
        @JvmStatic
        fun get(lifecycleOwner: LifecycleOwner): EventBusFactory {
            return with(lifecycleOwner) {
                var bus = buses[lifecycleOwner]
                if (bus == null) {
                    bus = EventBusFactory(lifecycleOwner)
                    buses[lifecycleOwner] = bus
                    // LifecycleOwner
                    lifecycleOwner.lifecycle.addObserver(bus.observer)
                }
                bus
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val map = HashMap<Class<*>, Subject<*>>()

    internal val observer = object : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            map.forEach { entry -> entry.value.onComplete() }
            buses.remove(owner)
        }
    }

    private fun <T> create(clazz: Class<T>): Subject<T> {
        val subject = PublishSubject.create<T>().toSerialized()
        map[clazz] = subject
        return subject
    }

    /**
     * emit will create (if needed) or use the existing Rx Subject to send events.
     *
     * @param clazz is the Event Class
     * @param event is the instance of the Event to be sent
     */
    fun <T : Event> emit(clazz: Class<T>, event: T) {
        val subject = if (map[clazz] != null) map[clazz] else create(clazz)
        (subject as Subject<T>).onNext(event)
    }

    /**x
     * getSafeManagedObservable returns an Rx Observable which is
     *  *Safe* against reentrant events as it is serialized and
     *  *Managed* since it disposes itself based on the lifecycle
     *
     *  @param clazz is the class of the event type used by this observable
     */
    fun <T : Event> getSafeManagedObservable(clazz: Class<T>): Observable<T> {
        return if (map[clazz] != null) map[clazz] as Observable<T> else create(clazz)
    }

    /**
     * getDestroyObservable observes to Lifecycle owner and fires when
     * lifecycle.currentState == Lifecycle.State.DESTROYED
     */
    fun getDestroyObservable(): Observable<Unit> {
        return owner.createDestroyObservable()
    }
}

/**
 * Extension on [LifecycleOwner] used to emit an event.
 */
inline fun <reified T : Event> LifecycleOwner.emit(event: T) =
    with(EventBusFactory.get(this)) {
        getSafeManagedObservable(T::class.java)
        emit(T::class.java, event)
    }

/**
 * Extension on [LifecycleOwner] used used to get the state observable.
 */
inline fun <reified T : Event> LifecycleOwner.getSafeManagedObservable(): Observable<T> =
    EventBusFactory.get(this).getSafeManagedObservable(T::class.java)

/**
 * This method returns a destroy observable that can be passed to [com.netflix.arch.UIPresenter]s and
 * [com.netflix.arch.UIView]s as needed. This is deliberately scoped to the attached
 * [LifecycleOwner]'s [Lifecycle.Event.ON_DESTROY] because a viewholder can be reused across
 * adapter destroys.
 */
inline fun LifecycleOwner?.createDestroyObservable(): Observable<Unit> {
    return Observable.create { emitter ->
        if (this == null || this.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            emitter.onNext(Unit)
            emitter.onComplete()
            return@create
        }
        this.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun emitDestroy() {
                if (emitter.isDisposed) {
                    emitter.onNext(Unit)
                    emitter.onComplete()
                }
            }
        })
    }
}