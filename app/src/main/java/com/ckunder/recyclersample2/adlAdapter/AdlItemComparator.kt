package com.ckunder.recyclersample2.adlAdapter

import com.ckunder.recyclersample2.adl.AdlViewEntity
import com.ckunder.recyclersample2.adlAdapter.controller.AdlViewHolderController
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import java.lang.IllegalStateException
import kotlin.reflect.KClass

class AdlItemComparator(
    private val controllerMap: Map<Int, AdlViewHolderController<AdlViewEntity>>
) : ItemComparator() {

    override fun areItemsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val adlItem1 = item1.model as AdlViewEntity
        val adlitem2 = item2.model as AdlViewEntity

        if(adlItem1::class == adlitem2::class) {
            return controllerMap[item1.type]?.areItemsTheSame(adlItem1, adlitem2)
                ?: throw IllegalStateException("No controller found for $adlItem1 or $adlitem2")
        }

        return false
    }

    override fun areContentsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val adlItem1 = item1.model as AdlViewEntity
        val adlitem2 = item2.model as AdlViewEntity

        if(adlItem1::class == adlitem2::class) {
            return controllerMap[item1.type]?.areContentsTheSame(adlItem1, adlitem2)
                ?: throw IllegalStateException("No controller found for $adlItem1 or $adlitem2")
        }

        return false
    }

    override fun getChangePayload(oldItem: DisplayableItem<*>, newItem: DisplayableItem<*>): Any? {
        val adlOldItem1 = oldItem.model as AdlViewEntity
        val adlNewItem2 = newItem.model as AdlViewEntity

        if(adlOldItem1::class == adlNewItem2::class) {
            return controllerMap[oldItem.type]?.getChangePayload(adlOldItem1, adlNewItem2)
                ?: throw IllegalStateException("No controller found for $adlOldItem1 or $adlNewItem2")
        }

        return null
    }
}
