package com.example.task18.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PersonsRepository {
    fun getPersons(): Flow<PagingData<PersonsList.Person>>
}