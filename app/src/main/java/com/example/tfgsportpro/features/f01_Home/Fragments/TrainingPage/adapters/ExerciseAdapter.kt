package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.ItemExerciseBinding
import com.example.tfgsportpro.features.f01_Home.domain.model.Exercise

class ExerciseAdapter(
    private val exercises: List<Exercise>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ex: Exercise) = with(binding) {
            // Nombre
            tvName.text = root.context.getString(ex.nombreResId)

            // Duración (en segundos)
            tvDuration.text = "${ex.duracion}s"

            // GIF (si hay)
            if (ex.gifResId != null) {
                imgGif.visibility = View.VISIBLE
                Glide.with(root)
                    .asGif()
                    .load(ex.gifResId)
                    .into(imgGif)
            } else {
                imgGif.visibility = View.GONE
            }

            // Si quisieras diferenciar BREAK:
            if (ex.nombreResId == R.string.BREAK) {
                root.setBackgroundResource(R.color.DarkGrey)
                tvName.textAlignment = View.TEXT_ALIGNMENT_CENTER
            } else {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exercises[position])
    }

    override fun getItemCount(): Int = exercises.size
}
