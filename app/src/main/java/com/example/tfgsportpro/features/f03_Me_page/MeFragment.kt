package com.example.tfgsportpro.features.f03_Me_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.databinding.FragmentMeBinding
import com.example.tfgsportpro.features.f00_login_register.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(inflater, container, false)

        // Obtener el usuario autenticado
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            // Mostrar algunos datos desde FirebaseAuth
            binding.tvEmail.text = user.email ?: "Email no disponible"
            binding.tvName.text = user.displayName ?: "Nombre no disponible"

            // Recuperar datos adicionales (Edad y Nivel físico) desde Firestore
            val db = FirebaseFirestore.getInstance()
            val userDocRef = db.collection("User").document(user.uid)
            userDocRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    // Cargar edad y nivel físico
                    binding.tvAge.text = document.getString("Age") ?: "Edad no disponible"
                    binding.tvPhysicallevel.text = document.getString("PhysicalLevel") ?: "Nivel físico no disponible"
                } else {
                    binding.tvAge.text = "Datos del usuario no encontrados"
                    binding.tvPhysicallevel.text = "Datos del usuario no encontrados"
                }
            }.addOnFailureListener { exception ->
                binding.tvAge.text = "Error al cargar datos"
                binding.tvPhysicallevel.text = "Error al cargar datos"
            }
        } else {
            binding.tvName.text = "No autenticado"
            binding.tvEmail.text = "No autenticado"
            binding.tvAge.text = "No autenticado"
            binding.tvPhysicallevel.text = "No autenticado"
        }

        // Botón de logout: al presionar, cierra la sesión y vuelve a MainActivity
        binding.bLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }
}
