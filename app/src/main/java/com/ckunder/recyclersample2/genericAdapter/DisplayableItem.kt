package com.ckunder.recyclersample2.genericAdapter

data class DisplayableItem<T>(val type: Int, val model: T, val id: Long = -1)

fun <T> T.toDisplayableItem(type: Int): DisplayableItem<T> = DisplayableItem(type, this)

fun <T> List<T>.toDisplayableItem(type: Int): List<DisplayableItem<T>> = map { DisplayableItem(type, it) }