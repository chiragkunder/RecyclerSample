package com.ckunder.recyclersample

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.ckunder.recyclersample.adapter.*
import com.ckunder.recyclersample.adapter.RecyclerViewAdapter
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
class RecyclerViewAdapter<Component : ViewComponent<ADLViewEntity>, ViewHolder : com.ckunder.recyclersample.ViewHolder>(
    private val viewPool: RecycledViewPool? = RecycledViewPool(),
    private val componentsMap: Map<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>>
) : RecyclerViewAdapter(
    ViewComponentComparator(componentsMap),
    createHolderFactory(componentsMap),
    createBinder(componentsMap)
) {

    private val items = arrayListOf<ADLViewEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val component = getComponentForViewType(viewType)
        return component.createViewHolder(parent, viewPool) as ViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val component = getComponentForItem(items[position])
        val item = items[position]
        component.bind(holder, item.toDisplayableItem(component.getLayoutId(), item.id))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any?>) {
        val component = getComponentForItem(items[position])
        val item = items[position]
        component.bindView(holder as com.ckunder.recyclersample.ViewHolder, item, payloads)
    }

    fun updateList(list: List<ADLViewEntity>) {
        items.clear()
        items.addAll(list)

        val displayableItemsList: List<DisplayableItem<ADLViewEntity>> =
            list.map { it.toDisplayableItem(getComponentForItem(it).getLayoutId(), it.id) }

        super.submitList(displayableItemsList)
    }

    override fun getItemViewType(position: Int): Int = getComponentForItem(items[position]).getLayoutId()

    private fun getComponentForViewType(@LayoutRes viewType: Int): Component =
        componentsMap
            .entries
            .filter { it.value.getLayoutId() == viewType }
            .map { it.value }
            .first() as Component

    private fun getComponentForItem(item: ADLViewEntity): Component = componentsMap[item::class] as Component
}

fun createBinder(componentsMap: Map<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>>): Map<Int, ViewHolderBinder> =
    componentsMap
        .entries
        .associate { it.value.getLayoutId() to it.value }

fun createHolderFactory(componentsMap: Map<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>>): Map<Int, ViewHolderFactory> =
    componentsMap
        .entries
        .associate { it.value.getLayoutId() to it.value }
