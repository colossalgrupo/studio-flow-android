package com.colossalgrupo.studioflow.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.colossalgrupo.studioflow.domain.model.AuthSession
import com.colossalgrupo.studioflow.domain.model.UserRole

/**
 * Persiste a sessão autenticada (token JWT + dados do usuário) em SharedPreferences
 * cifradas com uma chave gerenciada pelo Android Keystore.
 */
class SecureSessionStorage(context: Context) {

    private val masterKey = MasterKey.Builder(context.applicationContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val preferences = EncryptedSharedPreferences.create(
        context.applicationContext,
        PREFERENCES_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun save(session: AuthSession) {
        preferences.edit()
            .putString(KEY_TOKEN, session.token)
            .putString(KEY_ROLE, session.role.name)
            .putString(KEY_NOME, session.nome)
            .putString(KEY_EMAIL, session.email)
            .apply()
    }

    fun get(): AuthSession? {
        val token = preferences.getString(KEY_TOKEN, null) ?: return null
        val role = preferences.getString(KEY_ROLE, null)?.let { runCatching { UserRole.valueOf(it) }.getOrNull() }
            ?: return null
        val nome = preferences.getString(KEY_NOME, null) ?: return null
        val email = preferences.getString(KEY_EMAIL, null) ?: return null
        return AuthSession(token = token, role = role, nome = nome, email = email)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    private companion object {
        const val PREFERENCES_NAME = "studio_schedule_secure_session"
        const val KEY_TOKEN = "token"
        const val KEY_ROLE = "role"
        const val KEY_NOME = "nome"
        const val KEY_EMAIL = "email"
    }
}
