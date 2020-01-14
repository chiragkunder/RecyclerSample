package com.ckunder.recyclersample.cards_component

import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.ViewComponent
import com.ckunder.recyclersample.ViewHolder
import kotlinx.android.synthetic.main.two_line_view_component.view.*

private const val TITLE_CHANGED = 0
private const val SUBTITLE_CHANGED = 1

class CardViewComponent : ViewComponent<CardADLViewEntity>() {

    override fun getLayoutId(): Int = R.layout.card_view_component

    override fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool?) {
        holder.itemView.setOnClickListener {
            //Send events
        }
    }

    override fun bindView(holder: ViewHolder, entity: CardADLViewEntity, payloads: MutableList<Any?>?) {

        if (payloads.isNullOrEmpty()) {
            holder.itemView.title.text = entity.title
            holder.itemView.subtitle.text = entity.subtitle
        } else {
            payloads.forEach {
                when (it) {
                    TITLE_CHANGED -> holder.itemView.title.text = entity.title
                    SUBTITLE_CHANGED -> holder.itemView.subtitle.text = entity.subtitle
                }
            }
        }
    }

    override fun sameItem(entity1: CardADLViewEntity, entity2: CardADLViewEntity): Boolean = entity1.id == entity2.id

    override fun getChangePayload(entity1: CardADLViewEntity, entity2: CardADLViewEntity): Any? {
        val changedPayloads = mutableListOf<Int>()

        if (entity1.title != entity2.title) {
            changedPayloads += TITLE_CHANGED
        }

        if (entity2.subtitle != entity2.subtitle) {
            changedPayloads += SUBTITLE_CHANGED
        }

        return changedPayloads
    }

}

