package com.hamzaazman.countrycompose.domain.mapper

interface CountryMapper<I, O> {
    fun map(input: I?): O
}