package com.example.starwarsapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.model.Species
import kotlinx.android.synthetic.main.item_species.view.*

class SpeciesAdapter(
    private var speciesList: ArrayList<Species>
) : RecyclerView.Adapter<SpeciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_species, parent, false)
        return SpeciesViewHolder(itemView)
    }

    override fun getItemCount(): Int = speciesList.size

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(speciesList[position])
        holder.speciesCardView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Name: ${speciesList[position].name}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun setSpeciesList(listOfSpecies: List<Species>) {
        speciesList.clear()
        speciesList.addAll(listOfSpecies)
        notifyDataSetChanged()
    }
}

class SpeciesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val speciesCardView: CardView by lazy { view.card_view }
    private val speciesName: TextView by lazy { view.species_name }
    private val speciesAVGLifespan: TextView by lazy { view.species_average_lifespan }
    private val speciesLanguage: TextView by lazy { view.species_language }

    fun bind(species: Species) {
        speciesName.text = species.name
        speciesAVGLifespan.text = species.average_lifespan
        speciesLanguage.text = species.language
    }
}