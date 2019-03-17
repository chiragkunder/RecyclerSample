package com.ckunder.recyclersample2.adlAdapter.controller

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample2.adl.CardAdlViewComponent
import com.ckunder.recyclersample2.adl.CardAdlViewEntity
import com.ckunder.recyclersample2.genericAdapter.ViewHolder
import kotlinx.android.synthetic.main.card_view_component.view.*
import javax.inject.Inject

private const val TITLE_CHANGED = 0
private const val SUBTITLE_CHANGED = 1

class CardAdlViewHolderController @Inject constructor() : AdlViewHolderController<CardAdlViewEntity>() {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(CardAdlViewComponent(parent.context))

    override fun bindView(
        itemView: View,
        adlViewEntity: CardAdlViewEntity,
        payloads: List<Any>?
    ) {
        if (payloads.isNullOrEmpty()) {
            with(itemView as CardAdlViewComponent) {
                setViewEntity(adlViewEntity)
            }
        } else {
            payloads.forEach {
                when (it) {
                    TITLE_CHANGED -> itemView.title.text = adlViewEntity.title
                    SUBTITLE_CHANGED -> itemView.subtitle.text = adlViewEntity.subtitle
                }
            }
        }
    }

    override fun getChangePayload(oldEntity: CardAdlViewEntity, newEntity: CardAdlViewEntity): Any? {
        val changedPayloads = mutableListOf<Int>()

        if (oldEntity.title != newEntity.title) {
            changedPayloads += TITLE_CHANGED
        }

        if (oldEntity.subtitle != newEntity.subtitle) {
            changedPayloads += SUBTITLE_CHANGED
        }

        return changedPayloads
    }
}