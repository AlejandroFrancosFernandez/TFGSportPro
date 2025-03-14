package com.example.tfgsportpro.features.f03_profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.databinding.FragmentMeBinding
import com.google.firebase.auth.FirebaseAuth

class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeBinding.inflate(inflater, container, false)

        // Obtener el usuario autenticado
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            binding.tvUid.text = user.uid
            binding.tvName.text = user.displayName ?: "Nombre no disponible"
        } else {
            binding.tvUid.text = "No autenticado"
            binding.tvName.text = "No autenticado"
        }

        // Botón de logout
        binding.bLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            // Aquí podrías redirigir al usuario a la pantalla de login, si lo deseas.
        }

        return binding.root
    }
}

