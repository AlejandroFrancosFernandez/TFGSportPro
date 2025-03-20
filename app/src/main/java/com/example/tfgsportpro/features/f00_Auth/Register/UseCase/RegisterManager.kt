package com.example.tfgsportpro.features.f00_Auth.Register.UseCase

import android.content.Context
import com.example.tfgsportpro.features.f00_Auth.Register.Data.UserPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class RegisterManager(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val sharedPreferences = UserPreferences(context)

    // Insertar datos del usuario en Firebase Authentication
    fun registerUser(email: String, password: String, name: String, age: String, physicalLevel: String, onComplete: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    firebaseUser?.let {
                        insertUserIntoFirestore(it.uid, email, name, age, physicalLevel)
                    }
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }


    // Insertar datos del usuario en Firestore
    private fun insertUserIntoFirestore(uid: String, email: String, name: String, age: String, physicalLevel: String) {
        val currentDate = Calendar.getInstance().time

        val userData = hashMapOf(
            "Email" to email,
            "Name" to name,
            "Age" to age,
            "PhysicalLevel" to physicalLevel,
            "registrationDate" to currentDate,
            "lastLoginDate" to currentDate,
            "streak" to 0
        )

        // Guardar los datos en Firestore
        db.collection("User").document(uid)
            .set(userData)
            .addOnSuccessListener {
                // Lógica posterior a la inserción exitosa (si es necesario)
            }
            .addOnFailureListener { e ->
                // Manejo de errores si no se puede guardar en Firestore
            }
    }


}
