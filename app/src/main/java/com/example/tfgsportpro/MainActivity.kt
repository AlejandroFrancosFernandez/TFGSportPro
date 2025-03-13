package com.example.tfgsportpro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfgsportpro.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Crear un Intent para ir a la actividad RegisterActivity
        binding.bRegisterenviar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //Si el usuario existe y coincide con los datos, te lleva a la pagina Home
        binding.bLogin.setOnClickListener{
            email = binding.tietEmail.text.toString()
            password = binding.tietPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHomeActivity()
                    }else{
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)

        startActivity(intent)
        finish()
    }

    private fun showAlert() {
        Snackbar.make(binding.root, "You must register before continuing.", Snackbar.LENGTH_LONG).show()
    }
}