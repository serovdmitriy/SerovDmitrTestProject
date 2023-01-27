package com.example.app.di

import android.app.Application
import com.example.app.App
import com.example.app.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppBindModule::class])
interface AppComponent : AppDependencies {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: App)
    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {
            fun init(app: App): AppComponent = DaggerAppComponent
                .builder().application(app).build()
        }
    }
}
