package com.ckunder.recycler_2.recycler_view

import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.recyclerview.widget.ListAdapter
import com.ckunder.recycler_2.recycler_view.adl.GroupViewEntity
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity
import timber.log.Timber

@Suppress("TooManyFunctions")
class RecyclerViewAdapter constructor(
    private val viewControllersMap: ViewControllersMap
) : ListAdapter<ViewEntity, N26ViewHolder>(DefaultItemComparator(viewControllersMap)) {

    private val items = arrayListOf<ViewEntity>()
    private val groups = arrayListOf<GroupViewEntity>()
    private val viewTypeControllersMap = mutableMapOf<Int, ViewController<ViewEntity>>()

    /**
     * The adapter by default sets setHasStableIds to true since every [ViewEntity] should provide an ID.
     * If the [ViewEntity]'s, do not have unique IDs for some reason, this should be set to false.
     */
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): N26ViewHolder {
        Timber.d("On create view for $viewType.")
        val controller = getControllerByViewType(viewType)
        return controller.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: N26ViewHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun onBindViewHolder(holder: N26ViewHolder, position: Int, payloads: MutableList<Any>) {
        val controller = getControllerByPosition(position)
        controller.bindView(holder, items[position], payloads)
        Timber.d("On bind view for position $position and viewType ${controller.getItemType()}")
    }

    override fun onViewRecycled(holder: N26ViewHolder) {
        Timber.d("On View recycled for viewType ${holder.itemViewType}.")
        val controller = getControllerByViewType(holder.itemViewType)
        controller.unBindView(holder.itemView)
    }

    override fun onFailedToRecycleView(holder: N26ViewHolder): Boolean {
        Timber.d("On failed to recycle view for viewType ${holder.itemViewType}.")
        val controller = getControllerByViewType(holder.itemViewType)
        return controller.onFailedToRecycleView(holder.itemView)
    }

    override fun onViewDetachedFromWindow(holder: N26ViewHolder) {
        Timber.d("On view detached to window ${holder.itemViewType}.")
        val controller = getControllerByViewType(holder.itemViewType)
        controller.onViewDetachedFromWindow(holder.itemView)
    }

    override fun onViewAttachedToWindow(holder: N26ViewHolder) {
        Timber.d("On view attached to window ${holder.itemViewType}.")
        val controller = getControllerByViewType(holder.itemViewType)
        controller.onViewAttachedToWindow(holder.itemView)
    }

    @MainThread
    fun updateList(list: List<GroupViewEntity>) {
        groups.clear()
        groups.addAll(list)

        val newItems: List<ViewEntity> = groups.flatMap { it.items }
        items.clear()
        items.addAll(newItems)

        super.submitList(items)
    }

    @MainThread
    fun getModelItems(): List<ViewEntity> {
        return items
    }

    override fun getItemId(position: Int): Long = items[position].id

    override fun getItemCount(): Int = items.size

    fun getGroupViewEntityForPosition(position: Int): GroupViewEntity {
        val item = items[position]
        return groups
            .indexOfFirst { it.items.contains(item) }
            .run { groups[this] }
    }

    /**
     *
     * [getItemViewType] is called immediately before [onCreateViewHolder], so we use it to build a map
     * of viewType and [ViewController], so that when [onCreateViewHolder] is
     * called we can get the right [ViewController] for that viewType.
     * The controller is added to the map only in case it doesn't already exist.
     *
     */
    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        val controller = getControllerForViewEntity(item)
        val viewType = controller.getItemType()

        if (!viewTypeControllersMap.containsKey(viewType)) {
            viewTypeControllersMap[viewType] = controller
        }

        return viewType
    }

    private fun getControllerForViewEntity(item: ViewEntity): ViewController<ViewEntity> =
        viewControllersMap[item::class]
            ?: throw IllegalStateException("View holder controller not found for item: ${item::class}.")

    fun getControllerByViewType(viewType: Int): ViewController<ViewEntity> =
        viewTypeControllersMap[viewType]
            ?: throw IllegalStateException("View holder controller not found for viewType: $viewType")

    fun getControllerByPosition(position: Int): ViewController<ViewEntity> {
        val item = items[position]
        return viewControllersMap[item::class]
            ?: throw IllegalStateException("View holder controller not found for item: ${item::class}.")
    }
}