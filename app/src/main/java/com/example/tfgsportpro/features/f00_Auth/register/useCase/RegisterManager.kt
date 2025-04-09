package com.example.tfgsportpro.features.f00_Auth.register.useCase

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import com.example.tfgsportpro.R
import com.example.tfgsportpro.features.f00_Auth.register.data.UserPreferences
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
                                    waitForEmailVerification(user, onComplete)
                                } else {
                                    onComplete(false)
                                }
                            }
                    }
                } else {
                    hideLoading()
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

                        AlertDialog.Builder(context)
                            .setTitle(R.string.verification_Completed)
                            .setMessage(R.string.verification_Email)
                            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                            .show()
                        onComplete(true)
                    } else {
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
    }

    private fun notifyUserToCheckEmail() {
        AlertDialog.Builder(context)
            .setTitle(R.string.verification_Required)
            .setMessage(R.string.email_Sent)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}