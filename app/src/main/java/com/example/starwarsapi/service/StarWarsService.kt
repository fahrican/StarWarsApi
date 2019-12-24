package com.example.starwarsapi.service

import com.example.starwarsapi.api_endpoint.StarWarsApi
import com.example.starwarsapi.model.SpeciesResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class StarWarsService {

    private val BASE_URL = "https://swapi.co/"
    private val starWarsApi: StarWarsApi

    init {
        starWarsApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }

    fun loadSpecies(): Observable<SpeciesResult> {
        return starWarsApi.getSpecies()
    }
}