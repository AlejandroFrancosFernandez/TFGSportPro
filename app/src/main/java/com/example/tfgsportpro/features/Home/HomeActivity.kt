package com.example.tfgsportpro.features.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityHomeBinding
import com.example.tfgsportpro.features.Home.Fragments.ResumeFragment
import com.example.tfgsportpro.features.Home.Fragments.MeFragment
import com.example.tfgsportpro.features.Home.Fragments.Training.TrainingLowFragment
import com.example.tfgsportpro.features.Home.Fragments.TrainingFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar TrainingLowFragment por defecto al iniciar
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, TrainingFragment())
            .commit()

        binding.bNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navTraining -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, TrainingFragment()).commit()
                R.id.navMe -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, MeFragment()).commit()
                R.id.navResume -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, ResumeFragment()).commit()
            }
            true
        }

        //Configurar el boton este
        binding.bComeback.setOnClickListener {

        }

    }
}
