package com.hamzaazman.countrycompose.data.mapper

import com.hamzaazman.countrycompose.data.model.CountryDto
import com.hamzaazman.countrycompose.domain.mapper.CountryMapper
import com.hamzaazman.countrycompose.domain.model.CountryDomainModel
import javax.inject.Inject

class CountryMapperImpl @Inject constructor(): CountryMapper<CountryDto, CountryDomainModel> {
    override fun map(input: CountryDto?): CountryDomainModel {
        return CountryDomainModel(
            capital = input?.capital ?: emptyList(),
            capitalInfo = input?.capitalInfo,
            coatOfArms = input?.coatOfArms,
            currencies = input?.currencies,
            flags = input?.flags,
            languages = input?.languages,
            latlng = input?.latlng?: emptyList(),
            maps = input?.maps,
            name = input?.name,
            population = input?.population,
            region = input?.region,
            startOfWeek = input?.startOfWeek,
            subregion = input?.subregion,
            timezones = input?.timezones ?: emptyList()
        )
    }
}