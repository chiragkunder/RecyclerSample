package com.ckunder.recycler_2.recycler_view

import android.content.res.Resources
import android.graphics.*
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R

/**
 * Created by David on 16.06.2015.
 */
class CardViewDecoration(resources: Resources, backgroundColor: Int, private var mCornerRadius: Float = 40.0f) :
    RecyclerView.ItemDecoration() {

    /*
32 	 * This helper is set by CardView implementations. <p> Prior to API 17, canvas.drawRoundRect is expensive; which is
33 	 * why we need this interface to draw efficient rounded rectangles before 17.
34 	 */
    //    static RoundRectHelper sRoundRectHelper;

    internal var mPaint: Paint

    internal var mCornerShadowPaint: Paint

    internal var mEdgeShadowPaint: Paint

    internal val mPreShadowBounds: RectF

    internal var mCornerShadowPath: Path? = null

    private val mDirty = true

    private val mShadowStartColor: Int = resources.getColor(R.color.cardview_shadow_start_color)
    private val mShadowEndColor: Int = resources.getColor(R.color.cardview_shadow_end_color)
    private var mPadding: Float = 0.toFloat()
    private val mShadowSize = 14.0f


    init {

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mPaint.color = backgroundColor
        mCornerShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mCornerShadowPaint.style = Paint.Style.FILL
        mCornerShadowPaint.isDither = true
        mPreShadowBounds = RectF()
        mEdgeShadowPaint = Paint(mCornerShadowPaint)

        buildShadowCorners()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val bounds = Rect()
        val edgeShadowTop = -mCornerRadius - mShadowSize

        val lm = parent.layoutManager
        val size16dp = 16f
        val padding16dp =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size16dp, parent.context.resources.displayMetrics)
                .toInt()

        for (i in 0 until parent.childCount) {
            val save = c.save()

            // using decorated values, remove what we set before
            val child = parent.getChildAt(i)
            bounds.set(
                lm!!.getDecoratedLeft(child) + padding16dp - mPadding.toInt(),
                lm.getDecoratedTop(child),
                lm.getDecoratedRight(child) - padding16dp + mPadding.toInt(),
                lm.getDecoratedBottom(child)
            )

            val params = child.layoutParams as RecyclerView.LayoutParams

//            val position = parent.getChildAdapterPosition(view)
            val position = params.viewAdapterPosition
            val adapter: RecyclerViewAdapter = parent.adapter as RecyclerViewAdapter
            val item = adapter.getModelItems()[position]
            val viewType = adapter.getItemViewType(position)
            val controller = adapter.getControllerByViewType(viewType)
            val groupViewEntity = adapter.getGroupViewEntityForPosition(position)
            val groupItems = groupViewEntity.items

            val groupIndex = groupItems.indexOf(item)
//            val viewType = parent.adapter!!.getItemViewType(position)


            if (groupIndex == 0 && groupItems.size > 1) {
                bounds.top = (bounds.top + padding16dp - mPadding).toInt()

                // LT
                c.translate(bounds.left + mCornerRadius, bounds.top + mCornerRadius)
                c.drawPath(mCornerShadowPath!!, mCornerShadowPaint)
                c.drawRect(0f, edgeShadowTop, bounds.width() - 2 * mCornerRadius, -mCornerRadius, mEdgeShadowPaint)

                // RT
                c.rotate(90f)
                c.translate(0f, -bounds.width() + 2 * mCornerRadius)
                c.drawPath(mCornerShadowPath!!, mCornerShadowPaint)
                c.drawRect(0f, edgeShadowTop, bounds.height() - mCornerRadius, -mCornerRadius, mEdgeShadowPaint)

                // LBorder
                c.rotate(180f)
                c.translate((-bounds.height()).toFloat(), -bounds.width() + 2 * mCornerRadius)
                c.drawRect(mCornerRadius, edgeShadowTop, bounds.height().toFloat(), -mCornerRadius, mEdgeShadowPaint)


                addRoundRect(RectF(bounds), topLeftRadius = mCornerRadius, topRightRadius = mCornerRadius)


            } else {
                if (groupItems.size - 1 == groupIndex) {
                    bounds.bottom = (bounds.bottom - padding16dp + mPadding).toInt()

                    // last item before next header
                    c.rotate(180f)
                    c.translate(
                        -bounds.left - bounds.width() + mCornerRadius,
                        -bounds.top - bounds.height() + mCornerRadius
                    )

                    c.drawPath(mCornerShadowPath!!, mCornerShadowPaint)
                    c.drawRect(0f, edgeShadowTop, bounds.width() - 2 * mCornerRadius, -mCornerRadius, mEdgeShadowPaint)

                    // RT / Right border
                    c.rotate(90f)
                    c.translate(0f, -bounds.width() + 2 * mCornerRadius)
                    c.drawPath(mCornerShadowPath!!, mCornerShadowPaint)
                    c.drawRect(0f, edgeShadowTop, bounds.height() - mCornerRadius, -mCornerRadius, mEdgeShadowPaint)

                    // Left border
                    c.rotate(180f)
                    c.translate((-bounds.height()).toFloat(), -bounds.width() + 2 * mCornerRadius)
                    c.drawRect(
                        mCornerRadius,
                        edgeShadowTop,
                        bounds.height().toFloat(),
                        -mCornerRadius,
                        mEdgeShadowPaint
                    )
                } else {
                    // Right border
                    c.translate(bounds.left.toFloat(), bounds.top.toFloat())
                    c.rotate(90f)
                    c.translate(0f, -bounds.width() + mCornerRadius)
                    c.drawRect(0f, edgeShadowTop, bounds.height().toFloat(), -mCornerRadius, mEdgeShadowPaint)

                    // Left border
                    c.rotate(180f)
                    c.translate((-bounds.height()).toFloat(), -bounds.width() + 2 * mCornerRadius)
                    c.drawRect(0f, edgeShadowTop, bounds.height().toFloat(), -mCornerRadius, mEdgeShadowPaint)
                }
            }
            c.restoreToCount(save)
        }
    }

    private fun buildShadowCorners() {

        //        mPadding = (float) (Math.sqrt((double) mCornerRadius * (double) mCornerRadius * 2) - (double) mCornerRadius);
        mPadding = 0f

        val innerBounds = RectF(-mCornerRadius, -mCornerRadius, mCornerRadius, mCornerRadius)
        val outerBounds = RectF(innerBounds)
        outerBounds.inset(-mShadowSize, -mShadowSize)

        if (mCornerShadowPath == null) {
            mCornerShadowPath = Path()
        } else {
            mCornerShadowPath!!.reset()
        }
        mCornerShadowPath!!.fillType = Path.FillType.EVEN_ODD
        mCornerShadowPath!!.moveTo(-mCornerRadius, 0f)
        mCornerShadowPath!!.rLineTo(-mShadowSize, 0f)
        // outer arc
        mCornerShadowPath!!.arcTo(outerBounds, 180f, 90f, false)
        // inner arc
        mCornerShadowPath!!.arcTo(innerBounds, 270f, -90f, false)
        mCornerShadowPath!!.close()

        val startRatio = mCornerRadius / (mCornerRadius + mShadowSize)
        mCornerShadowPaint.shader = RadialGradient(
            0f,
            0f,
            mCornerRadius + mShadowSize,
            intArrayOf(mShadowStartColor, mShadowStartColor, mShadowEndColor),
            floatArrayOf(0f, startRatio, 1f),
            Shader.TileMode.CLAMP
        )

        // we offset the content shadowSize/2 pixels up to make it more realistic.
        // this is why edge shadow shader has some extra space
        // When drawing bottom edge shadow, we use that extra space.
        mEdgeShadowPaint.shader = LinearGradient(
            0f, -mCornerRadius + mShadowSize, 0f, -mCornerRadius - mShadowSize,
            intArrayOf(mShadowStartColor, mShadowStartColor, mShadowEndColor), floatArrayOf(0f, .5f, 1f),
            Shader.TileMode.CLAMP
        )
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val padding16dp =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                16f,
                parent.context.resources.getDisplayMetrics()
            ).toInt()

        val position = parent.getChildAdapterPosition(view)
        val adapter: RecyclerViewAdapter = parent.adapter as RecyclerViewAdapter
        val item = adapter.getModelItems()[position]
        val viewType = adapter.getItemViewType(position)
        val controller = adapter.getControllerByViewType(viewType)
        val groupViewEntity = adapter.getGroupViewEntityForPosition(position)
        val groupItems = groupViewEntity.items

        val groupIndex = groupItems.indexOf(item)

        if (groupIndex == 0 && groupItems.size == 1) {
            //Only item in group
            outRect.set(0, padding16dp, 0, padding16dp)
        } else if (groupIndex == groupItems.size - 1) {
            //Last item in group
            outRect.set(0, 0, 0, padding16dp)
        } else if (groupIndex == 0 && groupItems.size > 1) {
            //First item in group
            outRect.set(0, padding16dp, 0, 0)
        } else {
            //Middle
            outRect.set(0, 0, 0, 0)
        }

    }

//    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        super.getItemOffsets(outRect, view, parent, state)
//        val resources = parent.context.resources
//
//        val size16dp = 16f
//        val padding16dp =
//            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size16dp, resources.displayMetrics).toInt()
//
//        val params = view.layoutParams as RecyclerView.LayoutParams
//        val position = params.viewAdapterPosition
//        val viewType = parent.adapter!!.getItemViewType(position)
//
//        if (viewType == HeaderItemTestAdapter.HEADER) {
//            // header
//            outRect.set(0, padding16dp, 0, 0)
//        } else {
//            if (parent.adapter!!.getItemViewType(position + 1) == HeaderItemTestAdapter.HEADER) {
//                // last item before next header
//                outRect.set(0, 0, 0, padding16dp)
//            }
//        }
//        //        outRect.inset((int) size16dp, 0);
//        outRect.left = padding16dp
//        outRect.right = padding16dp
//    }

    private fun addRoundRect(
        rectF: RectF,
        topLeftRadius: Float = 0.0f,
        topRightRadius: Float = 0.0f,
        bottomLeftRadius: Float = 0.0f,
        bottomRightRadius: Float = 0.0f
    ) {
        mCornerShadowPath!!.addRoundRect(
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

    private fun Canvas.drawTopRoundRect(rect: RectF, paint: Paint, radius: Float) {
        // Step 1. Draw rect with rounded corners.
        drawRoundRect(rect, radius, radius, paint)

        // Step 2. Draw simple rect with reduced height,
        // so it wont cover top rounded corners.
        drawRect(
            rect.left,
            rect.top + radius,
            rect.right,
            rect.bottom,
            paint
        )
    }
}