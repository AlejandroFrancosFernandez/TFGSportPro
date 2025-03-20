package com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.TrainingLevels

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
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTrainingLowFragmnetBinding.inflate(inflater, container, false)

        return binding.root
    }
}
