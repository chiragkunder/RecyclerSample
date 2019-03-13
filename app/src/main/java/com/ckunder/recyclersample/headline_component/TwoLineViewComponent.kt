package com.ckunder.recyclersample.headline_component

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.ViewHolder
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.ViewComponent
import kotlinx.android.synthetic.main.two_line_view_component.view.*

class TwoLineViewComponent(
    private val twoLineADLViewEntity: TwoLineADLViewEntity
) : ViewComponent(twoLineADLViewEntity) {

    override fun getLayoutId(): Int = R.layout.two_line_view_component

    override fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool) {
        val position = holder.adapterPosition

        holder.itemView.setOnClickListener {
            //Send events
        }

        Log.i("SAMPLE_VIEWPOOL", "Headline: $viewPool")
    }

    override fun bindView(holder: ViewHolder, position: Int, payloads: MutableList<Any?>?) {
        holder.itemView.title.text = twoLineADLViewEntity.title
        holder.itemView.subtitle.text = twoLineADLViewEntity.subtitle
    }
}

