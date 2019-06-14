package com.ckunder.recycler_2.recycler_view

import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity


class CardItemDecorationWithOutlineProvider() : RecyclerView.ItemDecoration() {

    val enableShadow = true
    private val path = Path()
    private val rectF = RectF()

    var cornerRadius = 20.0f
    var shadowSize = 8f

    private val onlyItemOutlineProvider = OnlyItemOutlineProvider(cornerRadius)


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val position = parent.getChildAdapterPosition(view)
        val adapter: RecyclerViewAdapter = parent.adapter as RecyclerViewAdapter
        val item = adapter.getModelItems()[position]
        val groupViewEntity = adapter.getGroupViewEntityForPosition(position)
        val groupItems = groupViewEntity.items
        val groupIndex = groupItems.indexOf(item)

        when {
            isOnlyItem(groupIndex, groupItems) -> {
                //Only item in group
                view.clipToOutline = true
                view.outlineProvider = onlyItemOutlineProvider
//                        addRoundRect(rectF, cornerRadius, cornerRadius, cornerRadius, cornerRadius)
            }
            isLastItem(groupIndex, groupItems) -> {
                //Last item in a group with multiple items
            }
            isFirstItem(groupIndex, groupItems) -> {
                //First item in group with multiple times
            }
            else -> {
                //Middle
                path.addRect(
                    rectF,
                    Path.Direction.CW
                )
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