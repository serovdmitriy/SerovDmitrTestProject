package com.example.app.di

import androidx.lifecycle.ViewModel
import com.example.app.presentation.MainViewModel
import com.example.common.di.ViewModelBuilderModule
import com.example.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelBuilderModule::class])
internal abstract class AppBindModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
