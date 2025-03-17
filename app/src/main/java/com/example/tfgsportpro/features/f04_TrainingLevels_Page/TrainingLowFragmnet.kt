package com.example.tfgsportpro.features.f04_TrainingLevels_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentTrainingLowFragmnetBinding


class TrainingLowFragmnet : Fragment() {

    lateinit var binding: FragmentTrainingLowFragmnetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }
}