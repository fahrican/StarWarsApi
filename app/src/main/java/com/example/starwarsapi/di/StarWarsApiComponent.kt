package com.example.starwarsapi.di

import com.example.starwarsapi.service.NetworkService
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import dagger.Component

@Component(modules = [StarWarsApiModule::class])
interface StarWarsApiComponent {

    fun inject(service: NetworkService)

    fun inject(speciesViewModel: SpeciesViewModel)
}