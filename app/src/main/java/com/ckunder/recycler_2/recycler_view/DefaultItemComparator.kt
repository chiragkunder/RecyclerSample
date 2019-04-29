package com.ckunder.recycler_2.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity

class DefaultItemComparator internal constructor(
    private val viewControllersMap: ViewControllersMap
) : DiffUtil.ItemCallback<ViewEntity>() {

    override fun areItemsTheSame(oldItem: ViewEntity, newItem: ViewEntity): Boolean {
        if (oldItem::class == newItem::class) {
            val controller = viewControllersMap[oldItem::class] ?: throwControllerNotFound(oldItem)
            return controller.areItemsTheSame(oldItem, newItem)
        }
        return false
    }

    override fun areContentsTheSame(oldItem: ViewEntity, newItem: ViewEntity): Boolean {
        val controller = viewControllersMap[oldItem::class] ?: throwControllerNotFound(oldItem)
        return controller.areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: ViewEntity, newItem: ViewEntity): Any? {
        val controller = viewControllersMap[oldItem::class] ?: throwControllerNotFound(oldItem)
        return controller.getChangePayload(oldItem, newItem)
    }

    private fun throwControllerNotFound(entity: ViewEntity): Nothing {
        throw IllegalStateException("View holder controller not found for item: ${entity::class}.")
    }
}