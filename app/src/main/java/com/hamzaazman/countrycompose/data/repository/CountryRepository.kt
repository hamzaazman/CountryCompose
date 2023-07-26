package com.hamzaazman.countrycompose.data.repository

import com.hamzaazman.countrycompose.data.model.CountryDto
import com.hamzaazman.countrycompose.data.source.remote.CountryRemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class CountryRepository @Inject constructor(
    private val remoteDataSource: CountryRemoteDataSource
) {
    suspend fun getAllCountry(): Result<List<CountryDto>> {
        return remoteDataSource.getAllCountry()
    }

    suspend fun getCountryByName(countryName: String): Result<List<CountryDto>> {
        return remoteDataSource.getCountryByName(countryName = countryName)
    }
}