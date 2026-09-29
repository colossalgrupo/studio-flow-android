package com.colossalgrupo.studioflow.data.remote

import com.colossalgrupo.studioflow.BuildConfig
import com.colossalgrupo.studioflow.data.local.SecureSessionStorage
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    private val json = Json { ignoreUnknownKeys = true }

    fun createAuthApi(sessionStorage: SecureSessionStorage): AuthApi =
        createRetrofit(sessionStorage).create(AuthApi::class.java)

    private fun createRetrofit(sessionStorage: SecureSessionStorage): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(createOkHttpClient(sessionStorage))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    private fun createOkHttpClient(sessionStorage: SecureSessionStorage): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(sessionStorage))
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                }
            }
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
}
