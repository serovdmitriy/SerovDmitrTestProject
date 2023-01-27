package com.example.citylist.presentation

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.citylist.R
import com.example.citylist.adapter.CityAdapter
import com.example.citylist.databinding.CityListFragmentBinding
import com.example.citylist.di.CityListComponent
import com.example.common.base.BaseFragment
import com.example.common.extension.orEmpty
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

internal class CityListFragment :
    BaseFragment<CityListViewModel>(R.layout.city_list_fragment),
    IHasComponent<CityListComponent> {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    override val viewModel: CityListViewModel by viewModels { viewModelFactory.get() }

    private val binding by viewBinding(CityListFragmentBinding::bind)

    private val adapter by lazy { CityAdapter(viewModel::navigateToWeatherDetails) }

    override fun getComponent() = CityListComponent.Initializer.init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.bindComponent(this).inject(this)
    }

    override fun onInitView() {
        super.onInitView()
        binding.recyclerView.adapter = adapter

        binding.search.doAfterTextChanged { editable ->
            viewModel.cityName.value = editable.orEmpty()
        }
    }

    override fun onObserveData() {
        super.onObserveData()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.data.collectLatest { list ->
                adapter.submitData(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
    }
}
