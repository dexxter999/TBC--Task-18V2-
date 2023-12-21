package com.example.task18.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.task18.domain.PersonsList
import com.example.task18.domain.PersonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PersonsRepository) : ViewModel() {

    private val _viewState =
        MutableStateFlow(MainViewState())
    val viewState get() = _viewState.asStateFlow()

    init {
        onEvent(MainEvent.GetPersons)
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetPersons -> getPersons()
        }
    }


    private fun getPersons() = viewModelScope.launch {
        _viewState.update { it.copy(isLoading = true) }

        repository.getPersons().cachedIn(viewModelScope).collect { pagingData ->
            _viewState.update { it.copy(personsList = pagingData, isLoading = false) }
        }
    }
}


data class MainViewState(
    val personsList: PagingData<PersonsList.Person> = PagingData.empty(),
    val error: Exception? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

sealed interface MainEvent {
    data object GetPersons : MainEvent
}