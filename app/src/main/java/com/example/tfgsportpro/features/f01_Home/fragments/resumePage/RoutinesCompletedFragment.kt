package com.example.tfgsportpro.features.f01_Home.fragments.resumePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentRoutinesCompletedBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class RoutinesCompletedFragment : Fragment() {

    lateinit var binding: FragmentRoutinesCompletedBinding
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoutinesCompletedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val spinnerOptions = listOf("All", "Low", "Medium", "High")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.levelSpinner.adapter = adapter

        binding.levelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLevel = spinnerOptions[position]
                loadRoutines(currentUserUid, selectedLevel)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun drawCompletedRoutines(routines: List<Triple<String, Int, Long>>) {
        for ((level, day, timestamp) in routines) {
            context?.let { context ->
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                sdf.timeZone = TimeZone.getTimeZone("Europe/Madrid")
                val dateString = sdf.format(Date(timestamp))

                val cardView = CardView(context)
                val layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, 0, 0, 20)
                cardView.layoutParams = layoutParams

                val cardColor = when (level) {
                    "low" -> ContextCompat.getColor(context, R.color.btn_line_low)
                    "medium" -> ContextCompat.getColor(context, R.color.btn_line_medium)
                    "high" -> ContextCompat.getColor(context, R.color.btn_line_high)
                    else -> ContextCompat.getColor(context, R.color.btn_line_low)
                }
                cardView.setCardBackgroundColor(cardColor)
                cardView.radius = 16f

                val textView = TextView(context)
                val routineText = getString(R.string.day_level, day, level) + getString(R.string.date, dateString)
                textView.text = routineText
                textView.setPadding(16, 16, 16, 16)
                textView.setTextColor(ContextCompat.getColor(context, R.color.colorLetra))

                cardView.addView(textView)

                binding.containerResume.addView(cardView)
            }
        }
    }
    private fun loadRoutines(currentUserUid: String, filterLevel: String) {
        firestore.collection("User")
            .document(currentUserUid)
            .collection("routines")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener { result ->
                val completedRoutines = mutableListOf<Triple<String, Int, Long>>()

                if (!result.isEmpty) {
                    result.documents.forEach { document ->
                        val level = document.getString("level") ?: return@forEach
                        val day = document.getLong("day")?.toInt() ?: return@forEach
                        val completedDate = document.getLong("timestamp") ?: return@forEach

                        if (filterLevel == "All" || level.equals(filterLevel, ignoreCase = true)) {
                            completedRoutines.add(Triple(level, day, completedDate))
                        }
                    }
                }

                binding.containerResume.removeAllViews()
                drawCompletedRoutines(completedRoutines)
            }
    }
}
