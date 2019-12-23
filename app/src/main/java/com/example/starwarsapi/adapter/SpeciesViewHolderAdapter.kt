package com.example.starwarsapi.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_species.view.*

class SpeciesAdapter() : RecyclerView.Adapter<SpeciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

class SpeciesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val speciesCardView: CardView by lazy { view.card_view }
    val speciesName: TextView by lazy { view.species_name }
    val speciesAVGLifespan: TextView by lazy { view.species_average_lifespan }
    val speciesLanguage: TextView by lazy { view.species_language }
}