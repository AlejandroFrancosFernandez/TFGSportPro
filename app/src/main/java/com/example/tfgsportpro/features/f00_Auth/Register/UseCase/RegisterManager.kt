package com.example.tfgsportpro.features.f00_Auth.Register.UseCase

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.example.tfgsportpro.features.f00_Auth.Register.Data.UserPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class RegisterManager(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val sharedPreferences = UserPreferences(context)
    private lateinit var progressDialog: ProgressDialog

    private fun showLoading() {
        progressDialog = ProgressDialog(context).apply {
            setMessage("Registrando usuario...")
            setCancelable(false)
            show()
        }
    }

    private fun hideLoading() {
        if (::progressDialog.isInitialized) {
            progressDialog.dismiss()
        }
    }

    fun registerUser(email: String, password: String, name: String, age: String, physicalLevel: String, onComplete: (Boolean) -> Unit) {
        showLoading()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    firebaseUser?.let { user ->
                        user.sendEmailVerification()
                            .addOnCompleteListener { verifyTask ->
                                hideLoading()
                                if (verifyTask.isSuccessful) {
                                    insertUserIntoFirestore(user.uid, email, name, age, physicalLevel)
                                    notifyUserToCheckEmail()
                                    waitForEmailVerification(user, onComplete) // Esperar verificación
                                } else {
                                    Log.e("FirebaseError", "Error al enviar email", verifyTask.exception)
                                    onComplete(false)
                                }
                            }
                    }
                } else {
                    hideLoading()
                    Log.e("FirebaseError", "Error en el registro", task.exception)
                    onComplete(false)
                }
            }
    }

    private fun waitForEmailVerification(user: FirebaseUser, onComplete: (Boolean) -> Unit) {
        val handler = android.os.Handler()
        val checkInterval = 3000L // Cada 3 segundos

        val runnable = object : Runnable {
            override fun run() {
                user.reload().addOnCompleteListener {
                    if (user.isEmailVerified) {
                        db.collection("User").document(user.uid)
                            .update("verified", true)
                            .addOnSuccessListener {
                                Log.d("Firestore", "Usuario actualizado como verificado en Firestore ✅")
                            }
                            .addOnFailureListener {
                                Log.e("Firestore", "Error al actualizar usuario", it)
                            }

                        AlertDialog.Builder(context)
                            .setTitle("Verificación completada")
                            .setMessage("Tu correo ha sido verificado. Ahora puedes iniciar sesión.")
                            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                            .show()
                        onComplete(true)
                    } else {
                        Log.d("FirebaseDebug", "Correo aún no verificado. Reintentando...")
                        handler.postDelayed(this, checkInterval)
                    }
                }
            }
        }
        handler.postDelayed(runnable, checkInterval)
    }

    private fun insertUserIntoFirestore(uid: String, email: String, name: String, age: String, physicalLevel: String) {
        val currentDate = Calendar.getInstance().time

        val userData = hashMapOf(
            "Email" to email,
            "Name" to name,
            "Age" to age,
            "PhysicalLevel" to physicalLevel,
            "registrationDate" to currentDate,
            "lastLoginDate" to currentDate,
            "streak" to 0,
            "verified" to false
        )

        db.collection("User").document(uid)
            .set(userData)
            .addOnSuccessListener {
                Log.d("Firestore", "Usuario guardado en Firestore correctamente ✅")
            }
            .addOnFailureListener {
                Log.e("Firestore", "Error al guardar usuario", it)
            }
    }

    private fun notifyUserToCheckEmail() {
        AlertDialog.Builder(context)
            .setTitle("Verificación requerida")
            .setMessage("Hemos enviado un correo de verificación. Por favor, revisa tu bandeja de entrada y confirma tu email antes de iniciar sesión.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
