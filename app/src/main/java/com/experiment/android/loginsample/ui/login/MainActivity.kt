package com.experiment.android.loginsample.ui.login

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.experiment.android.loginsample.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extras = intent.extras ?: return
        val userName = extras.getString(getString(R.string.user_name_key))

        val userNameTv = findViewById<TextView>(R.id.tv_userName)
        userNameTv.text = "Welcome $userName"

        val logoutTv = findViewById<TextView>(R.id.tv_logout)
        logoutTv.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        finish()
    }
}