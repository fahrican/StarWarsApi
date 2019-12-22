package com.example.starwarsapi.api_endpoint

import com.example.starwarsapi.model.Species
import io.reactivex.Single
import retrofit2.http.GET

interface StarWarsApi {

    @GET("api/species/?format=json")
    fun getSpecies(): Single<List<Species>>
}