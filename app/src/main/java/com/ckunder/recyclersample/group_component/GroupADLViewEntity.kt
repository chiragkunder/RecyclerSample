package com.ckunder.recyclersample.group_component

import com.ckunder.recycler_2.recycler_view.adl.ViewEntity
import com.ckunder.recycler_2.recycler_view.amount.TwoLineADLViewEntity
import com.ckunder.recyclersample.ADLViewEntity
import java.util.concurrent.atomic.AtomicLong

data class GroupADLViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val items: List<TwoLineADLViewEntity>
) : ViewEntity