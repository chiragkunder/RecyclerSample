package com.ckunder.recyclersample.headline_component

import com.ckunder.recyclersample.ADLViewEntity
import java.util.concurrent.atomic.AtomicLong

data class TwoLineADLViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val title: String,
    val subtitle: String
) : ADLViewEntity