package com.hamzaazman.countrycompose.data.mapper

import com.hamzaazman.countrycompose.data.model.CountryDto
import com.hamzaazman.countrycompose.domain.mapper.CountryListMapper
import com.hamzaazman.countrycompose.domain.model.CountryDomainModel
import javax.inject.Inject

class CountryListMapperImpl @Inject constructor() :
    CountryListMapper<CountryDto, CountryDomainModel> {
    override fun map(input: List<CountryDto>?): List<CountryDomainModel> {
        return input?.map {
            CountryDomainModel(
                capital = it.capital ?: emptyList(),
                capitalInfo = it.capitalInfo,
                coatOfArms = it.coatOfArms,
                currencies = it.currencies,
                flags = it.flags,
                languages = it.languages,
                latlng = it.latlng?: emptyList(),
                maps = it.maps,
                name = it.name,
                population = it.population,
                region = it.region,
                startOfWeek = it.startOfWeek,
                subregion = it.subregion,
                timezones = it.timezones ?: emptyList()
            )
        } ?: emptyList()
    }
}