package com.ckunder.recyclersample2.adl

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ckunder.recyclersample.R
import kotlinx.android.synthetic.main.two_line_view_component.view.*
import java.util.concurrent.atomic.AtomicLong

class CardAdlViewComponent(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    root: ViewGroup? = null
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        root
            ?.let { LayoutInflater.from(context).inflate(layoutId, it, false) }
            ?: LayoutInflater.from(context).inflate(layoutId, this)
    }

    fun setViewEntity(viewEntity: CardAdlViewEntity) {
        title.text = viewEntity.title
        subtitle.text = viewEntity.subtitle
    }

    companion object {
        const val layoutId = R.layout.card_view_component
    }
}

data class CardAdlViewEntity(
    override val id: Long = AtomicLong(Math.random().toLong()).decrementAndGet(),
    val title: String,
    val subtitle: String
) : ViewEntity {

    override val viewType: Int
        get() = CardAdlViewComponent.layoutId
}
