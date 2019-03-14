package com.ckunder.recyclersample2.genericAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


interface ViewHolderFactory {

    fun createViewHolder(parent: ViewGroup, viewPool: RecyclerView.RecycledViewPool? = null): RecyclerView.ViewHolder
}