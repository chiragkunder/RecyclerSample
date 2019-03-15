package com.ckunder.recyclersample.recyler_framework

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import com.ckunder.recyclersample.Identifiable
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
class RecyclerViewAdapter<Entity : Identifiable, VH : N26ViewHolder>(
    private val delegatesMap: Map<KClass<Entity>, AdapterDelegate<View, Entity>>
) : ListAdapter<Entity, VH>(Comparator<Entity>(delegatesMap)) {

    init {
        setHasStableIds(true)
    }

    private val items = arrayListOf<Entity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val delegate = getDelegateForViewType(viewType)
        val view: View = delegate.createView(parent)
        return N26ViewHolder(view) as VH
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder = holder, position = position, payloads = mutableListOf())
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any?>) {
        val delegate = getDelegateForPosition(position)
        delegate.bindView(holder = holder, viewEntity = items[position], payloads = payloads)
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        getDelegateForViewType(holder.itemViewType).unBind(holder.itemView)
    }

    fun updateList(list: List<Entity>) {
        items.clear()
        items.addAll(list)
        super.submitList(items)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = getDelegateForPosition(position).getItemType()

    private fun getDelegateForViewType(@LayoutRes viewType: Int): AdapterDelegate<View, Entity> =
        delegatesMap
            .entries
            .asSequence()
            .filter { it.value.getItemType() == viewType }
            .map { it.value }
            .firstOrNull() ?: throw IllegalStateException("View delegate not found for ViewType: $viewType")

    private fun getDelegateForPosition(position: Int): AdapterDelegate<View, Entity> {
        val item = items[position]
        return delegatesMap[item::class]
            ?: throw IllegalStateException("View delegate not found for item of type: ${item::class}.")
    }
}