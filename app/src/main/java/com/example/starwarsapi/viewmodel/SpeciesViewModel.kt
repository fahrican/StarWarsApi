package com.example.starwarsapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.di.DaggerStarWarsApiComponent
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.service.StarWarsService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpeciesViewModel : ViewModel() {

    private val TAG by lazy { "SpeciesViewModel" }

    @Inject
    lateinit var starWarsService: StarWarsService

    private val disposable by lazy { CompositeDisposable() }
    private val listOfSpecies by lazy { ArrayList<Species>() }

    val species = MutableLiveData<List<Species>>()
    val loadingError = MutableLiveData<Boolean>()
    val loadingProcess = MutableLiveData<Boolean>()

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
        loadingProcess.value = true

        disposable.add(
            starWarsService.loadSpecies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it.results) }
                .subscribeWith(createSpeciesObserver())
        )
    }

    private fun createSpeciesObserver(): DisposableObserver<Species> {
        return object : DisposableObserver<Species>() {

            override fun onNext(value: Species) {
                if (!listOfSpecies.contains(value)) {
                    listOfSpecies.add(value)
                }
            }

            override fun onComplete() {
                species.value = listOfSpecies
                loadingProcess.value = false
                loadingError.value = false
                Log.v("onComplete", "Success list of: ${listOfSpecies.size}")
            }

            override fun onError(e: Throwable) {
                loadingProcess.value = false
                loadingError.value = true
                Log.e("onError", "Species error: ${e.message}")
            }
        }
    }
}