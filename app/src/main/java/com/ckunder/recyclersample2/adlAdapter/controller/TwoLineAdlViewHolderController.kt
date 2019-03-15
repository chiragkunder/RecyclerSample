package com.ckunder.recyclersample2.adlAdapter.controller

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.adl.TwoLineAdlViewComponent
import com.ckunder.recyclersample2.adl.TwoLineAdlViewEntity
import com.ckunder.recyclersample2.adlAdapter.AdlViewHolder

class TwoLineAdlViewHolderController : AdlViewHolderController<TwoLineAdlViewEntity>() {

    override fun createViewHolder(
        parent: ViewGroup,
        viewPool: RecyclerView.RecycledViewPool?
    ): RecyclerView.ViewHolder = AdlViewHolder(TwoLineAdlViewComponent(parent.context), getLayoutId())

    override fun getLayoutId(): Int = R.layout.two_line_view_component
}
