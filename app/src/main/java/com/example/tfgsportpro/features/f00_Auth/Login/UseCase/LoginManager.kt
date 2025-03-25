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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

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
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Guardamos el email en SharedPreferences
                        saveEmailInPreferences(email)

                        // Actualizamos la racha de días consecutivos conectados
                        val user = FirebaseAuth.getInstance().currentUser
                        user?.uid?.let { uid ->
                            updateStreak(uid)
                        }

                        onComplete(true)
                    } else {
                        onComplete(false)
                    }
                }
        } else {
            onComplete(false)
        }
    }

    private fun saveEmailInPreferences(email: String) {
        sharedPreferences.saveEmail(email)
    }

    fun loginWithGoogle(
        credentialManager: CredentialManager,
        signInOption: GetSignInWithGoogleOption,
        onComplete: (Boolean) -> Unit
    ) {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(signInOption)
            .build()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Intentar obtener las credenciales
                val result: GetCredentialResponse =
                    credentialManager.getCredential(context, request)
                handleGoogleSignIn(result, onComplete)
            } catch (e: androidx.credentials.exceptions.GetCredentialException) {
                onComplete(false)
            }
        }
    }

    private fun handleGoogleSignIn(result: GetCredentialResponse, onComplete: (Boolean) -> Unit) {
        val credential = result.credential

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential =
                    GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

                FirebaseAuth.getInstance().signInWithCredential(authCredential)
                    .addOnCompleteListener {
                        onComplete(it.isSuccessful)
                    }
            } catch (e: GoogleIdTokenParsingException) {
                onComplete(false)
            }
        } else {
            onComplete(false)
        }
    }

    fun updateStreakOnAutoLogin() {
        val user = auth.currentUser
        user?.uid?.let { uid ->
            updateStreak(uid)
        }
    }

    private fun updateStreak(uid: String) {
        val db = FirebaseFirestore.getInstance()
        val userDocRef = db.collection("User").document(uid)

        userDocRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val lastLoginDate = document.getDate("lastLoginDate") ?: Calendar.getInstance().time
                val streak = document.getLong("streak") ?: 0L
                val currentDate = Calendar.getInstance().time

                if (isSameDay(lastLoginDate, currentDate)) {
                    userDocRef.update("lastLoginDate", currentDate)
                    return@addOnSuccessListener
                }

                val updatedStreak = if (isNextDay(lastLoginDate, currentDate)) {
                    streak + 1
                } else {
                    1L
                }

                val updates: MutableMap<String, Any> = mutableMapOf(
                    "lastLoginDate" to currentDate,
                    "streak" to updatedStreak
                )

                userDocRef.update(updates)
                    .addOnSuccessListener {
                    }
                    .addOnFailureListener { e ->
                    }
            }
        }
    }

    //Verifica si es el mismo dia (no suma al streak)
    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val calendar1 = Calendar.getInstance().apply { time = date1 }
        val calendar2 = Calendar.getInstance().apply { time = date2 }
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }
    //Verifica si es otro dia (suma +1 al streak)
    private fun isNextDay(date1: Date, date2: Date): Boolean {
        val calendar1 = Calendar.getInstance().apply { time = date1 }
        val calendar2 = Calendar.getInstance().apply { time = date2 }
        return calendar2.get(Calendar.DAY_OF_YEAR) == calendar1.get(Calendar.DAY_OF_YEAR) + 1 &&
                calendar2.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR)
    }

}
