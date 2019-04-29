package com.ckunder.recycler_2.recycler_view

import android.graphics.Outline
import android.graphics.Rect
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.FloatRange
import timber.log.Timber

class OnlyItemOutlineProvider(
    val cornerRadius: Float = 20f,
    var scaleX: Float = 1f,
    var scaleY: Float = 1f,
    var yShift: Int = 0
) : ViewOutlineProvider() {

    private val rect: Rect = Rect()

    override fun getOutline(view: View, outline: Outline?) {
        Timber.i("Outline called")
        rect.set(view.left, view.top, view.right, view.bottom)

//        rect.scale(scaleX, scaleY)
//        rect.offset(0, yShift)
        Timber.i("Can clip : ${outline?.canClip()}")
        outline?.setRoundRect(rect, cornerRadius)
        view.clipToOutline = true
    }
}

private fun Rect.scale(
    @FloatRange(from = -1.0, to = 1.0) scaleX: Float,
    @FloatRange(from = -1.0, to = 1.0) scaleY: Float
) {
    val newWidth = width() * scaleX
    val newHeight = height() * scaleY
    val deltaX = (width() - newWidth) / 2
    val deltaY = (height() - newHeight) / 2

    set((left + deltaX).toInt(), (top + deltaY).toInt(), (right - deltaX).toInt(), (bottom - deltaY).toInt())
}