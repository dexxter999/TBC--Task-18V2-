package com.example.task18.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.task18.data.network.PersonsService
import com.example.task18.data.paging.PersonPagingSource
import com.example.task18.domain.PersonsList
import com.example.task18.domain.PersonsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(private val personsService: PersonsService) :
    PersonsRepository {

    override fun getPersons(): Flow<PagingData<PersonsList.Person>> {
        return Pager(PagingConfig(pageSize = 5)) {
            PersonPagingSource(personsService)
        }.flow.map { pagingData ->
            pagingData.map { personDto ->
                personDto.toPerson()
            }
        }
    }
}