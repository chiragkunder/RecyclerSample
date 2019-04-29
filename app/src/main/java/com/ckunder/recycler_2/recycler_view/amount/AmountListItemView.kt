package com.ckunder.recycler_2.recycler_view.amount

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.ckunder.recyclersample.R
import kotlinx.android.synthetic.main.two_line_view_component.view.*

class AmountListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.two_line_view_component, this)
        isClickable = true
    }

    fun setViewEntity(viewEntity: TwoLineADLViewEntity) {
        title.text = viewEntity.title
        subtitle.text = viewEntity.subtitle
    }
}