package com.ckunder.recyclersample.new

package com.n26.feeds_presentation.adl.recycler_view

import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.n26.common.extensions.assertIsUiThread
import com.n26.feeds_presentation.adl.ViewEntity
import com.n26.feeds_presentation.adl.recycler_view.actions.ItemViewActionsProvider
import com.n26.feeds_presentation.adl.recycler_view.actions.ViewAction
import com.n26.feeds_presentation.adl.recycler_view.grouping.GroupViewEntity
import timber.log.Timber

@Suppress("TooManyFunctions")
class RecyclerViewAdapter internal constructor(
    private val viewControllersMap: ViewControllersMap
) : ListAdapter<ViewEntity, N26ViewHolder>(DefaultItemComparator(viewControllersMap)) {

    private val viewEntityIdToGroupMap = mutableMapOf<Long, GroupViewEntity>()
    private val viewTypeToControllersMap = mutableMapOf<Int, ViewController<ViewEntity>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): N26ViewHolder {
        Timber.d("On create view for $viewType.")
        val controller = getControllerByViewType(viewType)
        val viewHolder = controller.createViewHolder(parent)
        viewHolder.setupClickListeners(controller)
        return viewHolder
    }

    @Suppress("UNCHECKED_CAST")
    private fun N26ViewHolder.setupClickListeners(controller: ViewController<ViewEntity>) {
        if (controller is ItemViewActionsProvider<*>) {
            val viewActions = controller.provideViewActions(itemView) as List<ViewAction<ViewEntity>>
            viewActions
                .forEach { viewAction ->
                    viewAction.view.setOnClickListener {
                        if (adapterPosition != androidx.recyclerview.widget.RecyclerView.NO_POSITION) {
                            viewAction.action.invoke(getItem(adapterPosition))
                        }
                    }
                }
        }
    }

    override fun onBindViewHolder(holder: N26ViewHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun onBindViewHolder(holder: N26ViewHolder, position: Int, payloads: MutableList<Any>) {
        val controller = getControllerByPosition(position)
        controller.bindView(holder, getItem(position), payloads)
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
    override fun submitList(viewEntities: List<ViewEntity>?) {
        viewEntities?.run {
            assertIsUiThread()
            updateModels(this)
        }
    }

    @MainThread
    fun getViewEntity(position: Int): ViewEntity? {
        assertIsUiThread()
        return if (position != NO_POSITION) {
            super.getItem(position)
        } else null
    }

    @MainThread
    fun getGroupViewEntityForPosition(position: Int): GroupViewEntity? {
        assertIsUiThread()
        return getViewEntity(position)?.let {
            viewEntityIdToGroupMap[it.id]
        }
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    /**
     *
     * [getItemViewType] is called immediately before [onCreateViewHolder], so we use it to build a map
     * of viewType and [ViewController], so that when [onCreateViewHolder] is
     * called we can get the right [ViewController] for that viewType.
     * The controller is added to the map only in case it doesn't already exist.
     *
     */
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        val controller = getControllerForViewEntity(item)
        val viewType = controller.getItemType()

        if (!viewTypeToControllersMap.containsKey(viewType)) {
            viewTypeToControllersMap[viewType] = controller
        }

        return viewType
    }

    private fun getControllerForViewEntity(item: ViewEntity): ViewController<ViewEntity> =
        viewControllersMap[item::class]
            ?: throw IllegalStateException("View holder controller not found for item: ${item::class}.")

    private fun getControllerByViewType(viewType: Int): ViewController<ViewEntity> =
        viewTypeToControllersMap[viewType]
            ?: throw IllegalStateException("View holder controller not found for viewType: $viewType")

    private fun getControllerByPosition(position: Int): ViewController<ViewEntity> {
        val item = getItem(position)
        return viewControllersMap[item::class]
            ?: throw IllegalStateException("View holder controller not found for item: ${item::class}.")
    }

    private fun updateModels(viewEntities: List<ViewEntity>) {
        val partitionedPair = viewEntities.flatMapPartitioned()
        viewEntityIdToGroupMap.clear()
        viewEntityIdToGroupMap.putAll(partitionedPair.first)
        super.submitList(partitionedPair.second)
    }

    /**
     * Spilt the list of [ViewEntity]'s into:
     * 1. A map containing id of a [ViewEntity] and the [GroupViewEntity] it belongs to as the value.
     * 2. The other a flattened list of all view entities.
     */
    private fun List<ViewEntity>.flatMapPartitioned(): Pair<Map<Long, GroupViewEntity>, List<ViewEntity>> {
        val viewEntities = ArrayList<ViewEntity>()
        val viewEntityIdToGroupMap = mutableMapOf<Long, GroupViewEntity>()
        for (element in this) {
            if (element is GroupViewEntity) {
                viewEntities.addAll(element.items)
                element.items.forEach {
                    viewEntityIdToGroupMap[it.id] = element
                }
            } else {
                viewEntities.add(element)
            }
        }
        return Pair(viewEntityIdToGroupMap, viewEntities)
    }
}