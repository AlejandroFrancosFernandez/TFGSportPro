package com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.TrainingLevels

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityRoutineSummaryBinding

class RoutineSummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Obtener el nivel ("low" o "high") y el día
        val level = intent.getStringExtra("level") ?: "low"
        val day = intent.getIntExtra("day", -1)

        if (day in 1..15) {
            loadRoutineDetails(level, day)
        } else {
        }
    }

    private fun loadRoutineDetails(level: String, day: Int) {
        val routineLow = Exercises.getRoutineForDayLow(day)
        val routineHigh = ExercisesHigh.getRoutineForDayHigh(day)
        // Para todos los niveles usamos List<String>
        val routineStrings: List<String> = when (level) {
            "low" -> routineLow.map { getString(it.nombreResId) }
            "high" -> routineHigh.map { getString(it.nombreResId) }
            "medium" -> getRoutineForDayMedium(day)
            else -> routineLow.map { getString(it.nombreResId) }
        }

        val totalExercises = routineStrings.size
        val warmUpExercises = routineStrings.take(2).joinToString("\n") { "• $it" }
        val mainExercises = routineStrings.drop(2).joinToString("\n") { "• $it" }

        binding.tvRoutineDetails.text = "Nivel: $level\nEjercicios: $totalExercises"
        binding.tvExerciseList.text = "Total de ejercicios: $totalExercises"
        binding.tvWarmUpDetails.text = warmUpExercises
        binding.tvOtherExerciseDetails.text = mainExercises
        binding.tvInfoday.text = "Día $day"
    }


    private fun getRoutineForDayMedium(day: Int): List<String> {
        return when (day) {
            1 -> listOf(
                "Jumping Jacks (Calentamiento)",
                "Arm Circles (Calentamiento)",
                getString(R.string.squats),
                "Flexiones normales",
                getString(R.string.lunges),
                "Plancha frontal",
                "Abdominales bicicleta",
                "Superman",
                "Step-ups",
                "Puente de glúteos",
                "Levantamiento de rodillas en plancha",
                "Elevación de talones"
            )
            2 -> listOf(
                "Jump Rope (Calentamiento)",
                "Shoulder Rolls (Calentamiento)",
                "Abdominales en V",
                "Elevaciones de pierna lateral",
                getString(R.string.isometric_squat),
                "Rotación de cintura",
                "Plancha con toques de hombros",
                "Flexiones inclinadas",
                "Giros rusos",
                getString(R.string.hip_Raise),
                getString(R.string.jumping_Lunges),
                "Puente de glúteos con una pierna"
            )
            3 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                "Estocadas con pausa",
                "Extensión de piernas sentado",
                "Step-ups",
                "Plancha con apoyo de codos",
                "Superman con brazos extendidos",
                "Crunches con piernas elevadas",
                "Elevaciones de talones",
                "Saltos de lado a lado",
                "Flexiones de tríceps en banco",
                "Abdominales cruzados"
            )
            4 -> listOf(
                "Jump Rope (Calentamiento)",
                "Shoulder Rolls (Calentamiento)",
                "Elevaciones de pierna a gatas",
                "Puente de glúteos con apertura de rodillas",
                "Plancha lateral",
                "Flexiones normales",
                "Giros de torso",
                getString(R.string.crossover_Lunges),
                getString(R.string.hip_Raise),
                "Rotaciones de torso",
                getString(R.string.squat_with_pause),
                "Superman con patada de rana"
            )
            5 -> listOf(
                "Jumping Jacks (Calentamiento)",
                "Arm Circles (Calentamiento)",
                "Plancha con movimiento lateral",
                getString(R.string.sumo_squat),
                "Flexiones con apoyo de rodillas",
                "Step-ups",
                "Puente de glúteos",
                "Abdominales cortos",
                "Elevación de piernas acostado",
                "Elevación de talones con apoyo",
                "Superman",
                getString(R.string.lateral_Lunges)
            )
            6 -> listOf(
                "Jump Rope (Calentamiento)",
                "Rodillas arriba (Calentamiento)",
                getString(R.string.jumping_Lunges),
                "Flexiones inclinadas",
                "Abdominales en V",
                "Plancha con movimientos laterales",
                "Crunches con piernas flexionadas",
                "Elevaciones de pierna lateral",
                "Superman con brazos extendidos",
                "Saltos laterales",
                getString(R.string.isometric_squat),
                "Levantamiento de rodillas en plancha"
            )
            7 -> listOf(
                "Jumping Jacks (Calentamiento)",
                "Shoulder Rolls (Calentamiento)",
                "Estocadas con pausa",
                "Flexiones normales",
                "Rotaciones de torso",
                getString(R.string.jump_squats),
                getString(R.string.hip_Raise),
                "Plancha",
                "Superman",
                "Elevación de piernas acostado",
                "Giros rusos",
                "Abdominales bicicleta"
            )
            8 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                getString(R.string.lunges),
                "Elevaciones de pierna lateral",
                "Abdominales en V",
                "Flexiones de tríceps en banco",
                "Plancha con apoyo de codos",
                getString(R.string.hip_Raise),
                "Superman con brazos extendidos",
                "Crunches con piernas flexionadas",
                getString(R.string.squat_with_pause),
                "Saltos de lado a lado"
            )
            9 -> listOf(
                "Jump Rope (Calentamiento)",
                "Arm Circles (Calentamiento)",
                "Step-ups",
                "Flexiones inclinadas",
                "Plancha lateral",
                "Rotación de cintura",
                "Elevación de talones",
                "Abdominales bicicleta",
                getString(R.string.lateral_Lunges),
                getString(R.string.isometric_squat),
                "Giros rusos",
                "Superman con patada de rana"
            )
            10 -> listOf(
                "Jumping Jacks (Calentamiento)",
                "Rodillas arriba (Calentamiento)",
                "Elevación de talones con apoyo",
                "Superman",
                "Plancha frontal",
                "Flexiones con apoyo de rodillas",
                "Crunches con piernas flexionadas",
                "Rotaciones de torso",
                "Step-ups",
                "Elevaciones de pierna lateral",
                "Abdominales cortos",
                "Saltos laterales"
            )
            else -> listOf("No hay rutina para este día.")
        }
    }

}
