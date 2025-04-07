package com.example.tfgsportpro.features.f00_Auth.ForgotPassword.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityForgotPasswordBinding
import com.example.tfgsportpro.features.f00_Auth.Login.UI.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.bComeBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.bRecover.setOnClickListener {
            val email = binding.tietRecoverPassword.text.toString()
            if (email.isNotEmpty()) {
                recoverPassword(email)
            } else {
                showSnackbar(getString(R.string.introduceEmail))
            }
        }
    }

    private fun recoverPassword(email: String) {
        if (email.isNotEmpty()) {
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showSnackbar(getString(R.string.emailSent))
                    } else {
                        showSnackbar(getString(R.string.emailCouldNotSent))
                    }
                }
        } else {
            showSnackbar(getString(R.string.introduceEmail))
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}