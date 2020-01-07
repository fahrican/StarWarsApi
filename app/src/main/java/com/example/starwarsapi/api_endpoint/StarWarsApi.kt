package com.example.starwarsapi.api_endpoint

import com.example.starwarsapi.model.SpeciesResult
import io.reactivex.Single
import retrofit2.http.GET

interface StarWarsApi {

    @GET("api/species")
    fun fetchSpeciesResult(): Single<SpeciesResult>
}