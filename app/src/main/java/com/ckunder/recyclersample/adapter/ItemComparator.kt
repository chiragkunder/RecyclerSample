package com.ckunder.recyclersample.adapter

interface ItemComparator {

    fun getChangePayload(item1: DisplayableItem<*>, item2: DisplayableItem<*>): Any?

}
