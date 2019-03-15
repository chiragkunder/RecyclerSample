package com.ckunder.recyclersample.recyler_framework

import android.view.View
import com.ckunder.recyclersample.ADLViewEntity

abstract class AdapterDelegateForView<VIEW : View, Entity : ADLViewEntity> : AdapterDelegate<VIEW, Entity>() {

    override fun unBind(view: VIEW) {}
}