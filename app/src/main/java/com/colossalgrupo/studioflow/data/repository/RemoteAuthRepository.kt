package com.colossalgrupo.studioflow.data.repository

import com.colossalgrupo.studioflow.data.local.SecureSessionStorage
import com.colossalgrupo.studioflow.data.remote.AuthApi
import com.colossalgrupo.studioflow.data.remote.dto.ErrorResponseDto
import com.colossalgrupo.studioflow.data.remote.dto.LoginRequestDto
import com.colossalgrupo.studioflow.data.remote.dto.TipoPerfilDto
import com.colossalgrupo.studioflow.domain.model.AuthSession
import com.colossalgrupo.studioflow.domain.model.UserRole
import com.colossalgrupo.studioflow.domain.repository.AuthRepository
import com.colossalgrupo.studioflow.domain.repository.LoginResult
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class RemoteAuthRepository(
    private val authApi: AuthApi,
    private val sessionStorage: SecureSessionStorage
) : AuthRepository {

    private val errorJson = Json { ignoreUnknownKeys = true }

    override suspend fun login(email: String, senha: String): LoginResult {
        return try {
            val response = authApi.login(LoginRequestDto(email = email, senha = senha))
            val session = AuthSession(
                token = response.token,
                role = response.tipoPerfil.toDomain(),
                nome = response.nome,
                email = response.email
            )
            sessionStorage.save(session)
            LoginResult.Success(session)
        } catch (e: HttpException) {
            e.toLoginResult()
        } catch (e: IOException) {
            LoginResult.NetworkError
        }
    }

    override fun currentSession(): AuthSession? = sessionStorage.get()

    override fun logout() = sessionStorage.clear()

    private fun HttpException.toLoginResult(): LoginResult {
        val error = parseErrorBody(response()?.errorBody())
        val message = error?.message
        return when (code()) {
            401 -> LoginResult.InvalidCredentials(message ?: "E-mail ou senha inválidos")
            403 -> LoginResult.EmailNotVerified(message ?: "Verifique seu e-mail antes de fazer login")
            400 -> LoginResult.ValidationError(message ?: "Dados inválidos")
            else -> LoginResult.UnknownError(message ?: "Algo deu errado. Tente novamente.")
        }
    }

    private fun parseErrorBody(errorBody: ResponseBody?): ErrorResponseDto? {
        val raw = errorBody?.string() ?: return null
        return runCatching { errorJson.decodeFromString<ErrorResponseDto>(raw) }.getOrNull()
    }

    private fun TipoPerfilDto.toDomain(): UserRole = when (this) {
        TipoPerfilDto.EMPREENDEDOR -> UserRole.ENTREPRENEUR
        TipoPerfilDto.CLIENTE -> UserRole.CLIENT
    }
}
