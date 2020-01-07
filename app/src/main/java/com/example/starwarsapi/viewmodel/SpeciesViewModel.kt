package com.example.starwarsapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.adapter.SpeciesAdapter
import com.example.starwarsapi.di.DaggerStarWarsApiComponent
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SpeciesViewModel : ViewModel() {

    private val TAG by lazy { "SpeciesViewModel" }

    @Inject
    lateinit var speciesAdapter: SpeciesAdapter
    @Inject
    lateinit var networkService: NetworkService
    @Inject
    lateinit var disposable: CompositeDisposable
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
        makeSpeciesWebCall()
    }

    private fun makeSpeciesWebCall() {
        progressLiveData.value = true

        disposable.add(
            networkService.loadSpeciesResult()
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
                Log.v("onComplete", "Success list of: ${speciesList.size}")
            }
        }
    }
}