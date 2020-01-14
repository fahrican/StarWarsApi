package com.example.starwarsapi.di

import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.view.adapter.SpeciesAdapter
import com.example.starwarsapi.networking.StarWarsApi
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.repository.SpeciesRepository
import com.example.starwarsapi.networking.RetrofitClient
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideStarWarsApi(): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
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

    @Provides
    fun provideSpeciesRepository(): SpeciesRepository {
        return SpeciesRepository()
    }
}