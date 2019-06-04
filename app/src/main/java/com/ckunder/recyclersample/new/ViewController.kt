package com.ckunder.recyclersample.new

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n26.feeds_presentation.adl.ViewEntity

@Suppress("TooManyFunctions", "ComplexInterface", "FunctionOnlyReturningConstant")
internal interface ViewController<Entity : ViewEntity> {

    fun getItemType(): Int

    fun inflateView(parent: ViewGroup): View

    fun createViewHolder(parent: ViewGroup): N26ViewHolder = N26ViewHolder(inflateView(parent))

    fun bindView(viewHolder: RecyclerView.ViewHolder, viewEntity: Entity, payloads: MutableList<Any>) {
        bindView(viewHolder.itemView, viewEntity, payloads)
    }

    fun bindView(itemView: View, viewEntity: Entity, payloads: MutableList<Any>)

    fun unBindView(view: View) {}

    fun onFailedToRecycleView(itemView: View): Boolean = false

    fun onViewAttachedToWindow(itemView: View) {}

    fun onViewDetachedFromWindow(itemView: View) {}

    fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean = oldItem.id == newItem.id

    fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean = oldItem == newItem

    fun getChangePayload(oldItem: Entity, newItem: Entity): Any? = null
}