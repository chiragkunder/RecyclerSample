package com.ckunder.recyclersample

import com.ckunder.recyclersample.adapter.DisplayableItem
import com.ckunder.recyclersample.adapter.SimpleItemComparator
import kotlin.reflect.KClass

class ViewComponentComparator(
    private val componentsMap: Map<KClass<out ADLViewEntity>, ViewComponent<ADLViewEntity>>
) : SimpleItemComparator {

    override fun areItemsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val adlViewEntity1 = item1.model as ADLViewEntity
        val adlViewEntity2 = item2.model as ADLViewEntity
        val viewComponent = componentsMap[adlViewEntity1::class] as ViewComponent<ADLViewEntity>
        return viewComponent.sameItem(adlViewEntity1, adlViewEntity2)
    }

    override fun areContentsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val adlViewEntity1 = item1.model as ADLViewEntity
        val adlViewEntity2 = item2.model as ADLViewEntity
        val viewComponent = componentsMap[adlViewEntity1::class] as ViewComponent<ADLViewEntity>
        return viewComponent.sameContent(adlViewEntity1, adlViewEntity2)
    }

    override fun getChangePayload(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Any? {
        val adlViewEntity1 = item1.model as ADLViewEntity
        val adlViewEntity2 = item2.model as ADLViewEntity
        val viewComponent = componentsMap[adlViewEntity1::class] as ViewComponent<ADLViewEntity>
        return viewComponent.getChangePayload(adlViewEntity1, adlViewEntity2)
    }
}