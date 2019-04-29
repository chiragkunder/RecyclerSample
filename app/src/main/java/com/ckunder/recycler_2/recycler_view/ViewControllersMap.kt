package com.ckunder.recycler_2.recycler_view

import com.ckunder.recycler_2.recycler_view.adl.ViewEntity
import kotlin.reflect.KClass

typealias ViewControllersMap = Map<KClass<out ViewEntity>, @JvmSuppressWildcards ViewController<ViewEntity>>