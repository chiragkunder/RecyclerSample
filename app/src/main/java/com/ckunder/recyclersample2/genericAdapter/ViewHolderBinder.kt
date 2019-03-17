package com.ckunder.recyclersample2.genericAdapter

import androidx.recyclerview.widget.RecyclerView

interface ViewHolderBinder {

    fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<@JvmSuppressWildcards Any>)

    fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<@JvmSuppressWildcards Any>, payloads: List<@JvmSuppressWildcards Any>)
}