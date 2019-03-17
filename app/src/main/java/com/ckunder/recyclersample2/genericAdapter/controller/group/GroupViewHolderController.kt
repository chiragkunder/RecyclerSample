package com.ckunder.recyclersample2.genericAdapter.controller.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import com.ckunder.recyclersample2.genericAdapter.RecyclerViewAdapter
import com.ckunder.recyclersample2.genericAdapter.controller.ViewHolderController
import dagger.Lazy
import kotlinx.android.synthetic.main.group_view_component.view.*
import javax.inject.Inject


class GroupViewHolderController @Inject constructor(
    // These can all be the same that the parent adapter uses.
    private val viewPool: RecyclerView.RecycledViewPool,
    private val itemComparator: Lazy<@JvmSuppressWildcards ItemComparator>,
    private val controllerMap: Lazy<Map<Int, @JvmSuppressWildcards ViewHolderController<*>>>
) : ViewHolderController<GroupViewEntity>() {

    override fun inflateView(parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_view_component, parent, false)
        with(view.groupRecyclerView) {
            adapter = RecyclerViewAdapter(itemComparator.get(), controllerMap.get(), controllerMap.get())
            layoutManager = LinearLayoutManager(context)
            setRecycledViewPool(viewPool)
        }
        return view
    }

    override fun bindView(itemView: View, viewEntity: GroupViewEntity, payloads: List<Any>?) {
        val adapter = itemView.groupRecyclerView.adapter as RecyclerViewAdapter
        adapter.update(viewEntity.displayableItemList)
    }

    // The child recycler adapter will calculate this when it gets the list with the elements.
    // To compare here we would need to go through all the elements in entity1 and entity2 lists.
    override fun areItemsTheSame(entity1: GroupViewEntity, entity2: GroupViewEntity): Boolean = false

    override fun areContentsTheSame(entity1: GroupViewEntity, entity2: GroupViewEntity): Boolean = false

    override fun getChangePayload(oldEntity: GroupViewEntity, newEntity: GroupViewEntity): Any? = null
}
