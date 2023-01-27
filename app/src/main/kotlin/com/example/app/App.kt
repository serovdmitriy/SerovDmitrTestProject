package com.example.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.app.di.AppComponent
import com.example.common.BuildConfig
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber

class App : Application(), IHasComponent<AppComponent> {

    override fun getComponent() = AppComponent.Initializer.init(this)

    override fun onCreate() {
        super.onCreate()
        setUpDI()
        setUpLightMode()
        setUpTimber()
    }

    /**
     * Dagger Injection manager - helps with dependencies and share them between modules
     * more information: https://proandroiddev.com/lifecycle-aware-dagger-components-8c74d01fa15
     */
    private fun setUpDI() {
        XInjectionManager.init(this)
        XInjectionManager
            .bindComponent(this)
            .inject(this@App)
    }

    private fun setUpLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
