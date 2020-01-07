package com.example.starwarsapi.service

import com.example.starwarsapi.api_endpoint.StarWarsApi
import com.example.starwarsapi.di.DaggerStarWarsApiComponent
import com.example.starwarsapi.model.SpeciesResult
import io.reactivex.Single
import javax.inject.Inject

class NetworkService {

    @Inject
    lateinit var starWarsApi: StarWarsApi

    init {
        DaggerStarWarsApiComponent.create().inject(this)
    }

    fun loadSpecies(): Single<SpeciesResult> {
        return starWarsApi.getSpecies()
    }
}