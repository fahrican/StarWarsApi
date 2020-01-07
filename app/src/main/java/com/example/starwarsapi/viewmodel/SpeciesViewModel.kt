package com.example.starwarsapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.adapter.SpeciesAdapter
import com.example.starwarsapi.di.DaggerApiComponent
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
    lateinit var speciesListMLD: MutableLiveData<List<Species>>
    @Inject
    lateinit var isErrorMLD: MutableLiveData<Boolean>
    @Inject
    lateinit var inProgressMLD: MutableLiveData<Boolean>

    init {
        Log.i(TAG, "init")
        DaggerApiComponent.create().inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        makeSpeciesWebCall()
    }

    private fun makeSpeciesWebCall() {
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