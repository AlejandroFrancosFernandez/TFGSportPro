package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfgsportpro.databinding.FragmentTrainingLowFragmnetBinding

class TrainingLowFragment : Fragment() {

    lateinit var binding: FragmentTrainingLowFragmnetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrainingLowFragmnetBinding.inflate(inflater, container, false)

        // Llamamos a setupButtons para configurar los botones
        setupButtons()

        // Devolvemos la vista inflada
        return binding.root
    }

    private fun setupButtons() {
        for (i in 1..15) {
            val button = when (i) {
                1 -> binding.bDay1
                2 -> binding.bDay2
                3 -> binding.bDay3
                4 -> binding.bDay4
                5 -> binding.bDay5
                6 -> binding.bDay6
                7 -> binding.bDay7
                8 -> binding.bDay8
                9 -> binding.bDay9
                10 -> binding.bDay10
                11 -> binding.bDay11
                12 -> binding.bDay12
                13 -> binding.bDay13
                14 -> binding.bDay14
                15 -> binding.bDay15
                else -> null
            }

            button?.setOnClickListener {
                val intent = Intent(requireContext(), RoutineSummaryActivity::class.java)
                intent.putExtra("level", "low")
                intent.putExtra("day", i)
                startActivity(intent)
            }
        }
    }
}
