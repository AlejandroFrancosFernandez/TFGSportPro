package com.example.tfgsportpro.features.f01_Home.Fragments.Training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.databinding.FragmentTrainingMediumBinding

class TrainingMediumFragment : Fragment() {

    lateinit var binding: FragmentTrainingMediumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrainingMediumBinding.inflate(inflater, container, false)

        return binding.root
    }
}
