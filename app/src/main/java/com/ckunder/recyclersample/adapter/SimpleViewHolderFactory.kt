package com.ckunder.recyclersample.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleViewHolderFactory(val context: Context? = null): ViewHolderFactory {

    override fun createViewHolder(parent: ViewGroup, viewPool: RecyclerView.RecycledViewPool?): RecyclerView.ViewHolder {
        return createViewHolder(parent)
    }

    /**
     * Creates a [RecyclerView.ViewHolder]
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @return the newly created [RecyclerView.ViewHolder]
     */
    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
}