package com.ckunder.recyclersample.headline_component

import android.view.View
import android.view.ViewGroup
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.recyler_framework.AdapterDelegateForXML
import com.ckunder.recyclersample.recyler_framework.N26ViewHolder
import kotlinx.android.synthetic.main.two_line_view_component.view.*

class TwoLineViewComponent : AdapterDelegateForXML<TwoLineADLViewEntity>() {

    override fun getLayoutId(): Int = R.layout.two_line_view_component

    override fun createView(parent: ViewGroup): View {
        val view = super.createView(parent)
        //Handle clicks
        return view
    }

    override fun bindView(holder: N26ViewHolder, viewEntity: TwoLineADLViewEntity, payloads: MutableList<Any?>) {
        holder.itemView.title.text = viewEntity.title
        holder.itemView.subtitle.text = viewEntity.subtitle
    }
}

