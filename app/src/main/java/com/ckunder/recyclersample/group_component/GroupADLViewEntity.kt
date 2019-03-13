package com.ckunder.recyclersample.group_component

import com.ckunder.recyclersample.ADLViewEntity
import com.ckunder.recyclersample.headline_component.TwoLineADLViewEntity
import java.util.concurrent.atomic.AtomicLong

data class GroupADLViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val items: List<TwoLineADLViewEntity>
) : ADLViewEntity