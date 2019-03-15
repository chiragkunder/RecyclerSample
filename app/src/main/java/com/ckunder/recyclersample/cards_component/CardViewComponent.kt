package com.ckunder.recyclersample.cards_component

import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.recyler_framework.AdapterDelegateForXML
import com.ckunder.recyclersample.recyler_framework.N26ViewHolder
import kotlinx.android.synthetic.main.two_line_view_component.view.*

private const val TITLE_CHANGED = 0
private const val SUBTITLE_CHANGED = 1

class CardViewComponent : AdapterDelegateForXML<CardADLViewEntity>() {

    override fun getLayoutId(): Int = R.layout.card_view_component

    override fun bindView(holder: N26ViewHolder, viewEntity: CardADLViewEntity, payloads: MutableList<Any?>) {
        if (payloads.isNullOrEmpty()) {
            holder.itemView.title.text = viewEntity.title
            holder.itemView.subtitle.text = viewEntity.subtitle
        } else {
            payloads.forEach {
                when (it) {
                    TITLE_CHANGED -> holder.itemView.title.text = viewEntity.title
                    SUBTITLE_CHANGED -> holder.itemView.subtitle.text = viewEntity.subtitle
                }
            }
        }
    }

    override fun getChangePayload(oldItem: CardADLViewEntity, newItem: CardADLViewEntity): Any? {
        val changedPayloads = mutableListOf<Int>()

        if (oldItem.title != newItem.title) {
            changedPayloads += TITLE_CHANGED
        }

        if (oldItem.subtitle != newItem.subtitle) {
            changedPayloads += SUBTITLE_CHANGED
        }

        return changedPayloads
    }
}

