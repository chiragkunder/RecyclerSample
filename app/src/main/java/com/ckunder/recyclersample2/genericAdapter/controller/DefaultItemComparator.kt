package com.ckunder.recyclersample2.genericAdapter.controller

import com.ckunder.recyclersample2.adl.ViewEntity
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import javax.inject.Inject

class DefaultItemComparator @Inject constructor(
    private val controllerMap: Map<Int, @JvmSuppressWildcards ViewHolderController<*>>
) : ItemComparator() {

    override fun areItemsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        if (item1.type == item2.type) {
            val controller = controllerMap[item1.type] as ViewHolderController<ViewEntity>
            return controller.areItemsTheSame(item1.toViewEntity(), item2.toViewEntity())
        }
        return false
    }

    override fun areContentsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val controller = controllerMap[item1.type] as ViewHolderController<ViewEntity>
        return controller.areContentsTheSame(item1.toViewEntity(), item2.toViewEntity())
    }

    override fun getChangePayload(oldItem: DisplayableItem<*>, newItem: DisplayableItem<*>): Any? {
        val controller = controllerMap[oldItem.type] as ViewHolderController<ViewEntity>
        return controller.getChangePayload(oldItem.toViewEntity(), newItem.toViewEntity())
    }

    private fun DisplayableItem<*>.toViewEntity() = this.model as ViewEntity
}
