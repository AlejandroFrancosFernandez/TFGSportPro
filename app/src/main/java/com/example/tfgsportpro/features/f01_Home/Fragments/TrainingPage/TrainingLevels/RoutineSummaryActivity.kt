package com.example.tfgsportpro.features.f01_Home.Fragments.TrainingPage.TrainingLevels

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        // Se decide qué rutina usar según el nivel
        val routine = when (level) {
            "low" -> getRoutineForDayLow(day)
            "medium" -> getRoutineForDayMedium(day)
            "high" -> getRoutineForDayHigh(day)
            else -> getRoutineForDayLow(day)
        }

        // Obtener el número total de ejercicios
        val totalExercises = routine.size

        // Establecer el texto de la rutina
        val warmUpExercises = routine.take(2).joinToString("\n") { "• $it" }
        val mainExercises = routine.drop(2).joinToString("\n") { "• $it" }

        binding.tvRoutineDetails.text = "Nivel: $level\nEjercicios: $totalExercises"
        binding.tvExerciseList.text = "Total de ejercicios: $totalExercises"  // Solo muestra el total

        binding.tvWarmUpDetails.text = warmUpExercises
        binding.tvOtherExerciseDetails.text = mainExercises

        binding.tvInfoday.text = "Día $day"
    }



    private fun getRoutineForDayLow(day: Int): List<String> {
        return when (day) {
            1 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                "Zancadas alternas",
                "Elevación de talones (pantorrillas)",
                "Flexiones normales",
                "Giros de torso",
                "Plancha frontal",
                "Abdominales crunch",
                "Superman",
                "Sentadillas",
                "Step-ups",
                "Puente de glúteos"
            )
            2 -> listOf(
                "Saltos tijera (Calentamiento)",
                "Jumping squats (Calentamiento)",
                "Subir escaleras",
                "Plancha con apoyo de codos",
                "Levantamiento de rodillas en plancha",
                "Rotación de cintura",
                "Puente de glúteos a una pierna",
                "Abdominales bicicleta",
                "Sentadilla isométrica",
                "Elevación de caderas",
                "Flexiones apoyando rodillas",
                "Elevación de pierna lateral"
            )
            3 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Skipping bajo (Calentamiento)",
                "Abdominales en V",
                "Extensión de piernas sentado",
                "Puente de glúteos con apertura de rodillas",
                "Elevaciones de talones en un escalón",
                "Flexiones inclinadas (apoyadas en superficie elevada)",
                "Superman",
                "Sentadillas con pausa abajo",
                "Saltos laterales",
                "Zancadas laterales",
                "Plancha con toques de hombros"
            )
            4 -> listOf(
                "Saltos tijera (Calentamiento)",
                "Rodillas arriba (Calentamiento)",
                "Elevaciones de pierna a gatas",
                "Plancha lateral (15 seg por lado)",
                "Giros rusos",
                "Jumping jacks lentos",
                "Abdominales cruzados",
                "Sentadillas sumo",
                "Estocadas hacia atrás",
                "Flexiones normales",
                "Pasos laterales en sentadilla",
                "Elevación de caderas con una pierna extendida"
            )
            5 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Jumping jacks (Calentamiento)",
                "Zancadas con salto",
                "Plancha",
                "Superman",
                "Elevación de talones con apoyo",
                "Crunches con piernas elevadas",
                "Sentadillas con toque de talón",
                "Step-ups",
                "Flexiones",
                "Abdominales",
                "Elevación de pierna lateral"
            )
            6 -> listOf(
                "Jumping jacks (Calentamiento)",
                "Saltos en el sitio (Calentamiento)",
                "Superman",
                "Abdominales",
                "Step-ups",
                "Flexiones",
                "Elevación de pierna lateral",
                "Sentadillas",
                "Plancha",
                "Zancadas con salto",
                "Crunches con piernas elevadas",
                "Elevación de talones con apoyo"
            )
            7 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                "Abdominales en V",
                "Flexiones con apoyo de rodillas",
                "Sentadilla con salto",
                "Plancha con movimiento lateral",
                "Zancadas hacia atrás",
                "Elevación de piernas acostado",
                "Superman con patada de rana",
                "Elevación de caderas",
                "Step-ups",
                "Crunches con piernas flexionadas"
            )
            8 -> listOf(
                "Jumping jacks (Calentamiento)",
                "Rodillas arriba (Calentamiento)",
                "Estocadas con pausa",
                "Flexiones normales",
                "Sentadilla isométrica",
                "Plancha con movimientos laterales",
                "Abdominales cortos",
                "Elevación de piernas",
                "Puente de glúteos",
                "Superman con brazos extendidos",
                "Rotaciones de torso",
                "Saltos de lado a lado"
            )
            9 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                "Abdominales en V",
                "Flexiones con apoyo de rodillas",
                "Sentadilla con salto",
                "Plancha con movimiento lateral",
                "Zancadas hacia atrás",
                "Elevación de piernas acostado",
                "Superman con patada de rana",
                "Elevación de caderas",
                "Step-ups",
                "Crunches con piernas flexionadas"
            )
            10 -> listOf(
                "Jumping jacks (Calentamiento)",
                "Rodillas arriba (Calentamiento)",
                "Estocadas con pausa",
                "Flexiones normales",
                "Sentadilla isométrica",
                "Plancha con movimientos laterales",
                "Abdominales cortos",
                "Elevación de piernas",
                "Puente de glúteos",
                "Superman con brazos extendidos",
                "Rotaciones de torso",
                "Saltos de lado a lado"
            )
            else -> listOf("No hay rutina para este día.")
        }
    }

    private fun getRoutineForDayMedium(day: Int): List<String> {
        return when (day) {
            1 -> listOf(
                "Jumping Jacks (Calentamiento)",
                "Arm Circles (Calentamiento)",
                "Sentadillas",
                "Flexiones normales",
                "Zancadas alternas",
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
                "Sentadilla isométrica",
                "Rotación de cintura",
                "Plancha con toques de hombros",
                "Flexiones inclinadas",
                "Giros rusos",
                "Elevación de caderas",
                "Zancadas con salto",
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
                "Zancadas hacia atrás",
                "Elevación de caderas",
                "Rotaciones de torso",
                "Sentadillas con pausa abajo",
                "Superman con patada de rana"
            )
            5 -> listOf(
                "Jumping Jacks (Calentamiento)",
                "Arm Circles (Calentamiento)",
                "Plancha con movimiento lateral",
                "Sentadillas sumo",
                "Flexiones con apoyo de rodillas",
                "Step-ups",
                "Puente de glúteos",
                "Abdominales cortos",
                "Elevación de piernas acostado",
                "Elevación de talones con apoyo",
                "Superman",
                "Zancadas laterales"
            )
            6 -> listOf(
                "Jump Rope (Calentamiento)",
                "Rodillas arriba (Calentamiento)",
                "Zancadas con salto",
                "Flexiones inclinadas",
                "Abdominales en V",
                "Plancha con movimientos laterales",
                "Crunches con piernas flexionadas",
                "Elevaciones de pierna lateral",
                "Superman con brazos extendidos",
                "Saltos laterales",
                "Sentadilla isométrica",
                "Levantamiento de rodillas en plancha"
            )
            7 -> listOf(
                "Jumping Jacks (Calentamiento)",
                "Shoulder Rolls (Calentamiento)",
                "Estocadas con pausa",
                "Flexiones normales",
                "Rotaciones de torso",
                "Sentadilla con salto",
                "Elevación de caderas",
                "Plancha",
                "Superman",
                "Elevación de piernas acostado",
                "Giros rusos",
                "Abdominales bicicleta"
            )
            8 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                "Zancadas alternas",
                "Elevaciones de pierna lateral",
                "Abdominales en V",
                "Flexiones de tríceps en banco",
                "Plancha con apoyo de codos",
                "Elevación de caderas",
                "Superman con brazos extendidos",
                "Crunches con piernas flexionadas",
                "Sentadillas con pausa abajo",
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
                "Zancadas hacia atrás",
                "Sentadilla isométrica",
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

    private fun getRoutineForDayHigh(day: Int): List<String> {
        return when (day) {
            1 -> listOf(
                "Saltos en el sitio (Calentamiento)",
                "Jumping jacks intensos (Calentamiento)",
                "Flexiones controladas (descenso y ascenso lento, 5 seg)",
                "Abdominales bicicleta con pausa",
                "Zancadas con salto",
                "Elevación de pantorrillas a un pie",
                "Burpees sin flexión",
                "Superman con brazos extendidos",
                "Plancha con toque de hombros",
                "Sentadilla con salto explosivo",
                "Giros rusos con peso",
                "Puente de glúteos con una pierna"
            )
            2 -> listOf(
                "Saltos tijera (Calentamiento)",
                "Skipping alto (Calentamiento)",
                "Sentadillas con salto lateral",
                "Flexiones con palmada",
                "Zancadas con cambio explosivo",
                "Abdominales en V",
                "Superman con brazos y piernas extendidos",
                "Plancha a un pie (15 seg cada lado)",
                "Elevaciones de pierna lateral en plancha",
                "Plank con giro de cadera",
                "Mountain climbers a alta velocidad",
                "Puente de glúteos con peso"
            )
            3 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Jumping jacks intensos (Calentamiento)",
                "Flexiones en diamante",
                "Abdominales con piernas extendidas",
                "Saltos al cajón (o sobre un escalón)",
                "Sentadillas búlgaras (apoyando un pie en superficie elevada)",
                "Plancha con desplazamiento lateral",
                "Superman con movimiento de brazos",
                "Elevaciones de talón con una pierna",
                "Saltos de lado a lado",
                "Abdominales laterales",
                "Plancha con toque de rodilla al codo"
            )
            4 -> listOf(
                "Saltos tijera (Calentamiento)",
                "Sentadillas dinámicas (Calentamiento)",
                "Flexiones explosivas",
                "Abdominales tipo rana",
                "Sentadillas con salto a un pie",
                "Elevaciones de pierna en plancha",
                "Superman con extensión de brazos",
                "Zancadas cruzadas",
                "Plancha con rotación de torso",
                "Mountain climbers a velocidad alta",
                "Puente de glúteos con una pierna",
                "Plancha con toque de hombro"
            )
            5 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Jumping jacks (Calentamiento)",
                "Flexiones controladas con pausa abajo",
                "Zancadas laterales profundas",
                "Abdominales bicicleta con elevación de piernas",
                "Elevaciones de talón con carga extra",
                "Superman con pausa arriba",
                "Crunches con piernas en 90°",
                "Burpees completos",
                "Sentadillas con salto y giro",
                "Plancha con paso lateral",
                "Plancha a un pie"
            )
            6 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                "Flexiones diamante con control",
                "Burpees con salto alto",
                "Plancha con desplazamiento de brazos",
                "Zancadas explosivas",
                "Elevación de piernas en L",
                "Superman nadador",
                "Puente de glúteos con resistencia",
                "Elevaciones de talón con pausa arriba",
                "Abdominales con toque de talón",
                "Sentadilla isométrica con salto final"
            )
            7 -> listOf(
                "Jumping jacks (Calentamiento)",
                "Saltos en el sitio (Calentamiento)",
                "Flexiones con palmada en el pecho",
                "Zancadas alternas rápidas",
                "Abdominales en V con giro",
                "Plancha con elevación alterna de brazo",
                "Superman con elevación simultánea",
                "Elevación de talón con una pierna y peso",
                "Sprint en el sitio",
                "Sentadilla con salto y pausa de 3 seg abajo",
                "Plank con toque de rodilla al codo",
                "Plancha con cambio de apoyo"
            )
            8 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Saltos tijera (Calentamiento)",
                "Flexiones controladas con pausa de 2 seg abajo",
                "Sentadilla con salto a un pie",
                "Zancadas con salto lateral",
                "Abdominales laterales",
                "Crunches con piernas estiradas",
                "Superman con giro de torso",
                "Elevaciones de talón con cambio de ritmo",
                "Mountain climbers con cambio de ritmo",
                "Plancha con codos y giro de cadera",
                "Plancha con toques alternos en rodilla"
            )
            9 -> listOf(
                "Jumping jacks (Calentamiento)",
                "Skipping alto (Calentamiento)",
                "Flexiones con pausa y subida rápida",
                "Sentadilla sumo con pausa",
                "Elevaciones de talón con resistencia",
                "Plancha con movimientos de brazos",
                "Zancadas con cambio explosivo",
                "Crunches con elevación de cadera",
                "Abdominales en L",
                "Superman con estiramiento lateral",
                "Saltos a un pie alternando",
                "Plancha con codos y toques laterales"
            )
            10 -> listOf(
                "Rodillas arriba (Calentamiento)",
                "Sentadillas con salto (Calentamiento)",
                "Flexiones diamante con control total",
                "Sentadilla con pausa y explosión final",
                "Puente de glúteos a una pierna",
                "Elevaciones de talón lentas y controladas",
                "Abdominales con brazos extendidos",
                "Plancha con desplazamiento lateral",
                "Superman con extensión de piernas y brazos",
                "Sprint en el sitio",
                "Zancadas cruzadas con pausa",
                "Plank con giro de torso"
            )
            else -> listOf("No hay rutina para este día.")
        }
    }
}
