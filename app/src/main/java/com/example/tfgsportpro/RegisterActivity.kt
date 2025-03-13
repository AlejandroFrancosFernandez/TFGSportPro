package com.example.tfgsportpro

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfgsportpro.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

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
            }else{
                insertUser()
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        showMainActivity()
                    }else{
                        showAlert()
                    }
                }
            }
        }

        //Volver a la pagina Main, para el login
        binding.bComeback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Iniciar la actividad
            startActivity(intent)
        }
    }

    //Añadir el usuario a la base de datos de firestore
    private fun insertUser(){

        val user = hashMapOf(
            "Email" to email,
            "Name" to name,
            "Age" to age,
            "PhysicalLevel" to physicalLevel
        )

        baseDatos.collection("User")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("depurando", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("depurando", "Error adding document", e)
            }
    }

    //Validar los inputs
    private fun validateInputs(email: String, password: String, confirmPassword: String, name: String, age: String): Boolean {
        var hasError = false
        // Validar que el email no esté vacío y tenga formato válido
        if (email.isBlank()) {
            binding.tietEmail.error = "Email cannot be empty."
            hasError = true
        } else if (!isValidEmail(email)) {
            binding.tietEmail.error = "Please enter a valid email address."
            hasError = true
        } else {
            binding.tietEmail.error = null
        }

        // Validar que las contraseñas coinciden
        if (password != confirmPassword) {
            binding.InputPassword.error = "Passwords do not match."
            binding.InputConfirmPassword.error = "Passwords do not match."
            hasError = true
        } else {
            binding.InputPassword.error = null
            binding.InputConfirmPassword.error = null
        }

        // Validar que la contraseña tenga al menos 4 caracteres
        if (password.length < 6) {
            binding.InputPassword.error = "Password must be at least 6 characters."
            hasError = true
        } else if (password.length >= 6 && binding.InputPassword.error != null) {
            binding.InputPassword.error = null
        }

        // Validar que el nombre no esté vacío
        if (name.isBlank()) {
            binding.tietName.error = "Name cannot be empty."
            hasError = true
        } else {
            binding.tietName.error = null
        }

        // Validar que la edad no esté vacía
        if (age.isBlank()) {
            binding.tietAge.error = "Age cannot be empty."
            hasError = true
        } else {
            binding.tietAge.error = null
        }

        return !hasError
    }

    //Si ocurre algun error
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Ir a la página de login(MainActivity) si ha funcionado el register
    private fun showMainActivity(){
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        finish()
    }

    //Verificar que se esté introduciendo un email
    private fun isValidEmail(email: String): Boolean {
        // Expresión regular para validar un email
        val emailRegex =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
        return email.matches(emailRegex.toRegex())
    }
}