package com.ckunder.recyclersample.textview_delegate

import android.view.ViewGroup
import android.widget.TextView
import com.ckunder.recyclersample.recyler_framework.AdapterDelegateForView

private val UNIQUE_ID_FOR_VIEWS = 111

class TextViewDelegate : AdapterDelegateForView<TextView, TextViewEntity>() {

    override fun getItemType(): Int = UNIQUE_ID_FOR_VIEWS

    override fun createView(parent: ViewGroup): TextView = TextView(parent.context)

    override fun bindView(view: TextView, viewEntity: TextViewEntity, payloads: MutableList<Any?>) {
        view.text = viewEntity.title
    }
}