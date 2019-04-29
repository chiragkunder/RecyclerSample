package com.ckunder.recycler_2.recycler_view.amount

import com.ckunder.recycler_2.recycler_view.UniqueId
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity

data class TwoLineADLViewEntity(
    override val id: Long = UniqueId.getLong(),
    val title: String,
    val subtitle: String
) : ViewEntity