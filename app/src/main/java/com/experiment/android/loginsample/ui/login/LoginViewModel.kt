package com.experiment.android.loginsample.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.experiment.android.loginsample.R
import com.experiment.android.loginsample.data.model.LoginModel

class LoginViewModel() : ViewModel() {

    val progressBarState = MutableLiveData<Boolean>()

    private val _loginDataState = MutableLiveData<LoginModel>()
    val loginDataState: LiveData<LoginModel>
        get() = _loginDataState

    init {
        progressBarState.value = false
    }

    fun performLogin(userName: String, password: String) {
        progressBarState.value = true
        performValidation(userName, password)
    }

    private fun performValidation(userName: String, password: String) {

        when {
            userName.isBlank() -> {
                _loginDataState.value =
                    LoginModel(usernameError = R.string.username_empty)
                progressBarState.value = false
            }
            password.isBlank() -> {
                _loginDataState.value =
                    LoginModel(passwordError = R.string.password_empty)
                progressBarState.value = false
            }
            password != ("password") -> {
                _loginDataState.value = LoginModel(passwordError = R.string.password_mismatch)
                progressBarState.value = false
            }
            else -> {
                _loginDataState.value = LoginModel(isDataValid = true, userName = userName)
                progressBarState.value = false
            }
        }

    }

}