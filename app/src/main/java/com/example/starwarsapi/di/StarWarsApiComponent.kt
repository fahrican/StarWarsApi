package com.example.starwarsapi.di

import com.example.starwarsapi.service.StarWarsService
import com.example.starwarsapi.view.MainActivity
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import dagger.Component

@Component(modules = [StarWarsApiModule::class])
interface StarWarsApiComponent {

    fun inject(service: StarWarsService)

    fun inject(speciesViewModel: SpeciesViewModel)

    fun inject(mainActivity: MainActivity)
}