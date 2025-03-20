package com.example.tfgsportpro.features.f01_Home.Fragments.MePage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentMeBinding
import com.example.tfgsportpro.features.f00_Auth.Login.UI.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(layoutInflater)

        // Obtener el usuario autenticado
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            binding.tvEmail.text = user.email ?: "Email Not Found"
            binding.tvName.text = user.displayName ?: "Nombre Not Found"

            val db = FirebaseFirestore.getInstance()
            val userDocRef = db.collection("User").document(user.uid)
            userDocRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    // Si displayName es null, intentamos obtenerlo desde Firestore con la clave correcta "Name"
                    val displayName = document.getString("Name") ?: document.getString("name") ?: "Nombre no disponible"
                    binding.tvName.text = displayName

                    val age = document.getString("Age")
                    if (age != null) {
                        binding.tvAge.text = age
                    } else {
                        binding.tvAge.text = "Edad no disponible"
                    }

                    val physicalLevel = document.getString("PhysicalLevel")
                    if (physicalLevel != null) {
                        binding.tvPhysicallevel.text = physicalLevel
                    } else {
                        binding.tvPhysicallevel.text = "Nivel físico no disponible"
                    }

                    // Obtener la racha
                    val streak = document.getLong("streak") ?: 0
                    // Mostrar la racha en el TextView
                    binding.RachaDias.text = "Racha de días consecutivos: $streak"
                } else {
                    binding.tvAge.text = "Not found"
                    binding.tvPhysicallevel.text = "Not found"
                }
            }.addOnFailureListener { exception ->
                binding.tvAge.text = "Error loading data"
                binding.tvPhysicallevel.text = "Error loading data"
            }

        } else {
            binding.tvName.text = getString(R.string.infoNotauthenticated)
            binding.tvEmail.text = getString(R.string.infoNotauthenticated)
            binding.tvAge.text = getString(R.string.infoNotauthenticated)
            binding.tvPhysicallevel.text = getString(R.string.infoNotauthenticated)
        }

        binding.bLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            // Limpiar SharedPreferences del LoginActivity
            requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit().apply {
                remove("email")
            }.apply()

            // Volver al Login para volver a logarse/registrarse
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }
}


