package com.ckunder.recyclersample2.genericAdapter.controller.group

import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.ckunder.recyclersample2.adl.ViewEntity
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import java.util.concurrent.atomic.AtomicLong

// Can GroupViewEntity be a Displayable Item?
data class GroupViewEntity(
    // Having id calculated like this is risky. Collisions can happen.
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val displayableItemList: List<DisplayableItem<*>>
) : ViewEntity

fun GroupViewEntity.toDisplayableItem(id: Long = NO_ID): DisplayableItem<*> = DisplayableItem(GROUP_VIEW_TYPE, this, id)

const val GROUP_VIEW_TYPE = 1234
