package com.example.tfgsportpro.features.f00_login_register.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    // Crear la lista con tres ítems
    val levels = listOf("Low (<2.5 Hours)", "Medium (2.5-5 Hours)", "High (>5 Hours)")
    lateinit var binding: ActivityRegisterBinding

    private var email: String = ""
    private var password: String = ""
    private var confirmPassword: String = ""
    private var name: String = ""
    private var physicalLevel: String = ""
    private var age: String = ""

    val baseDatos = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, levels)
        binding.PhysicalActivityLevel.adapter = adapter

        binding.bRegister.setOnClickListener {
            email = binding.tietEmail.text.toString()
            password = binding.tietPassword.text.toString()
            confirmPassword = binding.tietConfirmPassword.text.toString()
            name = binding.tietName.text.toString()
            physicalLevel = binding.PhysicalActivityLevel.selectedItem.toString()
            age = binding.tietAge.text.toString()

            if (!validateInputs(email, password, confirmPassword, name, age)) {
                return@setOnClickListener
            } else {
                // Primero, crea el usuario en Firebase Auth
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Usuario creado exitosamente
                            val firebaseUser = task.result?.user
                            if (firebaseUser != null) {
                                // Opcional: Actualiza el displayName en Firebase Auth
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build()
                                firebaseUser.updateProfile(profileUpdates)
                                    .addOnCompleteListener { updateTask ->
                                        if (updateTask.isSuccessful) {
                                            Log.d("RegisterActivity", "Profile updated successfully.")
                                        } else {
                                            Log.w("RegisterActivity", "Error updating profile.", updateTask.exception)
                                        }
                                    }

                                // Inserta los datos del usuario en Firestore usando el UID del usuario recién creado
                                insertUser(firebaseUser.uid)
                            }
                            showMainActivity()
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        // Volver a la página Main para el login
        binding.bComeback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Añadir el usuario a la base de datos de Firestore usando el UID del usuario
    private fun insertUser(uid: String) {
        val userData = hashMapOf(
            "Email" to email,
            "Name" to name,
            "Age" to age,
            "PhysicalLevel" to physicalLevel
        )

        baseDatos.collection("User")
            .document(uid) // Usamos el UID del usuario como ID del documento
            .set(userData)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "User document successfully added with UID: $uid")
            }
            .addOnFailureListener { e ->
                Log.w("RegisterActivity", "Error adding document", e)
            }
    }

    // Validar los inputs
    private fun validateInputs(email: String, password: String, confirmPassword: String, name: String, age: String): Boolean {
        var hasError = false

        if (email.isBlank()) {
            binding.tietEmail.error = "Email cannot be empty."
            hasError = true
        } else if (!isValidEmail(email)) {
            binding.tietEmail.error = "Please enter a valid email address."
            hasError = true
        } else {
            binding.tietEmail.error = null
        }

        if (password != confirmPassword) {
            binding.InputPassword.error = "Passwords do not match."
            binding.InputConfirmPassword.error = "Passwords do not match."
            hasError = true
        } else {
            binding.InputPassword.error = null
            binding.InputConfirmPassword.error = null
        }

        if (password.length < 6) {
            binding.InputPassword.error = "Password must be at least 6 characters."
            hasError = true
        } else if (password.length >= 6 && binding.InputPassword.error != null) {
            binding.InputPassword.error = null
        }

        if (name.isBlank()) {
            binding.tietName.error = "Name cannot be empty."
            hasError = true
        } else {
            binding.tietName.error = null
        }

        if (age.isBlank()) {
            binding.tietAge.error = "Age cannot be empty."
            hasError = true
        } else {
            binding.tietAge.error = null
        }

        return !hasError
    }

    // Mostrar alerta en caso de error
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Ir a la página de login (MainActivity) si el registro ha funcionado
    private fun showMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Verificar que se esté introduciendo un email
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
        return email.matches(emailRegex.toRegex())
    }
}
