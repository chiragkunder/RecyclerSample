package com.ckunder.recyclersample2.genericAdapter

data class DisplayableItem<T> (val type: Int, val model: T, val id: Long)