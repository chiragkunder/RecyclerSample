package com.ckunder.recyclersample.cards_component

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.ViewHolder
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.ViewComponent
import kotlinx.android.synthetic.main.two_line_view_component.view.*

private const val TITLE_CHANGED = 0
private const val SUBTITLE_CHANGED = 1

class CardViewComponent(
    private val cardViewEntity: CardADLViewEntity
) : ViewComponent(cardViewEntity) {

    override fun getLayoutId(): Int = R.layout.card_view_component

    override fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool) {
        val position = holder.adapterPosition

        holder.itemView.setOnClickListener {
            //Send events
        }

        Log.i("SAMPLE_VIEWPOOL", "CardView: $viewPool")
    }

    override fun bindView(holder: ViewHolder, position: Int, payloads: MutableList<Any?>?) {

        if (payloads.isNullOrEmpty()) {
            holder.itemView.title.text = cardViewEntity.title
            holder.itemView.subtitle.text = cardViewEntity.subtitle
        } else {
            payloads.forEach {
                when (it) {
                    TITLE_CHANGED -> holder.itemView.title.text = cardViewEntity.title
                    SUBTITLE_CHANGED -> holder.itemView.subtitle.text = cardViewEntity.subtitle
                }
            }
        }
    }

    override fun areContentsTheSame(viewComponent: ViewComponent): Boolean {
        return super.areContentsTheSame(viewComponent)
    }

    override fun getChangePayload(newComponent: ViewComponent): Any? {
        val cardViewComponent = newComponent as CardViewComponent

        val changedPayloads = mutableListOf<Int>()

        if (cardViewEntity.title != cardViewComponent.cardViewEntity.title) {
            changedPayloads += TITLE_CHANGED
        }

        if (cardViewEntity.subtitle != cardViewComponent.cardViewEntity.subtitle) {
            changedPayloads += SUBTITLE_CHANGED
        }

        return changedPayloads
    }
}

