package com.colossalgrupo.studioflow.domain.repository

import com.colossalgrupo.studioflow.domain.model.AuthSession

sealed class LoginResult {
    data class Success(val session: AuthSession) : LoginResult()
    data class InvalidCredentials(val message: String) : LoginResult()
    data class EmailNotVerified(val message: String) : LoginResult()
    data class ValidationError(val message: String) : LoginResult()
    data object NetworkError : LoginResult()
    data class UnknownError(val message: String) : LoginResult()
}

interface AuthRepository {
    suspend fun login(email: String, senha: String): LoginResult
    fun currentSession(): AuthSession?
    fun logout()
}
