package com.example.starwarsapi.model

data class Species(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Any>
)