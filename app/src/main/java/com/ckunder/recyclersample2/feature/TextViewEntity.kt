package com.ckunder.recyclersample2.feature

import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.adl.ViewEntity
import java.util.concurrent.atomic.AtomicLong

data class TextViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val text: String
) : ViewEntity {

    override val viewType: Int
        get() = R.layout.item_text
}