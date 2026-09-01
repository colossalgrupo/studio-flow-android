package com.colossalgrupo.studioflow.domain.model

data class Establishment(
    val id: String,
    val name: String,
    val category: ServiceCategory,
    val city: String,
    val rating: Double,
    val professionalName: String,
    val services: List<ServiceOffering>
)
