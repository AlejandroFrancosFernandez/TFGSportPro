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
        setupButtons()

        return binding.root
    }

    private fun setupButtons() = with(binding){
        listOf(bDay1,bDay2,bDay3,bDay4,bDay5,bDay6,bDay7,bDay8,bDay9,bDay10,bDay11,bDay12,bDay13,bDay14,bDay15).forEachIndexed { i, button ->
            button.setOnClickListener{
                val intent = Intent(requireContext(), RoutineSummaryActivity::class.java)
                intent.putExtra("level", "low")
                intent.putExtra("day", i+1)
                startActivity(intent)
            }
        }
    }
}
