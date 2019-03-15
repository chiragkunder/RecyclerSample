package com.ckunder.recyclersample.recyler_framework

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.ckunder.recyclersample.Identifiable
import java.lang.IllegalStateException
import kotlin.reflect.KClass

class Comparator<Entity : Identifiable>(
    private val delegatesMap: Map<KClass<Entity>, AdapterDelegate<View, Entity>>
) : DiffUtil.ItemCallback<Entity>() {

    override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
        val delegate =
            delegatesMap[oldItem::class]
                ?: throw IllegalStateException("View delegate not found for item of type: ${oldItem::class}.")

        return delegate.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
        val delegate =
            delegatesMap[oldItem::class]
                ?: throw IllegalStateException("View delegate not found for item of type: ${oldItem::class}.")

        return delegate.areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: Entity, newItem: Entity): Any? {
        val delegate =
            delegatesMap[oldItem::class]
                ?: throw IllegalStateException("View delegate not found for item of type: ${oldItem::class}.")

        return delegate.getChangePayload(oldItem, newItem)
    }
}