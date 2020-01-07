package com.example.starwarsapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.adapter.SpeciesAdapter
import com.example.starwarsapi.di.DaggerApiComponent
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.repository.SpeciesRepository
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
    lateinit var disposable: CompositeDisposable
    @Inject
    lateinit var speciesRepository: SpeciesRepository

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
        disposable.add(speciesRepository.callSpecies())
    }
}