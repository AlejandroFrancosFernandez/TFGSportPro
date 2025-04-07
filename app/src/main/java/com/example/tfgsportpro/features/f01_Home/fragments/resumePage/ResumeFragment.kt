package com.example.tfgsportpro.features.f01_Home.fragments.resumePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.tfgsportpro.databinding.FragmentResumeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import com.example.tfgsportpro.R

class ResumeFragment : Fragment() {

    lateinit var binding: FragmentResumeBinding

    private val firestore = FirebaseFirestore.getInstance() // Instancia de Firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResumeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el UID del usuario actual
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        // Lista de niveles
        val levels = listOf("low", "medium", "high")

        // Iterar sobre los niveles y los días completados
        for (level in levels) {
            for (day in 1..30) {  // Asumiendo que la rutina tiene un máximo de 30 días
                // Obtener las rutinas completadas desde Firestore
                firestore.collection("users")
                    .document(currentUserUid)
                    .collection("routines")
                    .whereEqualTo("level", level)
                    .whereEqualTo("day", day)
                    .get()
                    .addOnSuccessListener { result ->
                        if (!result.isEmpty) {
                            // Si se encuentran rutinas completadas, procesarlas
                            result.documents.forEach { document ->
                                val completedDate = document.getLong("timestamp")
                                if (completedDate != null) {
                                    // Ajustar la zona horaria a la de España
                                    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                                    sdf.timeZone = TimeZone.getTimeZone("Europe/Madrid")
                                    val dateString = sdf.format(Date(completedDate))

                                    // Aseguramos que el contexto esté disponible
                                    context?.let { context ->
                                        // Crear la CardView para mostrar la rutina completada
                                        val cardView = CardView(context)
                                        val layoutParams = ViewGroup.MarginLayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT
                                        )
                                        layoutParams.setMargins(0, 0, 0, 5)  // Margen de 5dp entre cards
                                        cardView.layoutParams = layoutParams
                                        cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorBoton))
                                        cardView.radius = 16f

                                        val textView = TextView(context)
                                        textView.text = "Rutina completada: Día $day - Nivel $level\nFecha: $dateString"
                                        textView.setPadding(16, 16, 16, 16)
                                        textView.setTextColor(ContextCompat.getColor(context, R.color.colorLetra))

                                        cardView.addView(textView)

                                        // Añadir la CardView al contenedor en el layout del fragmento
                                        binding.containerResume.addView(cardView)
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

    // Método para guardar una rutina completada en Firestore
    fun saveCompletedRoutine(day: Int, level: String) {
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val timestamp = System.currentTimeMillis()  // Marca de tiempo actual

        val routineData = hashMapOf(
            "day" to day,
            "level" to level,
            "timestamp" to timestamp
        )

        firestore.collection("users")
            .document(currentUserUid)
            .collection("routines")
            .add(routineData)
            .addOnSuccessListener {
                // La rutina se ha guardado correctamente
                println("Rutina completada guardada exitosamente.")
            }
            .addOnFailureListener {
                // Error al guardar la rutina
                println("Error al guardar rutina: ${it.message}")
            }
    }
}
