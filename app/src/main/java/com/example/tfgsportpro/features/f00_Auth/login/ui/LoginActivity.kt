package com.example.tfgsportpro.features.f00_Auth.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.credentials.CredentialManager
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityLoginBinding
import com.example.tfgsportpro.features.f00_Auth.forgotPassword.ui.ForgotPasswordActivity
import com.example.tfgsportpro.features.f00_Auth.login.useCase.LoginManager
import com.example.tfgsportpro.features.f00_Auth.register.ui.RegisterActivity
import com.example.tfgsportpro.features.f01_Home.HomeActivity
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean("dark_mode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginManager = LoginManager(this)

        loginManager.checkIfUserLoggedIn { isLoggedIn ->
            if (isLoggedIn) {
                showHomeActivity()
            }
        }

        binding.bRegisterenviar.setOnClickListener {
            binding.bRegisterenviar.isEnabled = false
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.bLogin.setOnClickListener {
            binding.bLogin.isEnabled = false
            val email = binding.tietEmail.text.toString()
            val password = binding.tietPassword.text.toString()

            loginManager.loginWithEmailAndPassword(email, password) { success ->
                if (success) {
                    showHomeActivity()
                } else {
                    binding.bLogin.isEnabled = true
                    val message = getString(R.string.errorloginmessage)
                    showAlert(message)
                }
            }
        }

        binding.bGoogle.setOnClickListener {
            binding.bGoogle.isEnabled = false

            val credentialManager = CredentialManager.create(this)
            val signInOption = GetSignInWithGoogleOption.Builder(getString(R.string.web_client))
                .setNonce("nonce")
                .build()

            loginManager.loginWithGoogle(credentialManager, signInOption) { success ->
                if (success) {
                    showHomeActivity()
                } else {
                    binding.bGoogle.isEnabled = true
                    val message = getString(R.string.errorloginGooglemessage)
                    showAlert(message)
                }
            }
        }

        // Botón de "¿Olvidaste tu contraseña?"
        binding.bForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showAlert(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
