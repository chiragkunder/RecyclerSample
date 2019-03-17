package com.ckunder.recyclersample2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.genericAdapter.DisplayableItem
import com.ckunder.recyclersample2.genericAdapter.controller.ViewHolder
import com.ckunder.recyclersample2.genericAdapter.controller.ViewHolderController
import kotlinx.android.synthetic.main.item_text.view.*
import javax.inject.Inject

class TextViewHolderController @Inject constructor() : ViewHolderController<TextViewEntity>() {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        return ViewHolder(view)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>) {
        val entity = item.model as TextViewEntity
        viewHolder.itemView.tv_text.text = entity.text
    }

    override fun areItemsTheSame(entity1: TextViewEntity, entity2: TextViewEntity): Boolean = entity1 == entity2

    override fun areContentsTheSame(entity1: TextViewEntity, entity2: TextViewEntity): Boolean = true

    override fun getChangePayload(oldEntity: TextViewEntity, newEntity: TextViewEntity): Any? = null
}
