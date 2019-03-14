package com.ckunder.recyclersample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.adapter.*

@Suppress("UNCHECKED_CAST")
abstract class ViewComponent<T>(context: Context? = null) : ViewHolderBinder, SimpleViewHolderFactory(context) {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool?)

    abstract fun bindView(
        holder: ViewHolder,
        entity: T,
        payloads: MutableList<Any?>? = null
    )

    abstract fun sameItem(entity1: T, entity2: T): Boolean

    open fun sameContent(entity1: T, entity2: T): Boolean = entity1 == entity2

    open fun getChangePayload(entity1: T, entity2: T): Any? = null

    // ------------------------------------------------

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<*>) {
        bindView(viewHolder as ViewHolder, item.model as T, null)
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
        val holder = ViewHolder(itemView)
        onCreate(holder, null)
        return holder
    }

    override fun createViewHolder(
        parent: ViewGroup,
        viewPool: RecyclerView.RecycledViewPool?
    ): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
        val holder = ViewHolder(itemView)
        onCreate(holder, viewPool)
        return holder
    }
}
