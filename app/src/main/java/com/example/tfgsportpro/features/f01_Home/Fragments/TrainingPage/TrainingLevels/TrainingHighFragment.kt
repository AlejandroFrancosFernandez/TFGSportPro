package com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.TrainingLevels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.databinding.FragmentTrainingHighBinding
import com.google.android.material.snackbar.Snackbar

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

        binding.bDay1.setOnClickListener{
            Snackbar.make(binding.root, "Dia 1", Snackbar.LENGTH_LONG).show()
        }

        return binding.root
    }
}