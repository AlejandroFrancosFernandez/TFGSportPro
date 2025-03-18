package com.example.tfgsportpro.features.Home.Fragments.Training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentTrainingLowFragmnetBinding
import com.example.tfgsportpro.features.Home.Fragments.Training.TrainingMediumFragment
import com.example.tfgsportpro.features.Home.Fragments.Training.TrainingHighFragment

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
