package com.colossalgrupo.studioflow.domain.model

enum class SubscriptionPlan(
    val displayName: String,
    val monthlyPrice: Double,
    val platformFeePercent: Double,
    val maxProfessionals: Int?
) {
    STANDARD(
        displayName = "Standard",
        monthlyPrice = 49.90,
        platformFeePercent = 5.0,
        maxProfessionals = 3
    ),
    BLACK(
        displayName = "Black",
        monthlyPrice = 89.90,
        platformFeePercent = 2.5,
        maxProfessionals = 10
    ),
    DIAMOND(
        displayName = "Diamond",
        monthlyPrice = 189.90,
        platformFeePercent = 1.5,
        maxProfessionals = null
    );

    val maxProfessionalsLabel: String
        get() = maxProfessionals?.let { "Até $it profissionais" } ?: "Profissionais ilimitados"
}
