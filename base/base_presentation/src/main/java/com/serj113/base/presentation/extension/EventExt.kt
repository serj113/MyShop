package com.serj113.base.presentation.extension

import com.serj113.base.presentation.util.Event

fun Event<Boolean>.isTrue(): Boolean {
    return if (hasBeenHandled) {
        false
    } else {
        val value = getContentIfNotHandled()
        (value != null && value)
    }
}