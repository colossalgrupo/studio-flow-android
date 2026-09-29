package com.colossalgrupo.studioflow.domain.repository

import com.colossalgrupo.studioflow.domain.model.AuthSession

sealed class LoginResult {
    data class Success(val session: AuthSession) : LoginResult()
    data class InvalidCredentials(val message: String) : LoginResult()
    data class EmailNotVerified(val message: String) : LoginResult()
    data class ValidationError(val message: String) : LoginResult()
    // Este app é só para o cliente final — uma conta de Empreendedor autentica
    // normalmente no backend, mas não tem o que fazer aqui.
    data class WrongProfile(val message: String) : LoginResult()
    data object NetworkError : LoginResult()
    data class UnknownError(val message: String) : LoginResult()
}

interface AuthRepository {
    suspend fun login(email: String, senha: String): LoginResult
    fun currentSession(): AuthSession?
    fun logout()
}
