package com.ckunder.recycler_2.recycler_view

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity
import com.ckunder.recycler_2.recycler_view.amount.TwoLineADLViewEntity
import com.ckunder.recyclersample.R

class CardItemDecoration2(val context: Context) : RecyclerView.ItemDecoration() {

    private val path = Path()
    private val rectF = RectF()

    var cornerRadius = 20.0f
    var shadowSize = 12f
    var shadowColor = Color.BLACK
    var shadowStartColor = ContextCompat.getColor(context, R.color.cardview_shadow_start_color)
    var shadowEndColor = ContextCompat.getColor(context, R.color.cardview_shadow_end_color)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        isDither = true
//        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        setShadowLayer(shadowSize, 0f, 0f, shadowColor)

        val startRatio = cornerRadius / (cornerRadius + shadowSize)
        shader = RadialGradient(
            0f,
            0f,
            cornerRadius + shadowSize,
            intArrayOf(shadowStartColor, shadowStartColor, shadowEndColor),
            floatArrayOf(0f, startRatio, 1f),
            Shader.TileMode.CLAMP
        )
    }

    init {

        val innerBounds = RectF(-cornerRadius, -cornerRadius, cornerRadius, cornerRadius)
        val outerBounds = RectF(innerBounds)
        outerBounds.inset(-shadowSize, -shadowSize)

        path.fillType = Path.FillType.EVEN_ODD
        // outer arc
        path.arcTo(outerBounds, 180f, 90f, false);
        // inner arc
        path.arcTo(innerBounds, 270f, -90f, false);
        path.close();
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        path.reset()

        (0 until parent.childCount)
            .forEach { i ->
                val view = parent.getChildAt(i)

                rectF.set(view.left.toFloat(), view.top.toFloat(), view.right.toFloat(), view.bottom.toFloat())

                val position = parent.getChildAdapterPosition(view)
                val adapter: RecyclerViewAdapter = parent.adapter as RecyclerViewAdapter
                val item = adapter.getModelItems()[position]
                val groupViewEntity = adapter.getGroupViewEntityForPosition(position)
                val groupItems = groupViewEntity.items
                val groupIndex = groupItems.indexOf(item)

                when {
                    isOnlyItem(groupIndex, groupItems) -> {
                        //Only item in group
                        addRoundRect(rectF, cornerRadius, cornerRadius, cornerRadius, cornerRadius)
                    }
                    isLastItem(groupIndex, groupItems) -> {
                        //Last item in a group with multiple items
                        addRoundRect(rectF, bottomLeftRadius = cornerRadius, bottomRightRadius = cornerRadius)
                    }
                    isFirstItem(groupIndex, groupItems) -> {
                        //First item in group with multiple times
                        addRoundRect(rectF, topLeftRadius = cornerRadius, topRightRadius = cornerRadius)
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

        c.drawPath(path, paint)
        c.clipPath(path)
    }

    private fun addRoundRect(
        rectF: RectF,
        topLeftRadius: Float = 0.0f,
        topRightRadius: Float = 0.0f,
        bottomLeftRadius: Float = 0.0f,
        bottomRightRadius: Float = 0.0f
    ) {
        path.addRoundRect(
            rectF,
            floatArrayOf(
                topLeftRadius,
                topLeftRadius,
                topRightRadius,
                topRightRadius,
                bottomLeftRadius,
                bottomLeftRadius,
                bottomRightRadius,
                bottomRightRadius
            ),
            Path.Direction.CW
        )
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