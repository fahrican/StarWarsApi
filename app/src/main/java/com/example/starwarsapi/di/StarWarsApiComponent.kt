package com.example.starwarsapi.di

import com.example.starwarsapi.service.StarWarsService
import dagger.Component

@Component(modules = [StarWarsApiModule::class])
interface StarWarsApiComponent {

    fun inject(service: StarWarsService)
}