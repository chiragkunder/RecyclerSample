package com.ckunder.recyclersample2

import com.ckunder.recyclersample2.adl.CardAdlViewComponent
import com.ckunder.recyclersample2.adl.TwoLineAdlViewComponent
import com.ckunder.recyclersample2.adlAdapter.AdlItemComparator
import com.ckunder.recyclersample2.adlAdapter.controller.AdlViewHolderController
import com.ckunder.recyclersample2.adlAdapter.controller.CardAdlViewHolderController
import com.ckunder.recyclersample2.adlAdapter.controller.TwoLineAdlViewHolderController
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class MainActivity2Module {

    @IntoMap
    @IntKey(TwoLineAdlViewComponent.layoutId)
    @Binds
    abstract fun provideTwoLineAdlViewHolderController(controller: TwoLineAdlViewHolderController)
            : AdlViewHolderController<*>

    @IntoMap
    @IntKey(CardAdlViewComponent.layoutId)
    @Binds
    abstract fun provideCardAdlViewHolderController(controller: CardAdlViewHolderController)
            : AdlViewHolderController<*>

    @Binds
    abstract fun adlItemComparator(comparator: AdlItemComparator): ItemComparator
}
