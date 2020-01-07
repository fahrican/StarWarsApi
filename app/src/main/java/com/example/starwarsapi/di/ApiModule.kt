package com.example.starwarsapi.di

import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.adapter.SpeciesAdapter
import com.example.starwarsapi.api_endpoint.StarWarsApi
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.service.NetworkService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://swapi.co/"

    @Provides
    fun provideStarWarsApi(): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }

    @Provides
    fun provideStarWarsService(): NetworkService {
        return NetworkService()
    }

    @Provides
    fun provideBooleanLiveData(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }

    @Provides
    fun provideSpeciesListLiveData(): MutableLiveData<List<Species>> {
        return MutableLiveData()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSpeciesList(): ArrayList<Species> {
        return ArrayList()
    }

    @Provides
    fun provideSpeciesAdapter(speciesList: ArrayList<Species>): SpeciesAdapter {
        return SpeciesAdapter(speciesList)
    }
}