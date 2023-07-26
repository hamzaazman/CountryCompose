package com.hamzaazman.countrycompose.data.source.remote

import com.hamzaazman.countrycompose.data.api.CountryService
import com.hamzaazman.countrycompose.data.model.CountryDto
import javax.inject.Inject

class CountryRemoteDataSource @Inject constructor(
    private val service: CountryService
) {

    suspend fun getAllCountry(): Result<List<CountryDto>> {
        return try {
            val response = service.getAllCountry()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCountryByName(countryName: String): Result<List<CountryDto>> {
        return try {
            val response = service.getCountryByName(countryName = countryName)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



}
