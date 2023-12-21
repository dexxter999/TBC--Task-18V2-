package com.example.task18.data.network

import com.example.task18.core.helper.Constants
import com.example.task18.data.network.model.PersonsListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonsService {
    @GET(Constants.END_POINT)
    suspend fun getPersons(@Query("page") page: Int): Response<PersonsListDto>
}