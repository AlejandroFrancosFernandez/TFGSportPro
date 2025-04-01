package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgsportpro.R

class DaysAdapter(
    private val daysList: List<Int>,
    private val layoutResId: Int,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnDay: Button = itemView.findViewById(R.id.btnDay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = daysList[position]
        holder.btnDay.text = "Día $day"

        holder.btnDay.setOnClickListener {
            onClick(day)
        }
    }

    override fun getItemCount(): Int = daysList.size
}