package com.n26.feeds_presentation.adl.recycler_view.actions

import android.view.View
import com.n26.feeds_presentation.adl.ViewEntity

/**
 * [com.n26.feeds_presentation.adl.recycler_view.ViewController]'s can implement this interface
 * if the item view needs to handle clicks on the item view itself or individual child views.
 */
interface ItemViewActionsProvider<T : ViewEntity> {

    fun provideViewActions(itemView: View): List<ViewAction<T>>
}