package com.colossalgrupo.studioflow.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val email: String,
    val senha: String
)

@Serializable
enum class TipoPerfilDto {
    EMPREENDEDOR,
    CLIENTE
}

@Serializable
data class AuthResponseDto(
    val token: String,
    val tipoPerfil: TipoPerfilDto,
    val nome: String,
    val email: String,
    val planoPreferido: String? = null
)

@Serializable
data class ErrorResponseDto(
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val fieldErrors: Map<String, String>? = null
)
