package com.ckunder.recycler_2.recycler_view.amount

import com.ckunder.recycler_2.recycler_view.UniqueId
import com.ckunder.recycler_2.recycler_view.adl.GroupViewEntity
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity

data class GroupViewEntityImpl(override val id: Long = UniqueId.getLong(), override val items: MutableList<ViewEntity>) : GroupViewEntity