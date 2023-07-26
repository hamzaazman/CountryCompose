package com.hamzaazman.countrycompose.domain.usecase

import com.hamzaazman.countrycompose.data.model.CountryDto
import com.hamzaazman.countrycompose.data.repository.CountryRepository
import com.hamzaazman.countrycompose.domain.mapper.CountryListMapper
import com.hamzaazman.countrycompose.domain.model.CountryDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryByNameUseCase @Inject constructor(
    private val repository: CountryRepository,
    private val mapper: CountryListMapper<CountryDto, CountryDomainModel>
) {
    operator fun invoke(countryName: String): Flow<Result<List<CountryDomainModel>>> = flow {
        repository.getCountryByName(countryName = countryName)
            .onFailure { exception ->
                emit(Result.failure(exception))
            }
            .onSuccess { result ->
                emit(
                    Result.success(
                        value = mapper.map(input = result)
                    )
                )
            }
    }
}