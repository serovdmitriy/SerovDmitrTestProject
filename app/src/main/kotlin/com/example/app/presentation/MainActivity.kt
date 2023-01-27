package com.example.app.presentation

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.app.R
import com.example.app.di.AppComponent
import com.example.common.base.NavigationEvent
import com.example.common.base.ProgressProvider
import com.example.navigation.extension.NavigateR
import com.example.navigation.extension.navigateSafe
import dagger.Lazy
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

class MainActivity :
    AppCompatActivity(R.layout.activity_main),
    ProgressProvider {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>
    private val viewModel by viewModels<MainViewModel> { viewModelFactory.get() }

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDi()
        setUpNavHost()
        setObserveEvents()
    }

    private fun setUpDi() {
        XInjectionManager
            .findComponent<AppComponent>()
            .inject(this)
    }

    private fun setUpNavHost() {
        val navHost = supportFragmentManager.findFragmentById(NavigateR.navRootHost) as NavHostFragment
        navController = navHost.navController
    }

    private fun setObserveEvents() {
        viewModel.navigateEvent.observe(this@MainActivity) { handleNavigationEvent(it) }
    }

    override fun isVisibility(visible: Boolean) {
        findViewById<ProgressBar>(R.id.progress).isGone = !visible
    }

    private fun handleNavigationEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.ToDirection -> navController?.navigateSafe(event.direction, event.navOptions)
            is NavigationEvent.ToRes -> navController?.navigateSafe(event.resId, event.args, event.navOptions)
            is NavigationEvent.Back -> navController?.popBackStack()
            is NavigationEvent.BackTo -> navController?.popBackStack(event.destinationId, event.inclusive)
        }
    }
}
