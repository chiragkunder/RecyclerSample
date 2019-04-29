package com.ckunder.recycler_2.recycler_view

import android.content.Context
import android.graphics.*
import android.view.View.LAYER_TYPE_SOFTWARE
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recycler_2.recycler_view.adl.ViewEntity
import com.ckunder.recycler_2.recycler_view.amount.TwoLineADLViewEntity
import timber.log.Timber


class CardItemDecorationWithShadow(val context: Context) : RecyclerView.ItemDecoration() {

    val SHADOW_MULTIPLIER = 1.5f

    private val path = Path()
    private val rectF = RectF()

    var cornerRadius = 20.0f
    var shadowSize = 8.0f * SHADOW_MULTIPLIER
    var shadowColor = ContextCompat.getColor(context, com.ckunder.recyclersample.R.color.colorShadow)
    var shadowStartColor =
        ContextCompat.getColor(context, com.ckunder.recyclersample.R.color.cardview_shadow_start_color)
    var shadowEndColor = ContextCompat.getColor(context, com.ckunder.recyclersample.R.color.cardview_shadow_end_color)

    val shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        setShadowLayer(shadowSize, 0f, 0f, shadowColor)

        style = Paint.Style.FILL
        isDither = true
//        val startRatio = cornerRadius / (cornerRadius + shadowSize)
//        shader = RadialGradient(
//            0f,
//            0f,
//            cornerRadius + shadowSize,
//            intArrayOf(shadowStartColor, shadowStartColor, shadowEndColor),
//            floatArrayOf(0f, startRatio, 1f),
//            Shader.TileMode.CLAMP
//        )
    }

    val edgePaint = Paint(shadowPaint).apply {

        //        shader = LinearGradient(
//            0f, -cornerRadius + shadowSize, 0f, -cornerRadius - shadowSize,
//            intArrayOf(shadowStartColor, shadowStartColor, shadowEndColor), floatArrayOf(0f, .5f, 1f),
//            Shader.TileMode.CLAMP
//        )
    }

    init {

//        val innerBounds = RectF(-cornerRadius, -cornerRadius, cornerRadius, cornerRadius)
//        val outerBounds = RectF(innerBounds)
//        outerBounds.inset(-shadowSize, -shadowSize)
//
//        path.fillType = Path.FillType.EVEN_ODD;
//        path.moveTo(-cornerRadius, 0f);
//        path.rLineTo(-shadowSize, 0f);
//        // outer arc
//        path.arcTo(outerBounds, 180f, 90f, false);
//        // inner arc
//        path.arcTo(innerBounds, 270f, -90f, false);
//        path.close();

        buildShadowCorners()

    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        path.reset()

        val edgeShadowTop = -cornerRadius - shadowSize
        val cornerPathSize = 2 * (cornerRadius + shadowSize)

        (0 until parent.childCount)
            .forEach { i ->
                val save = c.save()
                val view = parent.getChildAt(i)
                view.setLayerType(LAYER_TYPE_SOFTWARE, null)

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

                        Timber.i("DECORATORRR: Only item ${(item as TwoLineADLViewEntity).title}")
                    }
                    isLastItem(groupIndex, groupItems) -> {
                        //Last item in a group with multiple items

                        addRoundRect(rectF, bottomLeftRadius = cornerRadius, bottomRightRadius = cornerRadius)

                        // last item before next header
                        //LT
                        c.rotate(180f)
                        c.translate(
                            -rectF.left - rectF.width() + cornerRadius,
                            -rectF.top - rectF.height() + cornerRadius
                        )

                        c.drawPath(path, shadowPaint)
                        c.drawRect(0f, edgeShadowTop, rectF.width() - 2 * cornerRadius, -cornerRadius, edgePaint)

                        // RT / Right border
                        c.rotate(90f)
                        c.translate(0f, -rectF.width() + 2 * cornerRadius)
                        c.drawPath(path!!, shadowPaint)
                        c.drawRect(0f, edgeShadowTop, rectF.height() - cornerRadius, -cornerRadius, edgePaint)

                        // Left border
                        c.rotate(180f)
                        c.translate((-rectF.height()).toFloat(), -rectF.width() + 2 * cornerRadius)
                        c.drawRect(
                            cornerRadius,
                            edgeShadowTop,
                            rectF.height().toFloat(),
                            -cornerRadius,
                            edgePaint
                        )

                        Timber.i("DECORATORRR: last item ${(item as TwoLineADLViewEntity).title}")
                    }
                    isFirstItem(groupIndex, groupItems) -> {
                        //First item in group with multiple times

                        addRoundRect(rectF, topLeftRadius = cornerRadius, topRightRadius = cornerRadius)

                        // LT
                        c.translate(rectF.left + cornerRadius, rectF.top + cornerRadius)
                        c.drawPath(path!!, shadowPaint)
                        c.drawRect(0f, edgeShadowTop, rectF.width() - 2 * cornerRadius, -cornerRadius, edgePaint)

                        // RT
                        c.rotate(90f)
                        c.translate(0f, -rectF.width() + 2 * cornerRadius)
//                        c.translate(rectF.right + cornerRadius, rectF.top + cornerRadius)
                        c.drawPath(path, shadowPaint)
                        c.drawRect(0f, edgeShadowTop, rectF.height() - cornerRadius, -cornerRadius, edgePaint)

                        // LBorder
                        c.rotate(180f)
                        c.translate((-rectF.height()).toFloat(), -rectF.width() + 2 * cornerRadius)
                        c.drawRect(cornerRadius, edgeShadowTop, rectF.height().toFloat(), -cornerRadius, edgePaint)

                        Timber.i("DECORATORRR: first item ${(item as TwoLineADLViewEntity).title}")
                    }
                    else -> {
                        //Middle

                        path.addRect(
                            rectF,
                            Path.Direction.CW
                        )

                        // Right border
                        c.translate(rectF.left.toFloat(), rectF.top.toFloat())
                        c.rotate(90f)
                        c.translate(0f, -rectF.width() + cornerRadius)
                        c.drawRect(0f, edgeShadowTop, rectF.height().toFloat(), -cornerRadius, edgePaint)

                        // Left border
                        c.rotate(180f)
                        c.translate((-rectF.height()).toFloat(), -rectF.width() + 2 * cornerRadius)
                        c.drawRect(0f, edgeShadowTop, rectF.height().toFloat(), -cornerRadius, edgePaint)

                        Timber.i("DECORATORRR: Middle ${(item as TwoLineADLViewEntity).title}")
                    }
                }
                c.restoreToCount(save)
            }

//        c.drawRect(0f, -cornerRadius - shadowSize, rectF.width() - 2 * cornerRadius, -cornerRadius, edgePaint);
//        c.drawPath(path, shadowPaint)
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


    private fun buildShadowCorners() {

        val innerBounds = RectF(-cornerRadius, -cornerRadius, cornerRadius, cornerRadius)
        val outerBounds = RectF(innerBounds)
        outerBounds.inset(-shadowSize, -shadowSize)

        with(path) {
            reset()
            fillType = Path.FillType.EVEN_ODD
            moveTo(-cornerRadius, 0f)
            rLineTo(-shadowSize, 0f)
            // outer arc
            arcTo(outerBounds, 180f, 90f, false)
            // inner arc
            arcTo(innerBounds, 270f, -90f, false)
            close()

        }

        val startRatio = cornerRadius / (cornerRadius + shadowSize)
        shadowPaint.shader = RadialGradient(
            0f,
            0f,
            cornerRadius + shadowSize,
            intArrayOf(shadowStartColor, shadowStartColor, shadowEndColor),
            floatArrayOf(0f, startRatio, 1f),
            Shader.TileMode.CLAMP
        )

        // we offset the content shadowSize/2 pixels up to make it more realistic.
        // this is why edge shadow shader has some extra space
        // When drawing bottom edge shadow, we use that extra space.
        edgePaint.shader = LinearGradient(
            0f, -cornerRadius + shadowSize, 0f, -cornerRadius - shadowSize,
            intArrayOf(shadowStartColor, shadowStartColor, shadowEndColor), floatArrayOf(0f, .5f, 1f),
            Shader.TileMode.CLAMP
        )
    }
}