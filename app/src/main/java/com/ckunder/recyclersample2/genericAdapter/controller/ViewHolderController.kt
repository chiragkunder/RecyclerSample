package com.ckunder.recyclersample2.genericAdapter.controller

import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ViewHolderBinder
import com.ckunder.recyclersample2.genericAdapter.ViewHolderFactory

abstract class ViewHolderController<T> : ViewHolderBinder, ViewHolderFactory {

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>, payloads: List<Any>) {
        bind(viewHolder, item)
    }

    abstract fun areItemsTheSame(entity1: T, entity2: T): Boolean

    abstract fun areContentsTheSame(entity1: T, entity2: T): Boolean

    abstract fun getChangePayload(oldEntity: T, newEntity: T): Any?
}
