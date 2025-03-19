package com.example.tfgsportpro.features.f01_Home.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentTrainingBinding
import com.example.tfgsportpro.features.f03_profile.incognita.ViewPagerAdapter

class TrainingFragment : Fragment() {

    lateinit var binding: FragmentTrainingBinding

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)

        // Configurar el ViewPager2 con el adaptador
        val pagerAdapter = ViewPagerAdapter(requireActivity()) // Usar el fragment activity actual
        binding.viewPager.adapter = pagerAdapter // Asignar el adaptador al ViewPager2

        // Configurar el BottomNavigationView para cambiar entre fragmentos del ViewPager2
        /*binding.bNavigationLevels.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navTrainingLow -> binding.viewPager.setCurrentItem(0, true) // Nivel Bajo
                R.id.navTrainingMedium -> binding.viewPager.setCurrentItem(1, true) // Nivel Medio
                R.id.navTrainingHigh -> binding.viewPager.setCurrentItem(2, true) // Nivel Alto
            }
            true
        }*/

        return binding.root
    }
}
