//package com.ckunder.recycler_2.recycler_view
//
//import android.content.res.Resources
//import android.graphics.*
//import android.util.TypedValue
//import android.view.View
//import androidx.recyclerview.widget.RecyclerView
//
//
//class CardItemDecoration(resources: Resources) : RecyclerView.ItemDecoration() {
//
//
//    internal var mPaint: Paint
//
//    internal var mCornerShadowPaint: Paint
//
//    internal var mEdgeShadowPaint: Paint
//
//    internal val mPreShadowBounds: RectF
//
//    private val mDirty = true
//
//    private val mShadowStartColor: Int = resources.getColor(com.ckunder.recyclersample.R.color.cardview_shadow_start_color)
//    private val mShadowEndColor: Int = resources.getColor(com.ckunder.recyclersample.R.color.cardview_shadow_end_color)
//    private var mPadding: Float = 0.toFloat()
//    private val mShadowSize = 0.0f
//
//    private val path: Path = Path()
//    private val mCornerShadowPath: Path = Path()
//    private val rectF: RectF = RectF().apply { set(0.0f, 0.0f, 0.0f, 0.0f) }
//    private var firstItemRect: RectF = RectF().apply { set(0.0f, 0.0f, 0.0f, 0.0f) }
//    private var lastItemRect: RectF = RectF().apply { set(0.0f, 0.0f, 0.0f, 0.0f) }
//
//    var cornerRadius = 20.0f
//
//    private val topLeftRadius = cornerRadius
//    private val topRightRadius = cornerRadius
//    private val bottomLeftRadius = cornerRadius
//    private val bottomRightRadius = cornerRadius
//
//
//    init {
//
//        mPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
//        mPaint.color = Color.BLACK
//        mCornerShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
//        mCornerShadowPaint.style = Paint.Style.FILL
//        mCornerShadowPaint.isDither = true
//        mPreShadowBounds = RectF()
//        mEdgeShadowPaint = Paint(mCornerShadowPaint)
//
//    }
//    val clipPath = Path()
//    val shadowPaint = Paint().apply {
//        style = Paint.Style.STROKE
//        color = Color.BLACK
//    }
//
//    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
////        super.onDraw(c, parent, state)
//
//
//
//        val padding16dp =
//            TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                16f,
//                parent.context.resources.getDisplayMetrics()
//            ).toInt()
//
//        clipPath.reset()
//
//        for (i in 0 until parent.childCount) {
//            val view = parent.getChildAt(i)
//            val params = view.layoutParams as RecyclerView.LayoutParams
//
//            val lm = parent.layoutManager
//
//
//            val position = params.viewAdapterPosition
//            val adapter: RecyclerViewAdapter = parent.adapter as RecyclerViewAdapter
//            val item = adapter.getModelItems()[position]
//            val viewType = adapter.getItemViewType(position)
//            val controller = adapter.getControllerByViewType(viewType)
//            val groupViewEntity = adapter.getGroupViewEntityForPosition(position)
//            val groupItems = groupViewEntity.items
//            val groupIndex = groupItems.indexOf(item)
//
//            if (groupIndex == 0 && groupItems.size == 1) {
//                //Only item in group
//
//                c.clipRect(bounds)
//                clipPath.addRoundRect(
//                    bounds,
//                    cornerRadius,
//                    cornerRadius,
//                    Path.Direction.CW
//                )
////                c.drawTopRoundRect(bounds, shadowPaint, cornerRadius)
////                path.reset()
////                path.addRoundRect(
////                    rectF,
////                    floatArrayOf(
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius
////                    ),
////                    Path.Direction.CCW
////                )
////                c.clipRect(bounds)
////                c.clipPath(path)
//
//            } else if (groupIndex == groupItems.size - 1) {
//                //Last item in group
//                val bounds = RectF()
//                bounds.set(view.left.toFloat(), view.top.toFloat(), view.right.toFloat(), view.bottom.toFloat())
//
//                c.clipRect(bounds)
//                clipPath.addRoundRect(
//                    bounds,
//                    cornerRadius,
//                    cornerRadius,
//                    Path.Direction.CW
//                )
////                val clipPath = Path()
////                clipPath.addRoundRect(
////                    bounds,
////                    cornerRadius,
////                    cornerRadius,
////                    Path.Direction.CW
////                )
////                c.clipPath(clipPath)
////                c.drawTopRoundRect(bounds, shadowPaint, cornerRadius)
////                path.reset()
////                path.addRoundRect(
////                    rectF,
////                    floatArrayOf(
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius
////                    ),
////                    Path.Direction.CCW
////                )
////                c.clipRect(bounds)
////                c.clipPath(path)
//            } else if (groupIndex == 0 && groupItems.size > 1) {
//                //First item in group
//
//                val bounds = RectF()
//                bounds.set(view.left.toFloat(), view.top.toFloat(), view.right.toFloat(), view.bottom.toFloat())
//
//                c.clipRect(bounds)
//                clipPath.addRoundRect(
//                    bounds,
//                    floatArrayOf(
//                        cornerRadius,
//                        cornerRadius,
//                        cornerRadius,
//                        cornerRadius,
//                        0.0f,
//                        0.0f,
//                        0.0f,
//                        0.0f
//                    ),
//                    Path.Direction.CCW
//                )
//
////                clipPath.reset()
////                clipPath.addRoundRect(
////                    bounds,
////                    cornerRadius,
////                    cornerRadius,
////                    Path.Direction.CW
////                )
////                c.clipPath(clipPath)
////                c.drawTopRoundRect(bounds, shadowPaint, cornerRadius)
////                path.reset()
////                path.addRoundRect(
////                    rectF,
////                    floatArrayOf(
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        cornerRadius,
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        0.0f
////                    ),
////                    Path.Direction.CCW
////                )
////                c.clipRect(bounds)
////                c.clipPath(path)
//            } else {
//                //Middle
//                val bounds = RectF()
//                bounds.set(view.left.toFloat(), view.top.toFloat(), view.right.toFloat(), view.bottom.toFloat())
//
//                c.clipRect(bounds)
//                clipPath.addRoundRect(
//                    bounds,
//                    floatArrayOf(
//                        0.0f,
//                        0.0f,
//                        0.0f,
//                        0.0f,
//                        0.0f,
//                        0.0f,
//                        0.0f,
//                        0.0f
//                    ),
//                    Path.Direction.CCW
//                )
////                val clipPath = Path()
////                clipPath.addRoundRect(
////                    bounds,
////                    cornerRadius,
////                    cornerRadius,
////                    Path.Direction.CW
////                )
////                c.clipPath(clipPath)
////                c.drawTopRoundRect(bounds, shadowPaint, cornerRadius)
////                path.reset()
////                path.addRoundRect(
////                    rectF,
////                    floatArrayOf(
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        0.0f,
////                        0.0f
////                    ),
////                    Path.Direction.CCW
////                )
////                c.clipRect(bounds)
////                c.clipPath(path)
//            }
//        }
//
//        c.clipPath(clipPath)
//    }
//
//    private fun setRoundRect(rectF: RectF) {
//        path.reset()
//        path.addRoundRect(
//            rectF,
//            floatArrayOf(
//                topLeftRadius,
//                topLeftRadius,
//                topRightRadius,
//                topRightRadius,
//                bottomLeftRadius,
//                bottomLeftRadius,
//                bottomRightRadius,
//                bottomRightRadius
//            ),
//            Path.Direction.CCW
//        )
//    }
//
//
//    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        super.getItemOffsets(outRect, view, parent, state)
//
//        val padding16dp =
//            TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                16f,
//                parent.context.resources.getDisplayMetrics()
//            ).toInt()
//
//        val position = parent.getChildAdapterPosition(view)
//        val adapter: RecyclerViewAdapter = parent.adapter as RecyclerViewAdapter
//        val item = adapter.getModelItems()[position]
//        val viewType = adapter.getItemViewType(position)
//        val controller = adapter.getControllerByViewType(viewType)
//        val groupViewEntity = adapter.getGroupViewEntityForPosition(position)
//        val groupItems = groupViewEntity.items
//        val groupIndex = groupItems.indexOf(item)
//
//        if (groupIndex == 0 && groupItems.size == 1) {
//            //Only item in group
////            outRect.set(0, padding16dp, 0, padding16dp)
//        } else if (groupIndex == groupItems.size - 1) {
//            //Last item in group
////            outRect.set(0, 0, 0, padding16dp)
//        } else if (groupIndex == 0 && groupItems.size > 1) {
//            //First item in group
////            outRect.set(0, padding16dp, 0, 0)
//        } else {
//            //Middle
////            outRect.set(0, 0, 0, 0)
//        }
//
//    }
//
//
//    private fun Canvas.drawTopRoundRect(rect: RectF, shadowPaint: Paint, radius: Float) {
//        // Step 1. Draw rect with rounded corners.
//        drawRoundRect(rect, radius, radius, shadowPaint)
//
//        // Step 2. Draw simple rect with reduced height,
//        // so it wont cover top rounded corners.
//        drawRect(
//            rect.left,
//            rect.top + radius,
//            rect.right,
//            rect.bottom,
//            shadowPaint
//        )
//    }
//}