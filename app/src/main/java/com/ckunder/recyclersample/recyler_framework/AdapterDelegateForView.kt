package com.ckunder.recyclersample.recyler_framework

import android.view.View
import com.ckunder.recyclersample.Identifiable

@Suppress("UNCHECKED_CAST")
abstract class AdapterDelegateForView<VIEW : View, ENTITY : Identifiable> : AdapterDelegate<ENTITY>() {

    abstract fun bindView(
        view: VIEW,
        viewEntity: ENTITY,
        payloads: MutableList<Any?>
    )

    override fun bindView(holder: N26ViewHolder, viewEntity: ENTITY, payloads: MutableList<Any?>) {
        bindView(holder.itemView as VIEW, viewEntity, payloads)
    }

    override fun unBind(view: View) {}
}