package com.example.starwarsapi.networking

import com.example.starwarsapi.model.SpeciesResult
import io.reactivex.Single
import retrofit2.http.GET

interface StarWarsApi {

    @GET("api/species")
    fun fetchSpeciesResult(): Single<SpeciesResult>
}