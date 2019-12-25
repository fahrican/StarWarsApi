package com.example.starwarsapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.di.DaggerStarWarsApiComponent
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.service.StarWarsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpeciesViewModel : ViewModel() {

    private val TAG by lazy { "SpeciesViewModel" }

    @Inject
    lateinit var starWarsService: StarWarsService
    @Inject
    lateinit var disposable: CompositeDisposable
    @Inject
    lateinit var listOfSpecies: ArrayList<Species>
    @Inject
    lateinit var speciesLiveData: MutableLiveData<List<Species>>
    @Inject
    lateinit var errorLiveData: MutableLiveData<Boolean>
    @Inject
    lateinit var progressLiveData: MutableLiveData<Boolean>

    init {
        Log.i(TAG, "init")
        DaggerStarWarsApiComponent.create().inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        fetchSpecies()
    }

    private fun fetchSpecies() {
        progressLiveData.value = true

        disposable.add(
            starWarsService.loadSpecies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.results }
                .subscribeWith(createSpeciesObserver())
        )
    }

    private fun createSpeciesObserver(): DisposableSingleObserver<List<Species>> {
        return object : DisposableSingleObserver<List<Species>>() {

            override fun onError(e: Throwable) {
                progressLiveData.value = false
                errorLiveData.value = true
                Log.e("onError", "Species error: ${e.message}")
            }

            override fun onSuccess(speciesList: List<Species>) {
                speciesLiveData.value = speciesList
                progressLiveData.value = false
                errorLiveData.value = false
                Log.v("onComplete", "Success list of: ${listOfSpecies.size}")
            }
        }
    }
}