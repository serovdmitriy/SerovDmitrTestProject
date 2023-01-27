package com.example.common.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.common.utils.Text

interface Event

sealed class NavigationEvent : Event {
    data class ToDirection(
        val direction: NavDirections,
        val navOptions: NavOptions? = null,
        @IdRes val hostId: Int? = null
    ) : NavigationEvent()

    data class ToRes(
        @IdRes val resId: Int,
        val args: Bundle? = null,
        val navOptions: NavOptions? = null,
        @IdRes val hostId: Int? = null
    ) : NavigationEvent()

    class Back : NavigationEvent()

    data class BackTo(val destinationId: Int, val inclusive: Boolean) : NavigationEvent()
}

data class ShowError(val message: Text) : Event
