package com.ckunder.recyclersample2

import com.ckunder.recyclersample.R
import com.ckunder.recyclersample2.adlAdapter.AdlItemComparator
import com.ckunder.recyclersample2.adlAdapter.controller.AdlViewHolderController
import com.ckunder.recyclersample2.adlAdapter.controller.TwoLineAdlViewHolderController
import com.ckunder.recyclersample2.genericAdapter.ItemComparator
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class MainActivity2Module {

    @IntoMap
    @IntKey(R.layout.two_line_view_component)
    @Binds
    abstract fun provideTwoLineAdlViewHolderController(controller: TwoLineAdlViewHolderController)
            : AdlViewHolderController<*>

    @Binds
    abstract fun adlItemComparator(comparator: AdlItemComparator): ItemComparator
}
