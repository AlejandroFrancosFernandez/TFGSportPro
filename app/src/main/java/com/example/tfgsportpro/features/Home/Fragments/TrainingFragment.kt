package com.example.tfgsportpro.features.Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentTrainingBinding
import com.example.tfgsportpro.features.Home.Fragments.Training.TrainingHighFragment
import com.example.tfgsportpro.features.Home.Fragments.Training.TrainingLowFragment
import com.example.tfgsportpro.features.Home.Fragments.Training.TrainingMediumFragment

class TrainingFragment : Fragment() {

    lateinit var binding: FragmentTrainingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)

        binding.bNavigationLevels.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.navTrainingLow -> TrainingLowFragment()    // Nivel bajo
                R.id.navTrainingMedium -> TrainingMediumFragment()  // Nivel medio
                R.id.navTrainingHigh -> TrainingHighFragment()   // Nivel alto
                else -> null
            }

            if (fragment != null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit()
            }
            true
        }

        return binding.root
    }
}
