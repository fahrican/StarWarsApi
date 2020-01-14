package com.example.starwarsapi.data.model

data class SpeciesResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Species>
)