package com.example.starwarsapi.data.networking

import com.example.starwarsapi.di.DaggerApiComponent
import com.example.starwarsapi.data.model.SpeciesResult
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