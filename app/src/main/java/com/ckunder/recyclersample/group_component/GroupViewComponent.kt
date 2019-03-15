package com.ckunder.recyclersample.group_component

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample.headline_component.TwoLineViewComponent
import com.ckunder.recyclersample.recyler_framework.AdapterDelegateForXML
import com.ckunder.recyclersample.recyler_framework.N26ViewHolder
import com.ckunder.recyclersample.recyler_framework.RecyclerViewAdapter
import kotlinx.android.synthetic.main.group_view_component.view.*

class GroupViewComponent() : AdapterDelegateForXML<GroupADLViewEntity>() {

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindView(holder: N26ViewHolder, viewEntity: GroupADLViewEntity, payloads: MutableList<Any?>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun getLayoutId(): Int = R.layout.group_view_component
//
//    private lateinit var recyclerViewAdapter: RecyclerViewAdapter<ADLViewController, N26ViewHolder>
//
//    override fun onCreate(holder: N26ViewHolder, viewPool: RecyclerView.RecycledViewPool) {
//        recyclerViewAdapter = RecyclerViewAdapter(viewPool)
//        with(holder.itemView.groupRecyclerView) {
//            adapter = recyclerViewAdapter
//            layoutManager = LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL, false)
//            setRecycledViewPool(viewPool)
//        }
//
//        Log.i("SAMPLE_VIEWPOOL", "Grouped: $viewPool")
//    }
//
//    override fun bindView(holder: N26ViewHolder, position: Int, payloads: MutableList<Any?>?) {
//
//        recyclerViewAdapter.updateList(groupADLViewEntity.items.map { TwoLineViewComponent(it) })
//    }
}