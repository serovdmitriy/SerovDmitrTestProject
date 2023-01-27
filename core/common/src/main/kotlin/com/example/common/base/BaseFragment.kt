package com.example.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.common.extension.getString
import com.example.common.extension.observe
import com.example.common.utils.Text
import com.example.navigation.extension.navigateSafe
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

abstract class BaseFragment<VM : BaseViewModel>(
    @LayoutRes layoutId: Int,
) : Fragment(layoutId) {

    protected abstract val viewModel: VM

    init {
        Timber.d("${javaClass.name} init")
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Timber.d("${javaClass.name} onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()
        onObserveData()
    }

    @CallSuper
    open fun onInitView() {
        Timber.d("${javaClass.name} onInitView")
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.d("${javaClass.name} onResume")
    }

    @CallSuper
    override fun onPause() {
        Timber.d("${javaClass.name} onPause")
        super.onPause()
    }

    @CallSuper
    override fun onDestroyView() {
        Timber.d("${javaClass.name} onDestroyView")
        super.onDestroyView()
    }

    @CallSuper
    override fun onDestroy() {
        Timber.d("${javaClass.name} onDestroy")
        super.onDestroy()
    }

    @CallSuper
    open fun onObserveData() {
        viewModel.events.observe(viewLifecycleOwner) { handleEvent(it) }
        viewModel.progressStateFlow.observe(viewLifecycleOwner) { isShow ->
            (requireActivity() as? ProgressProvider)?.isVisibility(isShow)
        }
    }

    @CallSuper
    protected open fun handleEvent(event: Event) {
        when (event) {
            is NavigationEvent -> handleNavigationEvent(event)
            is ShowError -> showError(event.message)
        }
    }

    private fun getNavController(@IdRes hostId: Int?): NavController {
        return if (hostId == null) {
            findNavController()
        } else {
            Navigation.findNavController(requireActivity(), hostId)
        }
    }

    private fun handleNavigationEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.ToDirection -> getNavController(event.hostId).navigateSafe(
                event.direction,
                event.navOptions
            )
            is NavigationEvent.ToRes -> getNavController(event.hostId).navigateSafe(
                event.resId,
                event.args,
                event.navOptions
            )
            is NavigationEvent.Back -> if (!findNavController().popBackStack()) requireActivity().finish()
            is NavigationEvent.BackTo -> findNavController().popBackStack(
                event.destinationId,
                event.inclusive
            )
        }
    }

    protected open fun showError(errorMessage: Text) {
        Snackbar.make(
            requireView(),
            requireContext().getString(errorMessage),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
