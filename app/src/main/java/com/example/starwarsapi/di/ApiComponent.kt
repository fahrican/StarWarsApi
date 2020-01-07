package com.example.starwarsapi.di

import com.example.starwarsapi.service.NetworkService
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: NetworkService)

    fun inject(speciesViewModel: SpeciesViewModel)
}