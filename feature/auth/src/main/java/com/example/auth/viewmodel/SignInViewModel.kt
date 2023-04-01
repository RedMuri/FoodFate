package com.example.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.AuthError
import com.example.auth.AuthProgress
import com.example.auth.AuthState
import com.example.auth.AuthSuccess
import com.example.data.exceptions.UserAlreadyExistsException
import com.example.domain.model.User
import com.example.domain.use_cases.SignInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private var _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun signIn(name: String, phone: String, password: String) {
        val fieldsValid = validateInputSignIn(name, phone, password)
        if (fieldsValid) {
            _authState.value = AuthProgress
            val user = User(name, phone, password)
            viewModelScope.launch {
                try {
                    signInUseCase.invoke(user) {
                        _authState.value = AuthSuccess
                    }
                } catch (e: UserAlreadyExistsException) {
                    _authState.value = AuthError(ERROR_SUCH_USER_EXISTS)
                }
            }
        }
    }

    private fun validateInputSignIn(name: String, phone: String, password: String): Boolean {
        if (name.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_NAME)
            return false
        }
        if (phone.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_PHONE)
            return false
        }
        if (password.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_PASSWORD)
            return false
        }
        return true
    }

    companion object {

        const val ERROR_EMPTY_NAME = 1
        const val ERROR_EMPTY_PHONE = 2
        const val ERROR_EMPTY_PASSWORD = 3
        const val ERROR_SUCH_USER_EXISTS = 4
    }
}