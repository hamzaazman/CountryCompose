package com.hamzaazman.countrycompose.di

import com.hamzaazman.countrycompose.data.model.CountryDto
import com.hamzaazman.countrycompose.domain.mapper.CountryListMapper
import com.hamzaazman.countrycompose.data.mapper.CountryListMapperImpl
import com.hamzaazman.countrycompose.domain.mapper.CountryMapper
import com.hamzaazman.countrycompose.data.mapper.CountryMapperImpl
import com.hamzaazman.countrycompose.domain.model.CountryDomainModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindCountryListMapper(countryListMapperImpl: CountryListMapperImpl): CountryListMapper<CountryDto, CountryDomainModel>

    @Binds
    @Singleton
    abstract fun bindCountryMapper(countryMapperImpl: CountryMapperImpl): CountryMapper<CountryDto, CountryDomainModel>
}