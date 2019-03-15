package com.ckunder.recyclersample.recyler_framework

import android.view.View
import android.view.ViewGroup
import com.ckunder.recyclersample.Identifiable

@Suppress("UNCHECKED_CAST")
abstract class AdapterDelegate<VIEW : View, ENTITY : Identifiable> {

    abstract fun getItemType(): Int

    abstract fun createView(parent: ViewGroup): VIEW

    abstract fun bindView(
        holder: N26ViewHolder,
        viewEntity: ENTITY,
        payloads: MutableList<Any?>
    )

    abstract fun unBind(view: VIEW)

    open fun getChangePayload(oldItem: ENTITY, newItem: ENTITY): Any? = null

    open fun areContentsTheSame(oldItem: ENTITY, newItem: ENTITY): Boolean = oldItem == newItem

    open fun areItemsTheSame(oldItem: ENTITY, newItem: ENTITY): Boolean = oldItem.id == newItem.id
}