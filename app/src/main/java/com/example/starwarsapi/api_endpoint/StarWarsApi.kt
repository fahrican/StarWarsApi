package com.example.starwarsapi.api_endpoint

import com.example.starwarsapi.model.SpeciesResult
import io.reactivex.Observable
import retrofit2.http.GET

interface StarWarsApi {

    @GET("api/species")
    fun getSpecies(): Observable<SpeciesResult>
}