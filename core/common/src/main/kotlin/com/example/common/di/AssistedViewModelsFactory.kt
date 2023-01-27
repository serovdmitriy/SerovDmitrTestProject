package com.example.common.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

// AssistedInject puts all assisted bindings in the same module.
// We need to make a decision about where to install it.
// In this case, as we only need it in fragments, we install it there.
// source: https://gist.github.com/manuelvicnt/437668cda3a891d347e134b1de29aee1
// @AssistedModule
// @Module(includes = [AssistedInject_AssistedInjectModule::class])
// Needed until AssistedInject is incorporated into Dagger
// interface AssistedInjectModule

inline fun <reified T : ViewModel> Fragment.assistedViewModels(
    owner: ViewModelStoreOwner = this,
    crossinline body: () -> T
): Lazy<T> {
    return viewModels({ owner }) {
        object : ViewModelProvider.AndroidViewModelFactory(requireActivity().application) {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = body() as T
        }
    }
}
