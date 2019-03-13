package com.ckunder.recyclersample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool


@Suppress("UNCHECKED_CAST")
class RecyclerViewAdapter<Component : ViewComponent, ViewHolder : com.ckunder.recyclersample.ViewHolder>(
    private val viewPool: RecycledViewPool = RecycledViewPool()
) : RecyclerView.Adapter<ViewHolder>() {

    private val components = arrayListOf<Component>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val component = getComponent(viewType)
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val holder = ViewHolder(itemView) as ViewHolder
        component.onCreate(holder, viewPool)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        components[position].bindView(holder = holder, position = position, payloads = null)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any?>) {
        components[position].bindView(holder = holder, position = position, payloads = payloads)
    }


    fun updateList(list: List<Component>) {
        val result = DiffUtil.calculateDiff(
            ViewComponentComparator(components, list), false
        )

        components.clear()
        components.addAll(list)

        result.dispatchUpdatesTo(this)
    }

    private fun getComponent(@LayoutRes viewType: Int): Component =
        components
            .asSequence()
            .filter { it.getLayoutId() == viewType }
            .first()

    override fun getItemCount(): Int = components.size

    override fun getItemViewType(position: Int): Int = components[position].getLayoutId()
}