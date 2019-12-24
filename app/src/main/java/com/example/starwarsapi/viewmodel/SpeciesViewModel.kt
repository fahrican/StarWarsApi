package com.example.starwarsapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.service.StarWarsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SpeciesViewModel : ViewModel() {

    private val starWarsService by lazy { StarWarsService() }
    private val disposable by lazy { CompositeDisposable() }

    val species = MutableLiveData<List<Species>>()
    val loadingError = MutableLiveData<Boolean>()
    val loadingProcess = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        fetchSpecies()
    }

    private fun fetchSpecies() {
        loadingProcess.value = false

        disposable.add(
            starWarsService.loadSpecies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Species>>() {

                    override fun onSuccess(value: List<Species>?) {
                        species.value = value
                        loadingError.value = false
                        loadingProcess.value = false

                    }

                    override fun onError(e: Throwable?) {
                        loadingProcess.value = false
                        loadingError.value = true
                    }
                }
                )
        )
    }
}