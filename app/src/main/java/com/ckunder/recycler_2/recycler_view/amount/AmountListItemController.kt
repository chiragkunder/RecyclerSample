package com.ckunder.recycler_2.recycler_view.amount

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recycler_2.recycler_view.UniqueId
import com.ckunder.recycler_2.recycler_view.ViewController

class AmountListItemController(
    private val viewType: Int = UniqueId.getInt()
) : ViewController<TwoLineADLViewEntity> {

    override fun getItemType(): Int = viewType

    override fun inflateView(parent: ViewGroup): View =
        AmountListItemView(parent.context).apply {
            rootView.layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }

    override fun bindView(itemView: View, viewEntity: TwoLineADLViewEntity, payloads: MutableList<Any>) {
        (itemView as AmountListItemView).setViewEntity(viewEntity)
    }
}