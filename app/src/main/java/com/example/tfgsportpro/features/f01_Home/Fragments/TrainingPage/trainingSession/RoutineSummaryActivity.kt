package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingSession

import com.example.tfgsportpro.features.f01_Home.domain.routines.MediumRoutine
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.databinding.ActivityRoutineSummaryBinding
import com.example.tfgsportpro.features.f01_Home.domain.routines.HighRoutine
import com.example.tfgsportpro.features.f01_Home.domain.routines.LowRoutine
import com.example.tfgsportpro.features.f01_Home.domain.model.Exercise
import com.example.tfgsportpro.features.f01_Home.domain.model.HighIntensityExercises
import com.example.tfgsportpro.features.f01_Home.domain.model.LowIntensityExercises

class RoutineSummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val level = intent.getStringExtra("level") ?: "low"
        val day = intent.getIntExtra("day", -1)

        if (day in 1..15) {
            loadRoutineDetails(level, day)
        } else {
        }

        binding.bStartRoutine.setOnClickListener {
            val intent = Intent(this, RoutineExerciseActivity::class.java)
            intent.putExtra("level", level)
            intent.putExtra("day", day)
            startActivity(intent)
        }
    }

    private fun loadRoutineDetails(level: String, day: Int) {
        val routineLow = LowRoutine().getRoutineForDayLow(day)
        val routineHigh = HighRoutine().getRoutineForDayHigh(day)
        val routineMedium = MediumRoutine().getRoutineForDayMedium(day)

        val routine: List<Exercise> = when (level) {
            "low" -> routineLow
            "high" -> routineHigh
            "medium" -> routineMedium
            else -> routineLow
        }

        // Incluir los descansos en el cálculo de tiempo total, pero no en la lista de ejercicios mostrados
        val exercisesWithoutBreak = routine.filter {
            it != LowIntensityExercises.BREAK && it != HighIntensityExercises.BREAK
        }
        val totalTime = routine.sumOf { it.duracion }
        val routineStrings: List<String> = exercisesWithoutBreak.map { getString(it.nombreResId) }
        val totalExercises = routineStrings.size
        val warmUpList = routineStrings.take(2)
        val mainList = routineStrings.drop(2)
        val minutes = totalTime / 60
        val seconds = totalTime % 60

        binding.tvInfoday.text = "Día $day"
        binding.tvRoutineDetails.text = "Nivel: $level\nEjercicios: $totalExercises\nTiempo total: $minutes min $seconds seg"
        binding.tvExerciseList.text = "Total de ejercicios: $totalExercises"

        binding.tvWarmUpDetails.text = warmUpList.joinToString(separator = "\n") { "• $it" }
        binding.tvOtherExerciseDetails.text = mainList.joinToString(separator = "\n") { "• $it" }

    }
}
