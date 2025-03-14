package com.example.tfgsportpro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.databinding.ActivityHomeBinding
import com.example.tfgsportpro.features.f03_profile.fragments.MeFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navTraining->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, TrainingFragment()).commit()
                R.id.navMe->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MeFragment()).commit()
            }
            true
        }
    }
}