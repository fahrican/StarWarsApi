package com.example.starwarsapi.service

import com.example.starwarsapi.networking.StarWarsApi
import com.example.starwarsapi.di.DaggerApiComponent
import com.example.starwarsapi.model.SpeciesResult
import io.reactivex.Single
import javax.inject.Inject

class RetrofitClient {

    companion object {
        val BASE_URL = "https://swapi.co/"
    }

    @Inject
    lateinit var starWarsApi: StarWarsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun loadSpeciesResult(): Single<SpeciesResult> {
        return starWarsApi.fetchSpeciesResult()
    }
}