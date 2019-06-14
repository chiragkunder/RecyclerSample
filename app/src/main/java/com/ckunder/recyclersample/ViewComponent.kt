package com.ckunder.recyclersample

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity

abstract class ViewComponent(private val adlViewEntity: ViewEntity) {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool)

    abstract fun bindView(
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any?>? = null
    )

    open fun areItemsTheSame(viewComponent: ViewComponent): Boolean =
        this.adlViewEntity.id == viewComponent.adlViewEntity.id

    open fun areContentsTheSame(viewComponent: ViewComponent): Boolean =
        this.adlViewEntity == viewComponent.adlViewEntity

    open fun getChangePayload(newComponent: ViewComponent): Any? = null

}