package com.example.pokerapp.model

data class EvolvesTo(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<EvolvesToX>,
    val is_baby: Boolean,
    val species: SpeciesX
)