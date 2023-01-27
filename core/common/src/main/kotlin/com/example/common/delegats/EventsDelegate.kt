package com.example.common.delegats

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.common.base.Event
import com.example.common.utils.SingleLiveEvent
import com.example.common.utils.Text

interface EventsDelegate {
    val events: SingleLiveEvent<Event>

    fun showError(text: Text)

    fun navigateTo(
        direction: NavDirections,
        @IdRes hostId: Int? = null,
        navOptions: NavOptions? = null
    )
    fun navigateTo(
        @IdRes resId: Int,
        args: Bundle? = null,
        @IdRes hostId: Int? = null,
        navOptions: NavOptions? = null
    )
    fun navigateBack()
    fun navigateBackTo(@IdRes resId: Int, inclusive: Boolean = false)
}
