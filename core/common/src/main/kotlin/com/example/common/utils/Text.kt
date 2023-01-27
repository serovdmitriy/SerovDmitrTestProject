package com.example.common.utils

import androidx.annotation.StringRes

sealed class Text {
    data class Res(@StringRes val resId: Int) : Text()
    data class ResParams(@StringRes val value: Int, val args: List<Any>) : Text()
    data class TextParams(@StringRes val value: Int, val args: List<Text>) : Text()
    data class Str(val text: String) : Text()
}
