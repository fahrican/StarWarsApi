package com.example.starwarsapi.data.networking

import com.example.starwarsapi.data.model.SpeciesResult
import io.reactivex.Single
import retrofit2.http.GET

interface StarWarsApi {

    @GET("api/species")
    fun fetchSpeciesResult(): Single<SpeciesResult>
}