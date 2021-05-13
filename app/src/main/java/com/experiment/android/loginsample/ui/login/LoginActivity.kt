package com.experiment.android.loginsample.ui.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.experiment.android.loginsample.R
import com.experiment.android.loginsample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        initViews()
        initObservables()
    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val userName = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        binding.etUsername.clearFocus()
        binding.etPassword.clearFocus()

        loginViewModel.performLogin(userName, password)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun initObservables() {
        loginViewModel.progressBarState.observe(this, Observer {
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        })

        loginViewModel.loginDataState.observe(this, {
            val loginDataState = it!!
            when {
                loginDataState.usernameError != null -> {
                    showAlert(getString(loginDataState.usernameError))
                    binding.etUsername.requestFocus()
                }
                loginDataState.passwordError != null -> {
                    showAlert(getString(loginDataState.passwordError))
                    binding.etPassword.requestFocus()
                }
                loginDataState.isDataValid -> {
                    goToNextScreen(loginDataState.userName)
                }
            }
        })
    }

    private fun goToNextScreen(userName: String?) {
        clearFields()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(getString(R.string.user_name_key), userName)
        startActivity(intent)
    }

    private fun clearFields() {
        binding.etPassword.text.clear()
        binding.etUsername.text.clear()
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    // User clicked OK button
                })
            .setCancelable(false)
            .create()
            .show()
    }
}