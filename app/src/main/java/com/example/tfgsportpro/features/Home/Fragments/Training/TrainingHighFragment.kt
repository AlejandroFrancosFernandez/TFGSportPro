package com.example.tfgsportpro.features.Home.Fragments.Training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.databinding.FragmentTrainingHighBinding
import com.example.tfgsportpro.databinding.FragmentTrainingMediumBinding

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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrainingHighBinding.inflate(inflater, container, false)

        return binding.root
    }
}