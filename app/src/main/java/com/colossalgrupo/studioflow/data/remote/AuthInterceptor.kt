package com.colossalgrupo.studioflow.data.remote

import com.colossalgrupo.studioflow.data.local.SecureSessionStorage
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Anexa o token Bearer da sessão salva em toda requisição. O backend ignora o
 * cabeçalho em endpoints públicos (ex.: /api/auth/login), então não há
 * necessidade de excluir rotas aqui.
 */
class AuthInterceptor(
    private val sessionStorage: SecureSessionStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sessionStorage.get()?.token
        val request = chain.request().let { original ->
            if (token != null) {
                original.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                original
            }
        }

        val response = chain.proceed(request)

        // Token expirado/inválido em uma chamada autenticada: derruba a sessão local
        // para forçar novo login na próxima verificação.
        if (response.code == 401 && token != null) {
            sessionStorage.clear()
        }

        return response
    }
}
