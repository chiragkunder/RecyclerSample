package com.ckunder.recycler_2.recycler_view

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val MAX_PREFETCH_ITEMS_LIMIT = 20
private const val DEFAULT_VIEW_CACHE_SIZE = 10

/**
 * Initialises [RecyclerView] with the given View pool and
 * sets a default view cache size to improve view look up for [RecyclerView].
 *
 * Note: This method does not set the adapter, layout manager or any other [RecyclerView] configurations.
 */
fun RecyclerView.initialise(
    viewPool: RecyclerView.RecycledViewPool,
    viewCacheSize: Int = DEFAULT_VIEW_CACHE_SIZE,
    block: RecyclerView.() -> Unit
) {
    with(this) {
        isNestedScrollingEnabled = false
        setRecycledViewPool(viewPool)
        setItemViewCacheSize(viewCacheSize)
        this.block()
    }
}

/**
 * [LinearLayoutManager] optimised for nested [RecyclerView]'s.
 *
 * Initial prefetch of items are enabled by default to avoid UI janks on the initial scroll or fling.
 */
fun Context.getNestedLinearLayoutManager(
    prefetchItemCount: Int = MAX_PREFETCH_ITEMS_LIMIT
): LinearLayoutManager =
    object : LinearLayoutManager(this) {

        override fun canScrollVertically(): Boolean = false
    }.apply {
        isItemPrefetchEnabled = true
        initialPrefetchItemCount = prefetchItemCount
    }