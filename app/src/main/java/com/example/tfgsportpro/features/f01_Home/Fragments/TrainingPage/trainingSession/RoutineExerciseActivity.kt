package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingSession

import com.example.tfgsportpro.features.f01_Home.domain.routines.MediumRoutine
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ActivityRoutineExerciseBinding
import com.example.tfgsportpro.features.f01_Home.domain.routines.LowRoutine
import com.example.tfgsportpro.features.f01_Home.domain.routines.HighRoutine
import com.example.tfgsportpro.features.f01_Home.domain.model.Exercise
import com.google.firebase.auth.FirebaseAuth

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

        // Mostrar la animación de cuenta atrás antes de empezar la rutina
        showCountdownAnimation()

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

        binding.bPause.setOnClickListener {
            if (countDownTimer != null) {
                pauseTimer()
            } else {
                resumeTimer()
            }
        }

        binding.bNext.setOnClickListener {
            nextExercise()
        }

        binding.bPrevious.setOnClickListener {
            previousExercise()
        }

        binding.bBackroutine.setOnClickListener {
            binding.bBackroutine.isEnabled = false

            binding.bBackroutine.postDelayed({
                countDownTimer?.cancel()
                finish()
            }, 1000)
        }
    }

    // Método para mostrar la animación de cuenta atrás
    private fun showCountdownAnimation() {
        Glide.with(this)
            .asGif()
            .load(R.drawable.cargarutina)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.ivExercise)

        // Configurar un timer para esperar antes de empezar la rutina
        object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                // Después de 5 segundos, ocultar la animación de cuenta atrás
                binding.ivExercise.visibility = View.GONE  // Ocultar el ImageView con el GIF

                startExercise(currentExerciseIndex)
            }
        }.start()
    }

    private fun startExercise(index: Int) {
        if (index in routine.indices) {
            val exercise = routine[index]

            // Actualizar el nombre del ejercicio en el TextView
            binding.tvExerciseName.text = getString(exercise.nombreResId)

            // Asegurarse de que el ImageView sea visible antes de cargar el GIF del ejercicio
            binding.ivExercise.visibility = View.VISIBLE

            // Cargar el GIF en el ImageView si el GIF está disponible
            if (exercise.gifResId != null) {
                Glide.with(this)
                    .load(exercise.gifResId)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.ivExercise)  // Establecer en el ImageView
            } else {
                // Si no hay GIF, puedes colocar una imagen predeterminada o dejar el ImageView vacío
                Glide.with(this)
                    .clear(binding.ivExercise)  // Limpiar la imagen si no hay GIF
            }

            // Configurar la duración del ejercicio (convertir segundos a milisegundos)
            timeLeft = exercise.duracion * 1000L
            updateTimerText()
            startTimer()
        }
    }

    private fun startTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateTimerText()
            }
            override fun onFinish() {
                nextExercise()
            }
        }.start()
        binding.bPause.text = getString(R.string.b_pause)
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
        binding.bPause.text = getString(R.string.b_resume)
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
            val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            val day = intent.getIntExtra("day", 1)
            val level = intent.getStringExtra("level") ?: "low"
            val key = "${currentUserUid}_${level}_day_$day"
            val sharedPreferences = getSharedPreferences("CompletedDays", MODE_PRIVATE)
            sharedPreferences.edit().putBoolean(key, true).apply()
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
        countDownTimer = null
        try {
            Glide.with(this).clear(binding.ivExercise)
        } catch (e: Exception) {
        }
        super.onDestroy()
    }

}
