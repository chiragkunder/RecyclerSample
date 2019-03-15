package com.ckunder.recyclersample2.adlAdapter.controller

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample2.adl.AdlViewEntity
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ViewHolderBinder
import com.ckunder.recyclersample2.genericAdapter.ViewHolderFactory

abstract class AdlViewHolderController<T : AdlViewEntity> : ViewHolderFactory, ViewHolderBinder() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<*>) {
        bindView(viewHolder.itemView, item.model as T, null)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<*>, payloads: MutableList<Any>) {
        bindView(viewHolder.itemView, item.model as T, payloads)
    }

    abstract fun bindView(itemView: View, adlViewEntity: T, payloads: MutableList<Any>?)

    open fun areItemsTheSame(entity1: T, entity2: T): Boolean =
        entity1.id == entity2.id

    open fun areContentsTheSame(entity1: T, entity2: T): Boolean =
        entity1 == entity2

    open fun getChangePayload(oldEntity: T, newEntity: T): Any? = null
}
