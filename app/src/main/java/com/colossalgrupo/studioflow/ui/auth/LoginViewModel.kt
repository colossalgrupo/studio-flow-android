package com.colossalgrupo.studioflow.ui.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.initializer
import com.colossalgrupo.studioflow.domain.model.AuthSession
import com.colossalgrupo.studioflow.domain.repository.AuthRepository
import com.colossalgrupo.studioflow.domain.repository.LoginResult
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    val canSubmit: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isLoading

    fun onEmailChange(value: String) {
        email = value
        errorMessage = null
    }

    fun onPasswordChange(value: String) {
        password = value
        errorMessage = null
    }

    fun login(onSuccess: (AuthSession) -> Unit) {
        if (!canSubmit) return

        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            when (val result = authRepository.login(email, password)) {
                is LoginResult.Success -> onSuccess(result.session)
                is LoginResult.InvalidCredentials -> errorMessage = result.message
                is LoginResult.EmailNotVerified -> errorMessage = result.message
                is LoginResult.ValidationError -> errorMessage = result.message
                is LoginResult.NetworkError ->
                    errorMessage = "Não foi possível conectar. Verifique sua internet e tente novamente."
                is LoginResult.UnknownError -> errorMessage = result.message
            }
            isLoading = false
        }
    }

    companion object {
        fun factory(authRepository: AuthRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer { LoginViewModel(authRepository) }
        }
    }
}
