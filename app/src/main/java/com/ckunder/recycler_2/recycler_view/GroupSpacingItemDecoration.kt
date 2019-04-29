package com.ckunder.recycler_2.recycler_view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity

/**
 * [RecyclerView.ItemDecoration] to add equal spacing between all [RecyclerView] items.
 */
class GroupSpacingItemDecoration internal constructor(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val adapter: RecyclerViewAdapter = parent.adapter as RecyclerViewAdapter
        val item = adapter.getModelItems()[position]
        val groupViewEntity = adapter.getGroupViewEntityForPosition(position)
        val groupItems = groupViewEntity.items
        val groupIndex = groupItems.indexOf(item)

        with(outRect) {

            left = spaceHeight
            right = spaceHeight

            when {
                isOnlyItem(groupIndex, groupItems) -> {
                    top = spaceHeight
                    bottom = spaceHeight
                }
                isLastItem(groupIndex, groupItems) -> {
                    top = 0
                    bottom = spaceHeight
                }
                isFirstItem(groupIndex, groupItems) -> {
                    top = spaceHeight
                    bottom = 0
                }
                else -> {
                    top = 0
                    bottom = 0
                }
            }
        }
    }

    private fun isOnlyItem(
        groupIndex: Int,
        groupItems: List<ViewEntity>
    ) = groupIndex == 0 && groupItems.size == 1

    private fun isFirstItem(
        groupIndex: Int,
        groupItems: List<ViewEntity>
    ) = groupIndex == 0 && groupItems.size > 1

    private fun isLastItem(
        groupIndex: Int,
        groupItems: List<ViewEntity>
    ) = groupIndex == groupItems.size - 1 && groupItems.size > 1
}