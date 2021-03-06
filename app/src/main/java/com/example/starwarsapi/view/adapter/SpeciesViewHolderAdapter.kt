package com.example.starwarsapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.databinding.ItemSpeciesBinding
import com.example.starwarsapi.data.model.Species

class SpeciesAdapter(
    private var speciesList: ArrayList<Species>
) : RecyclerView.Adapter<SpeciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val itemSpeciesBinding: ItemSpeciesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_species,
            parent,
            false
        )
        return SpeciesViewHolder(itemSpeciesBinding)
    }

    override fun getItemCount(): Int = speciesList.size

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val currentSpecies = speciesList[position]
        holder.itemSpeciesBinding.species = currentSpecies
        holder.itemSpeciesBinding.cardView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Name: ${currentSpecies.name}",
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

class SpeciesViewHolder(val itemSpeciesBinding: ItemSpeciesBinding) :
    RecyclerView.ViewHolder(itemSpeciesBinding.root)