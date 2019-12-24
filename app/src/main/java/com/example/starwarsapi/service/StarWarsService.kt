package com.example.starwarsapi.service

import com.example.starwarsapi.api_endpoint.StarWarsApi
import com.example.starwarsapi.di.DaggerStarWarsApiComponent
import com.example.starwarsapi.model.SpeciesResult
import io.reactivex.Observable
import javax.inject.Inject

class StarWarsService {

    @Inject
    lateinit var starWarsApi: StarWarsApi

    init {
        DaggerStarWarsApiComponent.create().inject(this)
    }

    fun loadSpecies(): Observable<SpeciesResult> {
        return starWarsApi.getSpecies()
    }
}