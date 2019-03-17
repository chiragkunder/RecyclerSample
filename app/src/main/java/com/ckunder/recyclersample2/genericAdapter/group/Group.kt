package com.ckunder.recyclersample2.genericAdapter.group

import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem

data class Group(val displayableItemList: List<DisplayableItem<*>>)

fun Group.toDisplayableItem(id: Long = NO_ID): DisplayableItem<*> = DisplayableItem(GROUP_VIEW_TYPE, this, id)

const val GROUP_VIEW_TYPE = 1234
