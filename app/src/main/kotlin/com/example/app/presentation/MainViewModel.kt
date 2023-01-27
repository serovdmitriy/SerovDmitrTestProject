package com.example.app.presentation

import androidx.lifecycle.ViewModel
import com.example.common.navigation.Router
import javax.inject.Inject

internal class MainViewModel @Inject constructor(router: Router) : ViewModel() {

    val navigateEvent = router.event
}
