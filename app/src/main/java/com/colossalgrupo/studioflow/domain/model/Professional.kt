package com.colossalgrupo.studioflow.domain.model

data class Professional(
    val id: String,
    val name: String,
    val cpf: String,
    val specialties: List<String>,
    val payoutKey: String,
    val payoutPeriodicity: PayoutPeriodicity,
    val commissionPercent: Double
)
