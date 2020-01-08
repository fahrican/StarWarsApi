package com.example.starwarsapi.di

import com.example.starwarsapi.adapter.SpeciesViewHolder
import com.example.starwarsapi.repository.SpeciesRepository
import com.example.starwarsapi.service.NetworkService
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: NetworkService)

    fun inject(speciesRepository: SpeciesRepository)

    fun inject(speciesViewModel: SpeciesViewModel)

    fun inject(speciesViewHolder: SpeciesViewHolder)
}