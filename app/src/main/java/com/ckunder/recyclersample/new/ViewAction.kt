package com.n26.feeds_presentation.adl.recycler_view.actions

import android.view.View
import com.n26.feeds_presentation.adl.ViewEntity

data class ViewAction<T : ViewEntity>(
    val view: View,
    val action: (T) -> Unit
)