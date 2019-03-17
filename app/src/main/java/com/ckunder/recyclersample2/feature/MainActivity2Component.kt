package com.ckunder.recyclersample2.feature

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainActivity2ListModule::class])
interface MainActivity2Component {

    fun inject(activity: MainActivity2)
}