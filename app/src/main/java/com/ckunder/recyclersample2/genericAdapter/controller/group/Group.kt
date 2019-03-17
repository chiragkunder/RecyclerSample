package com.ckunder.recyclersample2.genericAdapter.controller.group

import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem

// Can Group be a Displayable Item?
data class Group(val displayableItemList: List<DisplayableItem<*>>)

fun Group.toDisplayableItem(id: Long = NO_ID): DisplayableItem<*> = DisplayableItem(GROUP_VIEW_TYPE, this, id)

const val GROUP_VIEW_TYPE = 1234
