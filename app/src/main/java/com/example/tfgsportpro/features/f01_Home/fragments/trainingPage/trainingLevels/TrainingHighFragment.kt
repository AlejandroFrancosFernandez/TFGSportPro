package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentTrainingHighBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TrainingHighFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrainingHighFragment : Fragment() {

    lateinit var binding: FragmentTrainingHighBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingHighBinding.inflate(inflater, container, false)

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
                intent.putExtra("level", "high")
                intent.putExtra("day", i)
                startActivity(intent)
            }
        }
        return binding.root
    }
}
