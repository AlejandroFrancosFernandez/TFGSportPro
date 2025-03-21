package com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfgsportpro.databinding.FragmentTrainingBinding
import com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.ViewPager2.ViewPagerAdapter

class TrainingFragment : Fragment() {

    lateinit var binding: FragmentTrainingBinding

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)

        val pagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = pagerAdapter

        return binding.root
    }
}
