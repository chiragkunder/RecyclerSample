package com.ckunder.recyclersample2.adl

import java.util.concurrent.atomic.AtomicLong

data class CardAdlViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val title: String,
    val subtitle: String
) : AdlViewEntity