package com.example.app.presentation.router

import com.example.common.base.NavigationEvent
import com.example.common.navigation.Router
import com.example.common.utils.SingleLiveEvent

class MainRouter : Router {
    override val event = SingleLiveEvent<NavigationEvent>()
}
