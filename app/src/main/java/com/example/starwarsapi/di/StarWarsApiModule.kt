package com.example.starwarsapi.di

import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.api_endpoint.StarWarsApi
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.service.StarWarsService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class StarWarsApiModule {

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
    fun provideStarWarsService(): StarWarsService {
        return StarWarsService()
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
    fun provideListOfSpecies(): ArrayList<Species> {
        return ArrayList()
    }


}