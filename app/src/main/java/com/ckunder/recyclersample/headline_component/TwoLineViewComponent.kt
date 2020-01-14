package com.ckunder.recyclersample.headline_component

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.ViewHolder
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.ViewComponent
import kotlinx.android.synthetic.main.two_line_view_component.view.*

class TwoLineViewComponent : ViewComponent<TwoLineADLViewEntity>() {

    override fun getLayoutId(): Int = R.layout.two_line_view_component

    override fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool?) {
        holder.itemView.setOnClickListener {
            //Send events
        }
    }

    override fun bindView(holder: ViewHolder, entity: TwoLineADLViewEntity, payloads: MutableList<Any?>?) {
        holder.itemView.title.text = entity.title
        holder.itemView.subtitle.text = entity.subtitle
    }

    override fun sameItem(entity1: TwoLineADLViewEntity, entity2: TwoLineADLViewEntity): Boolean =
        entity1.id == entity2.id
}

