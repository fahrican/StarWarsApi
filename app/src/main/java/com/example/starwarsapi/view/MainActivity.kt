package com.example.starwarsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.R
import com.example.starwarsapi.adapter.SpeciesAdapter
import com.example.starwarsapi.viewmodel.SpeciesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: SpeciesViewModel
    private val speciesAdapter by lazy { SpeciesAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(SpeciesViewModel::class.java)
        viewModel.refresh()

        main_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = speciesAdapter
        }
    }
}
