package com.example.tfgsportpro.features.f00_Auth.Login.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.CredentialManager
import com.example.tfgsportpro.R
import com.example.tfgsportpro.features.f01_Home.HomeActivity
import com.example.tfgsportpro.features.f00_Auth.Register.UI.RegisterActivity
import com.example.tfgsportpro.features.f00_Auth.Login.UseCase.LoginManager
import com.example.tfgsportpro.databinding.ActivityLoginBinding
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var loginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar el LoginManager
        loginManager = LoginManager(this)

        // Verificar si el usuario ya está autenticado
        loginManager.checkIfUserLoggedIn { isLoggedIn ->
            if (isLoggedIn) {
                showHomeActivity()
            }
        }

        binding.bRegisterenviar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.bLogin.setOnClickListener {
            val email = binding.tietEmail.text.toString()
            val password = binding.tietPassword.text.toString()
            loginManager.loginWithEmailAndPassword(email, password) { success ->
                if (success) {
                    showHomeActivity()
                } else {
                    val message = getString(R.string.errorloginmessage)
                    showAlert(message)
                }
            }
        }


        binding.bGoogle.setOnClickListener {
            val credentialManager = CredentialManager.create(this)
            val signInOption = GetSignInWithGoogleOption.Builder(getString(R.string.web_client)).setNonce("nonce")
                .build()

            loginManager.loginWithGoogle(credentialManager, signInOption) { success ->
                if (success) {
                    showHomeActivity()
                } else {
                    val message = getString(R.string.errorloginGooglemessage)
                    showAlert(message)
                }
            }
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
