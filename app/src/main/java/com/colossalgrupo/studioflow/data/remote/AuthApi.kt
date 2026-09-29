package com.colossalgrupo.studioflow.data.remote

import com.colossalgrupo.studioflow.data.remote.dto.AuthResponseDto
import com.colossalgrupo.studioflow.data.remote.dto.LoginRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequestDto): AuthResponseDto
}
