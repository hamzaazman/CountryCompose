package com.hamzaazman.countrycompose.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzaazman.countrycompose.domain.model.CountryDomainModel
import com.hamzaazman.countrycompose.domain.usecase.GetCountryByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCountryByNameUseCase: GetCountryByNameUseCase
) : ViewModel() {

    private val _detailCountryState: MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> get() = _detailCountryState.asStateFlow()


    fun getCountryByName(countryName: String) = viewModelScope.launch {
        getCountryByNameUseCase.invoke(countryName)
            .flowOn(Dispatchers.IO)
            .onEach { result ->
                result.onSuccess { data ->
                    _detailCountryState.value = DetailUiState.Success(data = data.first())
                }
                result.onFailure { exception ->
                    _detailCountryState.value = DetailUiState.Error(message = exception)
                }
            }
            .collect()
    }

}

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val data: CountryDomainModel) : DetailUiState()
    data class Error(val message: Throwable?) : DetailUiState()
}