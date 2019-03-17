package com.ckunder.recyclersample2.genericAdapter.controller

import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import javax.inject.Inject

class DefaultItemComparator @Inject constructor(
    private val controllerMap: Map<Int, @JvmSuppressWildcards ViewHolderController<*>>
) : ItemComparator() {

    override fun areItemsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        if (item1.model!!::class == item2.model!!::class) {
            val controller = controllerMap[item1.type] as ViewHolderController<Any>
            return controller.areItemsTheSame(item1.model, item2.model)
        }
        return false
    }

    override fun areContentsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val controller = controllerMap[item1.type] as ViewHolderController<Any>
        return controller.areContentsTheSame(item1.model!!, item2.model!!)
    }

    override fun getChangePayload(oldItem: DisplayableItem<*>, newItem: DisplayableItem<*>): Any? {
        val controller = controllerMap[oldItem.type] as ViewHolderController<Any>
        return controller.getChangePayload(oldItem.model!!, newItem.model!!)
    }
}
