package com.ckunder.recyclersample2.adl

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ckunder.recyclersample.R
import kotlinx.android.synthetic.main.two_line_view_component.view.*

class TwoLineAdlViewComponent(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    root: ViewGroup? = null
) :
    FrameLayout(context, attrs, defStyleAttr), AdlViewComponent<TwoLineAdlViewEntity> {

    init {
        root
            ?.let { LayoutInflater.from(context).inflate(getLayoutId(), it, false) }
            ?: LayoutInflater.from(context).inflate(getLayoutId(), this)
    }

    override fun getLayoutId(): Int = R.layout.two_line_view_component

    override fun setViewEntity(viewEntity: TwoLineAdlViewEntity) {
        title.text = viewEntity.title
        subtitle.text = viewEntity.subtitle
    }
}
