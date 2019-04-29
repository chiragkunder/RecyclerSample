package com.ckunder.recycler_2.recycler_view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity

@Suppress("TooManyFunctions", "ComplexInterface", "FunctionOnlyReturningConstant")
interface ViewController<ENTITY : ViewEntity> {

    fun getItemType(): Int

    fun inflateView(parent: ViewGroup): View

    fun createViewHolder(parent: ViewGroup): N26ViewHolder = N26ViewHolder(inflateView(parent))

    fun bindView(viewHolder: RecyclerView.ViewHolder, viewEntity: ENTITY, payloads: MutableList<Any>) {
        bindView(viewHolder.itemView, viewEntity, payloads)
    }

    fun bindView(itemView: View, viewEntity: ENTITY, payloads: MutableList<Any>)

    fun unBindView(view: View) {}

    fun onFailedToRecycleView(itemView: View): Boolean = false

    fun onViewAttachedToWindow(itemView: View) {}

    fun onViewDetachedFromWindow(itemView: View) {}

    fun areItemsTheSame(oldItem: ENTITY, newItem: ENTITY): Boolean = oldItem.id == newItem.id

    fun areContentsTheSame(oldItem: ENTITY, newItem: ENTITY): Boolean = oldItem == newItem

    fun getChangePayload(oldItem: ENTITY, newItem: ENTITY): Any? = null
}