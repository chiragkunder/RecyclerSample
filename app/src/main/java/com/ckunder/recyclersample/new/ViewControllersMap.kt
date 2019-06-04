package com.ckunder.recyclersample.new

import com.n26.feeds_presentation.adl.ViewEntity
import kotlin.reflect.KClass

internal typealias ViewControllersMap = Map<KClass<out ViewEntity>, @JvmSuppressWildcards ViewController<ViewEntity>>