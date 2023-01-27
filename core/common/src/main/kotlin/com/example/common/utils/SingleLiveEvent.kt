package com.example.common.utils

import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 *
 *
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 *
 *
 * Note that only one observer is going to be notified of changes.
 */
class SingleLiveEvent<T> : LiveData<T> {

    private val pending: AtomicBoolean

    constructor() : super() {
        pending = AtomicBoolean(false)
    }

    constructor(value: T) : super(value) {
        pending = AtomicBoolean(true)
    }

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Timber.tag("SingleLiveEvent")
                .w("Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner) {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }
    }

    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call(value: T) {
        setValue(value)
    }

    @AnyThread
    fun postCall(value: T) {
        postValue(value)
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun SingleLiveEvent<Unit>.call() = call(Unit)

@Suppress("NOTHING_TO_INLINE")
inline fun SingleLiveEvent<Unit>.postCall() = postCall(Unit)
