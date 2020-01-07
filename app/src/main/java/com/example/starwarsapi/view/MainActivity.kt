package com.example.starwarsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.R
import com.example.starwarsapi.model.Species
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG by lazy { "MainActivity" }
    private lateinit var viewModel: SpeciesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate() start")

        viewModel = ViewModelProviders.of(this).get(SpeciesViewModel::class.java)
        viewModel.refresh()

        setUpViews()

        observeLiveData()
    }

    private fun setUpViews() {
        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModel.speciesAdapter
        }

        main_swipe_refresh_layout.setOnRefreshListener {
            main_swipe_refresh_layout.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observeSpeciesList()
    }

    private fun observeInProgress() {
        val inProgressLD: LiveData<Boolean> = viewModel.speciesRepository.inProgressMLD
        inProgressLD.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    species_list_error.visibility = View.GONE
                    main_recycler_view.visibility = View.GONE
                    species_list_progress.visibility = View.VISIBLE
                } else {
                    species_list_progress.visibility = View.GONE
                }
            }
        })
    }

    private fun observeIsError() {
        val isErrorLD: LiveData<Boolean> = viewModel.speciesRepository.isErrorMLD
        isErrorLD.observe(this, Observer { isError ->
            isError.let { species_list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
    }

    private fun observeSpeciesList() {
        val speciesListLD: LiveData<List<Species>> = viewModel.speciesRepository.speciesListMLD
        speciesListLD.observe(this, Observer { allSpecies ->
            allSpecies.let {
                main_recycler_view.visibility = View.VISIBLE
                viewModel.speciesAdapter.setSpeciesList(it)
            }
        })
    }
}
