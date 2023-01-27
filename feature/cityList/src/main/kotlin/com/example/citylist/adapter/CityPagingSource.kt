package com.example.citylist.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.citylist.domain.repository.CityListRepository
import com.example.database.dao.CityDao
import com.example.database.model.CityEntity

class CityPagingSource(
    private val cityName: String,
    private val repository: CityListRepository,
    private val dao: CityDao,
    private val progress: (Boolean) -> Unit
) : PagingSource<Int, CityEntity>() {

    @SuppressWarnings("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CityEntity> {
        val page = params.key ?: 0
        progress(true)
        repository.loadingCityList()
        return try {
            val entities = dao.getPagedListWithSearch(cityName, params.loadSize, page * params.loadSize)

            progress(false)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CityEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
