package com.ckunder.recyclersample2.adlAdapter

import com.ckunder.recyclersample2.adl.AdlViewEntity
import com.ckunder.recyclersample2.adlAdapter.controller.AdlViewHolderController
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import javax.inject.Inject

// This assumes all the elements in the RV are ADL. If not a custom comparator must be created.
class AdlItemComparator @Inject constructor(
    private val controllerMap: Map<Int, @JvmSuppressWildcards AdlViewHolderController<*>>
) : ItemComparator() {

    override fun areItemsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val adlItem1 = item1.model as AdlViewEntity
        val adlitem2 = item2.model as AdlViewEntity

        if (adlItem1::class == adlitem2::class) {
            val controller = controllerMap[item1.type] as AdlViewHolderController<AdlViewEntity>
            return controller.areItemsTheSame(adlItem1, adlitem2)
        }

        return false
    }

    override fun areContentsTheSame(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Boolean {
        val adlItem1 = item1.model as AdlViewEntity
        val adlitem2 = item2.model as AdlViewEntity

        if (adlItem1::class == adlitem2::class) {
            val controller = controllerMap[item1.type] as AdlViewHolderController<AdlViewEntity>
            return controller.areContentsTheSame(adlItem1, adlitem2)
        }

        return false
    }

    override fun getChangePayload(oldItem: DisplayableItem<*>, newItem: DisplayableItem<*>): Any? {
        val adlOldItem1 = oldItem.model as AdlViewEntity
        val adlNewItem2 = newItem.model as AdlViewEntity

        if (adlOldItem1::class == adlNewItem2::class) {
            val controller = controllerMap[oldItem.type] as AdlViewHolderController<AdlViewEntity>
            return controller.getChangePayload(adlOldItem1, adlNewItem2)
        }

        return null
    }
}
