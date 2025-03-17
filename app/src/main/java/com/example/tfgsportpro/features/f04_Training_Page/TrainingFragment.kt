package com.example.tfgsportpro.features.f04_Training_Page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentTrainingBinding
import com.example.tfgsportpro.features.f00_login_register.activity.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [TrainingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrainingFragment : Fragment() {

    lateinit var binding: FragmentTrainingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrainingBinding.inflate(layoutInflater)

        binding.imageviewComeBack.setOnClickListener {
            requireActivity().finishAffinity()
        }

        return binding.root
    }



}