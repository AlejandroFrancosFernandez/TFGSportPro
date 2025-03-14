package com.example.tfgsportpro.features.f00_login_register.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.HomeActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import androidx.core.content.edit
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private var email: String = ""
    private var password: String = ""

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit() {
            putString("email", email)
            apply()
        }

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

        binding.bGoogle.setOnClickListener{
            /*auth = Firebase.auth

            val googleIdOption = GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(true)
                .setServerClientId(baseContext.getString(R.string.default_web_client_id))
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        updateUI(null)
                    }
                }*/
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