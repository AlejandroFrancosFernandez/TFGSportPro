package com.example.tfgsportpro.features.f00_Auth.Register.UI

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.features.f00_Auth.Login.UI.LoginActivity
import com.example.tfgsportpro.features.f00_Auth.Register.UseCase.RegisterManager
import com.example.tfgsportpro.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerManager: RegisterManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración directamente en onCreate
        val levels = listOf("Low (<2.5 Hours)", "Medium (2.5-5 Hours)", "High (>5 Hours)")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, levels)
        binding.PhysicalActivityLevel.adapter = adapter

        binding.bRegister.setOnClickListener { handleRegister() }
        binding.bComeback.setOnClickListener { navigateToLogin() }

        // Inicializar RegisterManager
        registerManager = RegisterManager(this)
    }

    private fun handleRegister() {
        val email = binding.tietEmail.text.toString().trim()
        val password = binding.tietPassword.text.toString().trim()
        val confirmPassword = binding.tietConfirmPassword.text.toString().trim()
        val name = binding.tietName.text.toString().trim()
        val physicalLevel = binding.PhysicalActivityLevel.selectedItem.toString()
        val age = binding.tietAge.text.toString().trim()

        if (!validateInputs(email, password, confirmPassword, name, age)) return

        registerUser(email, password, name, age, physicalLevel)
    }

    private fun validateInputs(email: String, password: String, confirmPassword: String, name: String, age: String): Boolean {
        var isValid = true

        if (email.isBlank() || !isValidEmail(email)) {
            binding.tietEmail.error = getString(R.string.Error_invalid_Email)
            isValid = false
        }

        if (password.length < 6) {
            binding.InputPassword.error = getString(R.string.Error_at_Least_6_Characters)
            isValid = false
        }

        if (password != confirmPassword) {
            binding.InputPassword.error = getString(R.string.Error_passwords_Dont_Match)
            binding.InputConfirmPassword.error = getString(R.string.Error_passwords_Dont_Match)
            isValid = false
        }

        if (name.isBlank()) {
            binding.tietName.error = getString(R.string.Error_name_cannot_be_empty)
            isValid = false
        }

        if (age.isBlank()) {
            binding.tietAge.error = getString(R.string.Error_age_cannot_be_empty)
            isValid = false
        }

        return isValid
    }

    private fun registerUser(email: String, password: String, name: String, age: String, physicalLevel: String) {
        registerManager.registerUser(email, password, name, age, physicalLevel) { success ->
            if (success) {
                navigateToLogin()
            } else {
                showAlert(getString(R.string.Error_creating_user))
            }
        }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,7}$"
        return email.matches(emailRegex.toRegex())
    }
}
