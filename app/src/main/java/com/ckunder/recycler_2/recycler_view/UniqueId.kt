package com.ckunder.recycler_2.recycler_view

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong


object UniqueId {

    private val atomicInteger = AtomicInteger(0)
    private val atomicLong = AtomicLong(0)

    fun getInt() = atomicInteger.decrementAndGet()

    fun getLong() = atomicLong.decrementAndGet()
}