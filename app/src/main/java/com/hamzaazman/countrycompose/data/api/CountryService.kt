package com.hamzaazman.countrycompose.data.api

import com.hamzaazman.countrycompose.data.model.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("all")
    suspend fun getAllCountry(): List<CountryDto>

    //https://restcountries.com/v3.1/name/{name}?fullText=true
    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") countryName: String
    ): List<CountryDto>


}