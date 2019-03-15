package com.ckunder.recyclersample.group_component

import com.ckunder.recyclersample.Identifiable
import com.ckunder.recyclersample.two_line_delegate.TwoLineADLViewEntity
import java.util.concurrent.atomic.AtomicLong

data class GroupADLViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val items: List<TwoLineADLViewEntity>
) : Identifiable