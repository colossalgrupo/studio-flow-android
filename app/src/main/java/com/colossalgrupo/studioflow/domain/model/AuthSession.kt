package com.colossalgrupo.studioflow.domain.model

data class AuthSession(
    val token: String,
    val nome: String,
    val email: String
)
