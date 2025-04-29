package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentTrainingMediumBinding
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.adapters.DaysAdapter
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingSession.RoutineSummaryActivity

class TrainingMediumFragment : Fragment() {

    lateinit var binding: FragmentTrainingMediumBinding

    override fun onResume() {
        super.onResume()
        binding.recyclerDays.adapter?.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingMediumBinding.inflate(inflater, container, false)

        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val daysList = List(30) { it + 1 }
        val adapter = DaysAdapter(daysList, R.layout.btn_medium_day, "medium" ) { day ->
            val intent = Intent(requireContext(), RoutineSummaryActivity::class.java)
            intent.putExtra("level", "medium")
            intent.putExtra("day", day)
            startActivity(intent)
        }
        binding.recyclerDays.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerDays.adapter = adapter
    }
}