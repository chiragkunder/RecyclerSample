package com.ckunder.recyclersample.two_line_delegate

import com.ckunder.recyclersample.Event

sealed class HeadlineComponentEvents : Event {

    object OnItemClick : HeadlineComponentEvents()
}