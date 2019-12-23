package com.example.starwarsapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsapi.model.Species

class SpeciesViewModel : ViewModel() {

    val species = MutableLiveData<List<Species>>()
    val loadingError = MutableLiveData<Boolean>()
    val loadingProcess = MutableLiveData<Boolean>()

    fun refresh() {
        fetchSpecies()
    }

    private fun fetchSpecies() {
        //Todo: implement logic
        loadingError.value = false
        loadingProcess.value = false
        //Todo: assign species value
    }
}