package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels

import MediumRoutine
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityRoutineExerciseBinding
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.LowRoutine
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.HighRoutine
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.Exercise
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.routines.exercises.LowIntensityExercises

class RoutineExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutineExerciseBinding

    // Lista de ejercicios para la rutina actual
    private lateinit var routine: List<Exercise>
    private var currentExerciseIndex = 0

    // Timer para el ejercicio actual
    private var countDownTimer: CountDownTimer? = null
    private var timeLeft: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutineExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar el nivel y el día desde el Intent
        val level = intent.getStringExtra("level") ?: "low"
        val day = intent.getIntExtra("day", 1)

        // Seleccionar la rutina según el nivel recibido
        routine = when (level.lowercase()) {
            "low" -> LowRoutine().getRoutineForDayLow(day)
            "medium" -> MediumRoutine().getRoutineForDayMedium(day)
            "high" -> HighRoutine().getRoutineForDayHigh(day)
            else -> LowRoutine().getRoutineForDayLow(day)
        }
        // Iniciar el primer ejercicio
        startExercise(currentExerciseIndex)

        // Configurar botones
        binding.btnPause.setOnClickListener {
            if (countDownTimer != null) {
                pauseTimer()
            } else {
                resumeTimer()
            }
        }

        binding.btnNext.setOnClickListener {
            nextExercise()
        }

        binding.btnPrevious.setOnClickListener {
            previousExercise()
        }
    }

    private fun startExercise(index: Int) {
        if (index in routine.indices) {
            val exercise = routine[index]
            // Actualizar la UI: muestra el nombre del ejercicio.
            // Cuando dispongas de imágenes, podrías actualizar la ImageView con un recurso adecuado.
            binding.tvExerciseName.text = getString(exercise.nombreResId)

            // Configurar la duración del ejercicio (convertir segundos a milisegundos)
            timeLeft = exercise.duracion * 1000L
            updateTimerText()
            startTimer()
        }
    }

    private fun startTimer() {
        // Cancelar el timer anterior si existe
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateTimerText()
            }
            override fun onFinish() {
                // Al finalizar el tiempo del ejercicio, avanzar al siguiente
                nextExercise()
            }
        }.start()
        binding.btnPause.text = "Pausa"
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
        binding.btnPause.text = "Reanudar"
    }

    private fun resumeTimer() {
        startTimer()
    }

    private fun updateTimerText() {
        val seconds = (timeLeft / 1000) % 60
        val minutes = (timeLeft / 1000) / 60
        binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun nextExercise() {
        if (currentExerciseIndex < routine.size - 1) {
            currentExerciseIndex++
            startExercise(currentExerciseIndex)
        } else {
            Toast.makeText(this, "¡Rutina completada!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun previousExercise() {
        if (currentExerciseIndex > 0) {
            currentExerciseIndex--
            startExercise(currentExerciseIndex)
        }
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        super.onDestroy()
    }
}
