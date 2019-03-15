package com.ckunder.recyclersample.textview_delegate

import com.ckunder.recyclersample.Identifiable

data class TextViewEntity(override val id: Long, val title: String) : Identifiable