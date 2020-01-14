package com.example.starwarsapi.di

import com.example.starwarsapi.data.repository.SpeciesRepository
import com.example.starwarsapi.data.networking.RetrofitClient
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(client: RetrofitClient)

    fun inject(speciesRepository: SpeciesRepository)

    fun inject(speciesViewModel: SpeciesViewModel)
}