package com.ckunder.recyclersample.recyler_framework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.ckunder.recyclersample.Identifiable

abstract class AdapterDelegateForXML<Entity : Identifiable> : AdapterDelegate<View, Entity>() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun getItemType(): Int = getLayoutId()

    override fun createView(parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)

    override fun unBind(view: View) {}
}