package com.experiment.android.loginsample.data.model

/**
 * Data validation state of the login form.
 */
data class LoginModel(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false,
    val userName: String? = null
)