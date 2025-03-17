package com.example.tfgsportpro.features.f01_Home_page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityHomeBinding
import com.example.tfgsportpro.features.f02_Resume_page.ResumeFragment
import com.example.tfgsportpro.features.f03_Me_page.MeFragment
import com.example.tfgsportpro.features.f04_Training_Page.TrainingFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navTraining ->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, TrainingFragment()).commit()
                R.id.navMe ->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MeFragment()).commit()
                R.id.navResume ->supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ResumeFragment()).commit()
            }
            true
        }
    }
}