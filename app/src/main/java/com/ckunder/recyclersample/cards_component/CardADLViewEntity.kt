package com.ckunder.recyclersample.cards_component

import com.ckunder.recyclersample.ADLViewEntity
import java.util.concurrent.atomic.AtomicLong

data class CardADLViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val title: String,
    val subtitle: String
) : ADLViewEntity