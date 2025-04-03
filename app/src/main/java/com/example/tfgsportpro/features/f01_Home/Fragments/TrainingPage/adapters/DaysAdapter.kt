package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgsportpro.R
import com.google.firebase.auth.FirebaseAuth

class DaysAdapter(
    private val daysList: List<Int>,
    private val layoutResId: Int,
    private val level: String,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnDay: Button = itemView.findViewById(R.id.btnDay)
        val imgLine: ImageView = itemView.findViewById(R.id.imgLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = daysList[position]
        val context = holder.itemView.context
        holder.btnDay.text = context.getString(R.string.day, day)

        // Define el color base según el nivel
        val baseColorRes = when (level) {
            "low" -> R.color.btn_line_low
            "medium" -> R.color.btn_line_medium
            "high" -> R.color.btn_line_high
            else -> R.color.btn_line_low
        }

        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        // Construir la clave personalizada para el día actual
        val key = "${currentUserUid}_${level}_day_$day"

        // Obtener el estado del día a través de SharedPreferences usando la clave personalizada
        val sharedPreferences = context.getSharedPreferences("CompletedDays", Context.MODE_PRIVATE)
        val isCompleted = sharedPreferences.getBoolean(key, false)

        // Determinar el color final: verde si completado, o el color base según el nivel
        val finalColorRes = if (isCompleted) R.color.diaRealizado else baseColorRes
        holder.imgLine.setColorFilter(ContextCompat.getColor(context, finalColorRes))

        // Asignar el click listener
        holder.btnDay.setOnClickListener {
            onClick(day)
        }
    }

    override fun getItemCount(): Int = daysList.size
}