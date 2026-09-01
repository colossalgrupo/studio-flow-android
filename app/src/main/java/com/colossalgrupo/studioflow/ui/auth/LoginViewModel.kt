package com.colossalgrupo.studioflow.ui.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Holds only local form state. There is no authentication backend yet —
 * any non-blank email/password is accepted so the navigation flow can be
 * exercised end to end.
 */
class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    val canSubmit: Boolean
        get() = email.isNotBlank() && password.isNotBlank()

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }
}
