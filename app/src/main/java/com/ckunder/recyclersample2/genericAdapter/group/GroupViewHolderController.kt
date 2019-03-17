package com.ckunder.recyclersample2.genericAdapter.group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.genericAdapter.*
import dagger.Lazy
import kotlinx.android.synthetic.main.group_view_component.view.*
import javax.inject.Inject


class GroupViewHolderController @Inject constructor(
    private val viewPool: RecyclerView.RecycledViewPool,
    private val itemComparator: Lazy<@JvmSuppressWildcards ItemComparator>,
    private val controllerMap: Lazy<Map<Int, @JvmSuppressWildcards ViewHolderController<*>>>
) : ViewHolderController<Group>() {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_view_component, parent, false)
        with(view.groupRecyclerView) {
            adapter = RecyclerViewAdapter(itemComparator.get(), controllerMap.get(), controllerMap.get())
            layoutManager = LinearLayoutManager(context)
            setRecycledViewPool(viewPool)
        }
        return ViewHolder(view)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>) {
        val group = item.model as Group
        val adapter = viewHolder.itemView.groupRecyclerView.adapter as RecyclerViewAdapter
        adapter.update(group.displayableItemList)
    }

    // The child recycler adapter will calculate this when it gets the list with the elements.
    // To compare here we would need to go through all the elements in entity1 and entity2 lists.
    override fun areItemsTheSame(entity1: Group, entity2: Group): Boolean = false

    override fun areContentsTheSame(entity1: Group, entity2: Group): Boolean = false

    override fun getChangePayload(oldEntity: Group, newEntity: Group): Any? = null
}
