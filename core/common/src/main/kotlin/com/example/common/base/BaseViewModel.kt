package com.example.common.base

import androidx.lifecycle.ViewModel
import com.example.common.delegats.EventsDelegate
import com.example.common.delegats.impl.EventsDelegateImpl
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel(events: EventsDelegate = EventsDelegateImpl()) : ViewModel(), EventsDelegate by events {

    init {
        Timber.d("${javaClass.name} init")
    }

    protected val progressMutableStateFlow = MutableStateFlow(false)
    val progressStateFlow = progressMutableStateFlow.asStateFlow()

    fun CoroutineScope.launchWithProgress(
        progress: MutableStateFlow<Boolean> = progressMutableStateFlow,
        block: suspend () -> Unit,
    ): Job {
        return launch {
            try {
                progress.value = true
                block.invoke()
                progress.value = false
            } catch (exception: CancellationException) {
                Timber.e(exception)
                progress.value = false
            } finally {
                progress.value = false
            }
        }
    }
}
