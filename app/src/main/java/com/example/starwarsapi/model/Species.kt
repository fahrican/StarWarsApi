package com.example.starwarsapi.model

import javax.inject.Inject

data class Species @Inject constructor(
    val average_height: String,
    val average_lifespan: String,
    val classification: String,
    val created: String,
    val designation: String,
    val edited: String,
    val eye_colors: String,
    val films: List<String>,
    val hair_colors: String,
    val homeworld: String,
    val language: String,
    val name: String,
    val people: List<String>,
    val skin_colors: String,
    val url: String
)