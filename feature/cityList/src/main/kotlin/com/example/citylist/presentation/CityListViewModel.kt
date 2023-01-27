package com.example.citylist.presentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.citylist.adapter.CityPagingSource
import com.example.citylist.domain.repository.CityListRepository
import com.example.common.base.BaseViewModel
import com.example.common.utils.Text
import com.example.database.dao.CityDao
import com.example.model.domain.city.CoordModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CityListViewModel @Inject constructor(
    private val repository: CityListRepository,
    private val dao: CityDao
) :
    BaseViewModel() {

    val cityName = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val data = cityName.debounce(400).distinctUntilChanged().flatMapLatest { cityName ->
        Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 40
            ),
        ) {
            CityPagingSource(
                cityName = cityName,
                repository = repository,
                dao = dao
            ) { progress ->
                progressMutableStateFlow.value = progress
            }
        }.flow
    }.flowOn(Dispatchers.IO)

    fun navigateToWeatherDetails(coordModel: CoordModel?) {
        coordModel?.let { coord ->
            navigateTo(CityListFragmentDirections.openWeatherDetails(coord))
        } ?: run {
            showError(Text.Str("no coordinates"))
        }
    }
}
