package com.ckunder.recyclersample2.adlAdapter.controller

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample2.adl.TwoLineAdlViewComponent
import com.ckunder.recyclersample2.adl.TwoLineAdlViewEntity
import com.ckunder.recyclersample2.adlAdapter.AdlViewHolder
import javax.inject.Inject

class TwoLineAdlViewHolderController @Inject constructor() : AdlViewHolderController<TwoLineAdlViewEntity>() {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        AdlViewHolder(TwoLineAdlViewComponent(parent.context))

    override fun bindView(itemView: View, adlViewEntity: TwoLineAdlViewEntity, payloads: MutableList<Any>?) {
        with(itemView as TwoLineAdlViewComponent) {
            setViewEntity(adlViewEntity)
        }
    }
}
