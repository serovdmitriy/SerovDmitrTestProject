package com.example.common.delegats.impl

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.common.base.Event
import com.example.common.base.NavigationEvent
import com.example.common.base.ShowError
import com.example.common.delegats.EventsDelegate
import com.example.common.utils.SingleLiveEvent
import com.example.common.utils.Text
import javax.inject.Inject

class EventsDelegateImpl @Inject constructor() : EventsDelegate {
    override val events = SingleLiveEvent<Event>()

    override fun showError(text: Text) {
        events.call(ShowError(text))
    }

    override fun navigateTo(
        direction: NavDirections,
        @IdRes hostId: Int?,
        navOptions: NavOptions?,
    ) = events.call(NavigationEvent.ToDirection(direction, navOptions, hostId))

    override fun navigateTo(
        @IdRes resId: Int,
        args: Bundle?,
        @IdRes hostId: Int?,
        navOptions: NavOptions?,
    ) = events.call(NavigationEvent.ToRes(resId, args, navOptions, hostId))

    override fun navigateBack() = events.call(NavigationEvent.Back())

    override fun navigateBackTo(resId: Int, inclusive: Boolean) = events.call(NavigationEvent.BackTo(resId, inclusive))
}
