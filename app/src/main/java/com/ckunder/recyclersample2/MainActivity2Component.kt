package com.ckunder.recyclersample2

import dagger.Component

@Component(modules = [MainActivity2Module::class])
interface MainActivity2Component {

    fun inject(activity: MainActivity2)
}