package com.example.task18.di

import com.example.task18.data.repository.PersonRepositoryImpl
import com.example.task18.domain.PersonsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindPersonRepository(repositoryImpl: PersonRepositoryImpl): PersonsRepository
}