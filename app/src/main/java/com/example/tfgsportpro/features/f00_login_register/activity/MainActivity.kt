package com.example.tfgsportpro.features.f00_login_register.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.tfgsportpro.features.f01_Home_page.HomeActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import androidx.core.content.edit
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Verificar si el usuario ya está autenticado
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Si el usuario ya está logueado, redirigimos a HomeActivity
            showHomeActivity()
        }

        binding.bRegisterenviar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Si el usuario existe y coincide con los datos, te lleva a la página Home
        binding.bLogin.setOnClickListener {
            loginWithEmailAndPassword()
        }

        binding.bGoogle.setOnClickListener {
            loginWithGoogle()
        }
    }

    private fun loginWithGoogle() {
        val context = this
        val credentialManager = CredentialManager.create(context)
        val signInWithGoogleOption = GetSignInWithGoogleOption.Builder(getString(R.string.web_client))
            .setNonce("nonce")
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Intentar obtener las credenciales
                val result: GetCredentialResponse = credentialManager.getCredential(context, request)
                handleSignIn(result)
            } catch (e: androidx.credentials.exceptions.GetCredentialException) {
                // Manejo del error de obtener las credenciales
                e.printStackTrace() // Imprimir el error para depuración
                Toast.makeText(context, "Error getting the credential: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun handleSignIn(result: GetCredentialResponse) {
        val credential = result.credential

        when (credential) {
            is CustomCredential -> {
                // Verifica que el tipo de la credencial sea GoogleIdToken
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Si la credencial es válida, extrae el token de ID
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                        // Verifica que el ID Token no sea nulo
                        val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

                        // Autenticar con Firebase usando las credenciales de Google
                        FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener {
                            if (it.isSuccessful) {
                                // Si la autenticación es exitosa, ir a la actividad principal
                                showHomeActivity()
                            } else {
                                // Si ocurre un error, mostrar un mensaje
                                showAlert("Error signing in with Google.")
                            }
                        }

                    } catch (e: GoogleIdTokenParsingException) {
                        Snackbar.make(binding.root, "Error parsing the Google ID token", Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    Snackbar.make(binding.root, "Unexpected type of credential", Snackbar.LENGTH_LONG).show()
                }
            }

            else -> {
                Snackbar.make(binding.root, "Unexpected type of credential", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loginWithEmailAndPassword() {
        val email = binding.tietEmail.text.toString()
        val password = binding.tietPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    // Guardamos el correo electrónico del usuario en SharedPreferences
                    getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit() {
                        putString("email", email)  // Guardamos el email
                        apply()
                    }
                    showHomeActivity()
                } else {
                    showAlert()
                }
            }
        } else {
            showAlert("Please enter your credentials")
        }
    }

    private fun showHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showAlert(message: String = "You must register before continuing.") {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}
