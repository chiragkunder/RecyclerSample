package com.ckunder.recyclersample2.adl

interface AdlViewComponent<T : AdlViewEntity> {

    fun getLayoutId(): Int

    fun setViewEntity(viewEntity: T)
}
