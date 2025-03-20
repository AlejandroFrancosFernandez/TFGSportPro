package com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.TrainingLevels

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityRoutineSummaryBinding

class RoutineSummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener los datos enviados desde el fragmento de entrenamiento
        val level = intent.getStringExtra("level")  // 'low', 'medium', 'high'
        val day = intent.getIntExtra("day", -1)    // Día presionado (1-15)

        loadRoutineDetails(level, day)
    }

    private fun loadRoutineDetails(level: String?, day: Int) {

        binding.tvRoutineDetails.text = "Nivel: $level\nDía: $day\nEjercicios:\n"
        binding.tvExercise1.text = "Ejercicio 1: Sentadillas\nDuración: 30 segundos"
    }
}
