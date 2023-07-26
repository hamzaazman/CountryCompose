package com.hamzaazman.countrycompose.domain.model

import com.hamzaazman.countrycompose.data.model.CapitalInfo
import com.hamzaazman.countrycompose.data.model.CoatOfArms
import com.hamzaazman.countrycompose.data.model.Currencies
import com.hamzaazman.countrycompose.data.model.Flags
import com.hamzaazman.countrycompose.data.model.Languages
import com.hamzaazman.countrycompose.data.model.Maps
import com.hamzaazman.countrycompose.data.model.Name

data class CountryDomainModel(
    val capital: List<String>?,
    val capitalInfo: CapitalInfo?,
    val coatOfArms: CoatOfArms?,
    val currencies: Currencies?,
    val flags: Flags?,
    val languages: Languages?,
    val latlng: List<Double>?,
    val maps: Maps?,
    val name: Name?,
    val population: Int?,
    val region: String?,
    val startOfWeek: String?,
    val subregion: String?,
    val timezones: List<String>?,
)