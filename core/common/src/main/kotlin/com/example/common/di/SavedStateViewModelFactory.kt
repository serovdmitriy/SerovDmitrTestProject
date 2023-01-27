package com.example.common.di

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner

interface SavedStateViewModelFactory<T : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): T
}

inline fun <reified VM : ViewModel> SavedStateRegistryOwner.createSavedStateViewModelFactory(
    arguments: Bundle?,
    crossinline creator: (SavedStateHandle) -> VM,
): ViewModelProvider.Factory = object : AbstractSavedStateViewModelFactory(this, arguments) {
    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(
        key: String,
        modelClass: Class<VM>,
        handle: SavedStateHandle,
    ): VM = creator(handle) as VM
}
