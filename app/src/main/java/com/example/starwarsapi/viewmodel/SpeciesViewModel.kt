package com.example.starwarsapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.di.DaggerApiComponent
import com.example.starwarsapi.data.repository.SpeciesRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SpeciesViewModel : ViewModel() {

    private val TAG by lazy { "SpeciesViewModel" }

    @Inject
    lateinit var disposable: CompositeDisposable
    @Inject
    lateinit var speciesRepository: SpeciesRepository

    init {
        Log.i(TAG, "init")
        DaggerApiComponent.create().inject(this)
        getSpeciesFromRepo()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        getSpeciesFromRepo()
    }

    private fun getSpeciesFromRepo() {
        disposable.add(speciesRepository.makeSpeciesWebCall())
    }
}