package com.example.tfgsportpro.features.f01_Home.fragments.mePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentMeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(layoutInflater)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            binding.tvEmail.text = user.email ?: "Email Not Found"
            binding.tvName.text = user.displayName ?: "Nombre Not Found"

            val db = FirebaseFirestore.getInstance()
            val userDocRef = db.collection("User").document(user.uid)
            userDocRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
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

                    val streak = document.getLong("streak") ?: 0
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

        return binding.root
    }
}


