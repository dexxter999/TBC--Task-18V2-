package com.example.task18.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.task18.data.network.PersonsService
import com.example.task18.data.network.model.PersonsListDto
import retrofit2.HttpException
import java.io.IOException

class PersonPagingSource(private val personsService: PersonsService) :
    PagingSource<Int, PersonsListDto.PersonDto>() {

    override fun getRefreshKey(state: PagingState<Int, PersonsListDto.PersonDto>): Int? {
        return state.anchorPosition.let { anchorPosition ->
            anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
                ?: anchorPosition?.let { state.closestPageToPosition(it)?.nextKey?.minus(1) }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonsListDto.PersonDto> {
        return try {
            val nextPage = params.key ?: 1
            val response = personsService.getPersons(nextPage)
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body()!!.data,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (response.body()!!.data.isEmpty()) null else nextPage + 1
                )
            } else {
                LoadResult.Error(Exception(response.errorBody()?.string()))
            }

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}