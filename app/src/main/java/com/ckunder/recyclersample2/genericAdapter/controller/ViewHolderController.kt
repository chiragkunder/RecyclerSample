package com.ckunder.recyclersample2.genericAdapter.controller

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample2.adl.ViewEntity
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ViewHolderBinder
import com.ckunder.recyclersample2.genericAdapter.ViewHolderFactory

abstract class ViewHolderController<T : ViewEntity> : ViewHolderFactory, ViewHolderBinder {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(inflateView(parent))

    abstract fun inflateView(parent: ViewGroup): View

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>) {
        bindView(viewHolder.itemView, item.model as T, null)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>, payloads: List<Any>) {
        bindView(viewHolder.itemView, item.model as T, payloads)
    }

    abstract fun bindView(itemView: View, viewEntity: T, payloads: List<Any>?)

    open fun areItemsTheSame(entity1: T, entity2: T): Boolean =
        entity1.id == entity2.id

    open fun areContentsTheSame(entity1: T, entity2: T): Boolean =
        entity1 == entity2

    open fun getChangePayload(oldEntity: T, newEntity: T): Any? = null
}
