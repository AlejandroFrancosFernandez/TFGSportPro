package com.example.tfgsportpro.features.f00_Auth.Register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.databinding.ActivityRegisterBinding
import com.example.tfgsportpro.features.f00_Auth.Login.UI.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

        // Botón de registro
        binding.bRegister.setOnClickListener {
            getInputData()
            if (validateInputs()) {
                registerUser()
            }
        }

        // Volver a la página Main para el login
        binding.bComeback.setOnClickListener {
            navigateToMainActivity()
        }
    }

    // Método para obtener los datos del formulario
    private fun getInputData() {
        email = binding.tietEmail.text.toString()
        password = binding.tietPassword.text.toString()
        confirmPassword = binding.tietConfirmPassword.text.toString()
        name = binding.tietName.text.toString()
        physicalLevel = binding.PhysicalActivityLevel.selectedItem.toString()
        age = binding.tietAge.text.toString()
    }

    // Método para validar los inputs
    private fun validateInputs(): Boolean {
        var hasError = false

        if (email.isBlank()) {
            binding.tietEmail.error = "Email cannot be empty."
            hasError = true
        } else if (!isValidEmail(email)) {
            binding.tietEmail.error = "Please enter a valid email address."
            hasError = true
        }

        if (password != confirmPassword) {
            binding.InputPassword.error = "Passwords do not match."
            binding.InputConfirmPassword.error = "Passwords do not match."
            hasError = true
        }

        if (password.length < 6) {
            binding.InputPassword.error = "Password must be at least 6 characters."
            hasError = true
        }

        if (name.isBlank()) {
            binding.tietName.error = "Name cannot be empty."
            hasError = true
        }

        if (age.isBlank()) {
            binding.tietAge.error = "Age cannot be empty."
            hasError = true
        }

        return !hasError
    }

    // Método para registrar el usuario en Firebase
    private fun registerUser() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    if (firebaseUser != null) {
                        updateProfile(firebaseUser)
                        insertUser(firebaseUser.uid)
                    }
                    showMainActivity()
                } else {
                    showAlert("Error al crear el usuario.")
                }
            }
    }

    // Método para actualizar el perfil del usuario en Firebase
    private fun updateProfile(firebaseUser: FirebaseUser) {
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
    }

    // Método para insertar el usuario en Firestore
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

    // Método para mostrar alerta de error
    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Método para ir a la actividad de login (MainActivity)
    private fun showMainActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Método para verificar si el email es válido
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
        return email.matches(emailRegex.toRegex())
    }

    // Método para navegar hacia la MainActivity (login)
    private fun navigateToMainActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
