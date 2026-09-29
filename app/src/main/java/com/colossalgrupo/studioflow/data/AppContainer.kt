package com.colossalgrupo.studioflow.data

import android.content.Context
import com.colossalgrupo.studioflow.data.local.SecureSessionStorage
import com.colossalgrupo.studioflow.data.remote.NetworkModule
import com.colossalgrupo.studioflow.data.repository.RemoteAuthRepository
import com.colossalgrupo.studioflow.domain.repository.AuthRepository

/** Raiz de composição manual do app — instanciada uma vez em [com.colossalgrupo.studioflow.StudioScheduleApplication]. */
class AppContainer(context: Context) {

    private val sessionStorage = SecureSessionStorage(context.applicationContext)
    private val authApi = NetworkModule.createAuthApi(sessionStorage)

    val authRepository: AuthRepository = RemoteAuthRepository(authApi, sessionStorage)
}
