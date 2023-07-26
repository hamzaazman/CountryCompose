package com.hamzaazman.countrycompose.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzaazman.countrycompose.common.Resource
import com.hamzaazman.countrycompose.domain.model.CountryDomainModel
import com.hamzaazman.countrycompose.domain.usecase.GetAllCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getAllCountryUseCase: GetAllCountryUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    init {
        getAllCountry()
    }

    private fun getAllCountry() {
        getAllCountryUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.value = HomeUiState.Loading
                }

                is Resource.Error -> {
                    _uiState.value = HomeUiState.Error(result.exception)
                }

                is Resource.Success -> {
                    _uiState.value = HomeUiState.Success(result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<CountryDomainModel> = emptyList()) : HomeUiState()
    data class Error(val message: Throwable?) : HomeUiState()
}
