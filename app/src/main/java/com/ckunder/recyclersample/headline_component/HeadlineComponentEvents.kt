package com.ckunder.recyclersample.headline_component

import com.ckunder.recyclersample.Event

sealed class HeadlineComponentEvents : Event {

    object OnItemClick : HeadlineComponentEvents()
}