package com.ckunder.recyclersample.adapter

data class DisplayableItem<T>(
    val type: Int,
    val model: T,
    val id: Long
)

fun <T> T.toDisplayableItem(type: Int, id: Long): DisplayableItem<T> {
    return DisplayableItem(type, this, id)
}