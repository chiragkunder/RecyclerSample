package com.ckunder.recyclersample2.adlAdapter.controller

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample2.adl.AdlViewEntity
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.controller.ViewHolderController

abstract class AdlViewHolderController<T : AdlViewEntity> : ViewHolderController<T>() {

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>) {
        bindView(viewHolder.itemView, item.model as T, null)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>, payloads: List<Any>) {
        bindView(viewHolder.itemView, item.model as T, payloads)
    }

    abstract fun bindView(itemView: View, adlViewEntity: T, payloads: List<Any>?)

    // Try to do this with composition instead of inheritance.
    override fun areItemsTheSame(entity1: T, entity2: T): Boolean =
        entity1.id == entity2.id

    override fun areContentsTheSame(entity1: T, entity2: T): Boolean =
        entity1 == entity2

    override fun getChangePayload(oldEntity: T, newEntity: T): Any? = null
}
