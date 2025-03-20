package com.example.tfgsportpro.features.f01_Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityHomeBinding
import com.example.tfgsportpro.features.f01_Home.Fragments.ResumePage.ResumeFragment
import com.example.tfgsportpro.features.f01_Home.Fragments.MePage.MeFragment
import com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.TrainingFragment
import com.example.tfgsportpro.features.f00_Auth.Login.UseCase.LoginManager
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var loginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el LoginManager
        loginManager = LoginManager(this)

        // Por si no aprieta al login, y se inicia desde el sharedPreferences
        if (FirebaseAuth.getInstance().currentUser != null) {
            loginManager.updateStreakOnAutoLogin()
        }

        binding.bNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navTraining -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, TrainingFragment()).commit()
                R.id.navMe -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MeFragment()).commit()
                R.id.navResume -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ResumeFragment()).commit()
            }
            true
        }

        binding.bComeback.setOnClickListener {
            finish()
        }

        binding.bProfile.setOnClickListener {
            val meFragment = MeFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, meFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            binding.bNavigation.selectedItemId = R.id.navMe
        }
    }
}
