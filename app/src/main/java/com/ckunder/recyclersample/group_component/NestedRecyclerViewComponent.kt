package com.ckunder.recyclersample.group_component

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.*
import kotlinx.android.synthetic.main.group_view_component.view.*

@Suppress("UNCHECKED_CAST")
class NestedRecyclerViewComponent(
    private val adapter: RecyclerViewAdapter<ViewComponent<ADLViewEntity>, ViewHolder>
) : ViewComponent<GroupADLViewEntity>() {


    override fun onCreate(holder: ViewHolder, viewPool: RecyclerView.RecycledViewPool?) {
        with(holder.itemView.groupRecyclerView) {
            adapter = adapter
            layoutManager = LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL, false)
            setRecycledViewPool(viewPool)
        }
    }

    override fun bindView(holder: ViewHolder, entity: GroupADLViewEntity, payloads: MutableList<Any?>?) {
        adapter.updateList(entity.items)
    }

    override fun sameItem(entity1: GroupADLViewEntity, entity2: GroupADLViewEntity): Boolean = entity1.id == entity2.id

    override fun getLayoutId(): Int = R.layout.group_view_component
}