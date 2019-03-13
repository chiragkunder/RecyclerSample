package com.ckunder.recyclersample.group_component

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.ViewHolder
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.RecyclerViewAdapter
import com.ckunder.recyclersample.ViewComponent
import com.ckunder.recyclersample.headline_component.TwoLineViewComponent
import kotlinx.android.synthetic.main.group_view_component.view.*

class GroupViewComponent(private val groupADLViewEntity: GroupADLViewEntity) : ViewComponent(groupADLViewEntity) {

    override fun getLayoutId(): Int = R.layout.group_view_component

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter<ViewComponent, ViewHolder>

    override fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool) {
        recyclerViewAdapter = RecyclerViewAdapter(viewPool)
        with(holder.itemView.groupRecyclerView) {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL, false)
            setRecycledViewPool(viewPool)
        }

        Log.i("SAMPLE_VIEWPOOL", "Grouped: $viewPool")
    }

    override fun bindView(holder: ViewHolder, position: Int, payloads: MutableList<Any?>?) {

        recyclerViewAdapter.updateList(groupADLViewEntity.items.map { TwoLineViewComponent(it) })
    }
}