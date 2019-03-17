package com.ckunder.recyclersample2

import androidx.recyclerview.widget.RecyclerView
import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.adl.CardAdlViewComponent
import com.ckunder.recyclersample2.adl.TwoLineAdlViewComponent
import com.ckunder.recyclersample2.adlAdapter.controller.CardAdlViewHolderController
import com.ckunder.recyclersample2.adlAdapter.controller.TwoLineAdlViewHolderController
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import com.ckunder.recyclersample2.genericAdapter.RecyclerViewAdapter
import com.ckunder.recyclersample2.genericAdapter.controller.DefaultItemComparator
import com.ckunder.recyclersample2.genericAdapter.controller.ViewHolderController
import com.ckunder.recyclersample2.genericAdapter.controller.group.GROUP_VIEW_TYPE
import com.ckunder.recyclersample2.genericAdapter.controller.group.GroupViewHolderController
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class AdapterModule {

    @IntoMap
    @IntKey(TwoLineAdlViewComponent.layoutId)
    @Binds
    @Singleton
    abstract fun provideTwoLineAdlViewHolderController(controller: TwoLineAdlViewHolderController)
            : ViewHolderController<*>

    @IntoMap
    @IntKey(CardAdlViewComponent.layoutId)
    @Binds
    @Singleton
    abstract fun provideCardAdlViewHolderController(controller: CardAdlViewHolderController)
            : ViewHolderController<*>

    @IntoMap
    @IntKey(GROUP_VIEW_TYPE)
    @Binds
    @Singleton
    abstract fun provideGroupViewHolderController(controller: GroupViewHolderController)
            : ViewHolderController<*>

    @IntoMap
    @IntKey(R.layout.item_text)
    @Binds
    @Singleton
    abstract fun provideTextViewHolderController(controller: TextViewHolderController)
            : ViewHolderController<*>

    @Binds
    @Singleton
    abstract fun adlItemComparator(comparator: DefaultItemComparator): ItemComparator
}

@Module(includes = [AdapterModule::class])
object MainActivity2ListModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideAdapter(
        controllerMap: Map<Int, @JvmSuppressWildcards ViewHolderController<*>>,
        comparator: ItemComparator
    ) = RecyclerViewAdapter(comparator, controllerMap, controllerMap)

    @JvmStatic
    @Provides
    @Singleton
    fun provideViewPool(): RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
}
