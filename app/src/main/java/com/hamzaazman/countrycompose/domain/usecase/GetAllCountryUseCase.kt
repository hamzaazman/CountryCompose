package com.hamzaazman.countrycompose.domain.usecase

import com.hamzaazman.countrycompose.common.Resource
import com.hamzaazman.countrycompose.data.model.CountryDto
import com.hamzaazman.countrycompose.data.repository.CountryRepository
import com.hamzaazman.countrycompose.domain.mapper.CountryListMapper
import com.hamzaazman.countrycompose.domain.model.CountryDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCountryUseCase @Inject constructor(
    private val repository: CountryRepository,
    private val countryMapper: CountryListMapper<CountryDto, CountryDomainModel>
) {
    operator fun invoke(): Flow<Resource<List<CountryDomainModel>>> = flow {
        emit(Resource.Loading)
        repository.getAllCountry()
            .onFailure { exception ->
                emit(Resource.Error(exception = exception))
            }
            .onSuccess { result ->
                emit(
                    Resource.Success(
                        data = countryMapper.map(
                            input = result
                        )
                    )
                )
            }
    }
}