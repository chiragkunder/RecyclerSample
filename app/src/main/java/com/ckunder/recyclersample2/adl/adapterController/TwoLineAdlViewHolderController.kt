package com.ckunder.recyclersample2.adl.adapterController

import android.view.View
import android.view.ViewGroup
import com.ckunder.recyclersample2.adl.TwoLineAdlViewComponent
import com.ckunder.recyclersample2.adl.TwoLineAdlViewEntity
import com.ckunder.recyclersample2.genericAdapter.controller.ViewHolderController
import javax.inject.Inject

class TwoLineAdlViewHolderController @Inject constructor() : ViewHolderController<TwoLineAdlViewEntity>() {

    override fun inflateView(parent: ViewGroup): View = TwoLineAdlViewComponent(parent.context)

    override fun bindView(itemView: View, viewEntity: TwoLineAdlViewEntity, payloads: List<Any>?) {
        with(itemView as TwoLineAdlViewComponent) {
            setViewEntity(viewEntity)
        }
    }
}
