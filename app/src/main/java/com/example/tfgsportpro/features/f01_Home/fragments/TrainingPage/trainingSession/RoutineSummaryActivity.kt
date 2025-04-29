// RoutineSummaryActivity.kt
package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingSession

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityRoutineSummaryBinding
import com.example.tfgsportpro.features.f01_Home.domain.model.Exercise
import com.example.tfgsportpro.features.f01_Home.domain.routines.HighRoutine
import com.example.tfgsportpro.features.f01_Home.domain.routines.LowRoutine
import com.example.tfgsportpro.features.f01_Home.domain.routines.MediumRoutine
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.adapters.ExerciseAdapter
import kotlin.time.Duration.Companion.seconds

class RoutineSummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bBack.setOnClickListener {
            //Evitar el doble click
            binding.bBack.isEnabled = false
            onBackPressedDispatcher.onBackPressed()
        }

        val level = intent.getStringExtra("level") ?: "low"
        val day = intent.getIntExtra("day", -1)

        if (day in 1..30) {
            loadRoutineDetails(level, day)
        }

        binding.bStartRoutine.setOnClickListener {
            binding.bStartRoutine.isEnabled = false
            Intent(this, RoutineExerciseActivity::class.java).apply {
                putExtra("level", level)
                putExtra("day", day)
                startActivity(this)
            }
        }
    }

    private fun loadRoutineDetails(level: String, day: Int) {
        // Lista completa (incluye BREAKS)
        val routine: List<Exercise> = when (level) {
            "low"    -> LowRoutine().getRoutineForDayLow(day)
            "medium" -> MediumRoutine().getRoutineForDayMedium(day)
            "high"   -> HighRoutine().getRoutineForDayHigh(day)
            else     -> emptyList()
        }

        // Datos generales
        val totalTime = routine.sumOf { it.duracion }
        val minutes = totalTime / 60
        val seconds = totalTime % 60
        val exercisesCount = routine.count { it.nombreResId != R.string.BREAK }

        binding.tvInfoday.text = getString(R.string.day, day)
        binding.tvRoutineDetails.text = getString(R.string.routine_details, level, minutes, seconds)
        binding.tvExerciseList.text = getString(R.string.totalExercises, exercisesCount)

        val adapter = ExerciseAdapter(routine)
        binding.rvExercises.apply {
            layoutManager = LinearLayoutManager(this@RoutineSummaryActivity)
            this.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        //Para los dobles clicks
        binding.bStartRoutine.isEnabled = true
    }
}
