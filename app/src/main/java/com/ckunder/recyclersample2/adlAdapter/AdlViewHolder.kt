package com.ckunder.recyclersample2.adlAdapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

data class AdlViewHolder(val view: View, @LayoutRes val layoutId: Int) : RecyclerView.ViewHolder(view)
