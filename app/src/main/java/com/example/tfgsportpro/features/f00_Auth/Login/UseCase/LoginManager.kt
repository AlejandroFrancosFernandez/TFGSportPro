package com.example.tfgsportpro.features.f00_Auth.Login.UseCase

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.tfgsportpro.features.f00_Auth.Login.Data.UserPreferences
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginManager(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val sharedPreferences: UserPreferences = UserPreferences(context)

    // Verificar si el usuario ya está autenticado
    fun checkIfUserLoggedIn(onLoggedIn: (Boolean) -> Unit) {
        val currentUser = auth.currentUser
        onLoggedIn(currentUser != null)
    }

    // Iniciar sesión con correo y contraseña
    fun loginWithEmailAndPassword(email: String, password: String, onComplete: (Boolean) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                onComplete(it.isSuccessful)
                if (it.isSuccessful) {
                    saveEmailInPreferences(email)
                }
            }
        } else {
            onComplete(false)
        }
    }

    // Guardar el email del usuario en SharedPreferences
    private fun saveEmailInPreferences(email: String) {
        sharedPreferences.saveEmail(email)
    }

    // Iniciar sesión con Google
    fun loginWithGoogle(credentialManager: CredentialManager, signInOption: GetSignInWithGoogleOption, onComplete: (Boolean) -> Unit) {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInOption)
            .build()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Intentar obtener las credenciales
                val result: GetCredentialResponse = credentialManager.getCredential(context, request)
                handleGoogleSignIn(result, onComplete)
            } catch (e: androidx.credentials.exceptions.GetCredentialException) {
                onComplete(false)
            }
        }
    }

    // Manejar el inicio de sesión con Google
    private fun handleGoogleSignIn(result: GetCredentialResponse, onComplete: (Boolean) -> Unit) {
        val credential = result.credential

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

                FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener {
                    onComplete(it.isSuccessful)
                }
            } catch (e: GoogleIdTokenParsingException) {
                onComplete(false)
            }
        } else {
            onComplete(false)
        }
    }
}
