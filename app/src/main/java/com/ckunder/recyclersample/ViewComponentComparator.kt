package com.ckunder.recyclersample

import androidx.recyclerview.widget.DiffUtil

class ViewComponentComparator(
    private val oldComponents: List<ViewComponent>,
    private val newComponents: List<ViewComponent>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldComponent = oldComponents[oldItemPosition]
        val newComponent = newComponents[newItemPosition]

        return oldComponent.areItemsTheSame(newComponent)
    }

    override fun getOldListSize(): Int = oldComponents.size

    override fun getNewListSize(): Int = newComponents.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldComponent = oldComponents[oldItemPosition]
        val newComponent = newComponents[newItemPosition]

        return oldComponent.areContentsTheSame(newComponent)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldComponent = oldComponents[oldItemPosition]
        val newComponent = newComponents[newItemPosition]

        return oldComponent.getChangePayload(newComponent)
    }
}