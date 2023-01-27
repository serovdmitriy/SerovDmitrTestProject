package com.example.common.extension

import android.content.Context
import android.text.Editable
import android.widget.TextView
import com.example.common.utils.Text

val Int.text: Text
    get() = Text.Res(this)

val String.text: Text
    get() = Text.Str(this)

fun Editable?.orEmpty(): String = this?.toString() ?: ""

fun Int?.textOrEmpty(): Text = if (this != null) Text.Res(this) else Text.Str("")

fun Int.text(vararg args: Text): Text = Text.TextParams(this, args.toList())

fun String?.textOrEmpty(): Text = Text.Str(this.orEmpty())

@SuppressWarnings("SpreadOperator")
fun Context.getString(text: Text): String {
    return when (text) {
        is Text.Res -> getString(text.resId)
        is Text.ResParams -> getString(text.value, *text.args.toTypedArray())
        is Text.Str -> text.text
        is Text.TextParams -> getString(text.value, *text.args.map(::getString).toTypedArray())
    }
}

fun TextView.setText(resource: Text) {
    text = context.getString(resource)
}
