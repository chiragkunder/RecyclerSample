package com.ckunder.recyclersample2.genericAdapter

import com.ckunder.recyclersample2.adl.ViewEntity
import com.ckunder.recyclersample2.genericAdapter.controller.group.GroupViewEntity

fun recyclerViewList(vararg elements: ViewEntity) =
    elements.map { it.toDisplayableItem(it.viewType) }

fun nestedGroup(vararg elements: ViewEntity) =
    GroupViewEntity(displayableItemList = elements.map { it.toDisplayableItem(it.viewType) })