package com.example.starwarsapi.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.di.DaggerApiComponent
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.model.SpeciesResult
import com.example.starwarsapi.service.NetworkService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpeciesRepository {

    @Inject
    lateinit var networkService: NetworkService
    @Inject
    lateinit var speciesListMLD: MutableLiveData<List<Species>>
    @Inject
    lateinit var isErrorMLD: MutableLiveData<Boolean>
    @Inject
    lateinit var inProgressMLD: MutableLiveData<Boolean>

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun callSpecies(): DisposableSingleObserver<List<Species>>  {
       return networkService.loadSpeciesResult().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.results }
            .subscribeWith(createSpeciesObserver())
    }


    private fun createSpeciesObserver(): DisposableSingleObserver<List<Species>> {
        return object : DisposableSingleObserver<List<Species>>() {

            override fun onError(e: Throwable) {
                inProgressMLD.value = true
                isErrorMLD.value = true
                Log.e("onError", "Species error: ${e.message}")
                inProgressMLD.value = false
            }

            override fun onSuccess(speciesList: List<Species>) {
                inProgressMLD.value = true
                speciesListMLD.value = speciesList
                isErrorMLD.value = false
                Log.v("onComplete", "Success list of: ${speciesList.size}")
                inProgressMLD.value = false
            }
        }
    }
}