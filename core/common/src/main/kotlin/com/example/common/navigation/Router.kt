package com.example.common.navigation

import com.example.common.base.NavigationEvent
import com.example.common.utils.SingleLiveEvent

interface Router {
    val event: SingleLiveEvent<NavigationEvent>
}
