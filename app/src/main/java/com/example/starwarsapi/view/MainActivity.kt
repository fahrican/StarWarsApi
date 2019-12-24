package com.example.starwarsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.R
import com.example.starwarsapi.adapter.SpeciesAdapter
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG by lazy { "MainActivity" }
    lateinit var viewModel: SpeciesViewModel
    private val speciesAdapter by lazy { SpeciesAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate() start")

        viewModel = ViewModelProviders.of(this).get(SpeciesViewModel::class.java)
        viewModel.refresh()

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = speciesAdapter
        }

        main_swipe_refresh_layout.setOnRefreshListener {
            main_swipe_refresh_layout.isRefreshing = false
            viewModel.refresh()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume() start")
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.species.observe(this, Observer { allSpecies ->
            allSpecies.let {
                main_recycler_view.visibility = View.VISIBLE
                speciesAdapter.setSpeciesList(it)
            }
        })

        viewModel.loadingError.observe(this, Observer { isError ->
            isError.let { species_list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loadingProcess.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    species_list_progress.visibility = View.VISIBLE
                    species_list_error.visibility = View.GONE
                    main_recycler_view.visibility = View.GONE
                } else {
                    species_list_progress.visibility = View.GONE
                }
            }
        })
    }
}
