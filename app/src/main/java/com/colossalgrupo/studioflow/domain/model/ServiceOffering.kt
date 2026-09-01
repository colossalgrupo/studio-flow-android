package com.colossalgrupo.studioflow.domain.model

data class ServiceOffering(
    val id: String,
    val name: String,
    val durationMinutes: Int,
    val basePrice: Double
)
