package com.ckunder.recyclersample2.feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.genericAdapter.controller.ViewHolderController
import kotlinx.android.synthetic.main.item_text.view.*
import javax.inject.Inject

class TextViewHolderController @Inject constructor() : ViewHolderController<TextViewEntity>() {

    override fun inflateView(parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)

    override fun bindView(itemView: View, viewEntity: TextViewEntity, payloads: List<Any>?) {
        itemView.tv_text.text = viewEntity.text
    }
}
