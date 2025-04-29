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

        //Cargar los datos desde firestore en el fragment, cogiendo el UID del usuario logeado
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            binding.tvEmail.text = user.email ?: R.string.not_found.toString()
            binding.tvName.text = user.displayName ?: R.string.not_found.toString()

            val db = FirebaseFirestore.getInstance()
            val userDocRef = db.collection("User").document(user.uid)
            userDocRef.get().addOnSuccessListener { document ->
                if (!isAdded || context == null) return@addOnSuccessListener

                if (document.exists()) {
                    val displayName = document.getString("Name") ?: document.getString("name")
                    binding.tvName.text = displayName

                    val age = document.getString("Age")
                    if (age != null) {
                        binding.tvAge.text = age
                    } else {
                        binding.tvAge.text = getString(R.string.age_not_available)
                    }

                    val physicalLevel = document.getString("PhysicalLevel")
                    if (physicalLevel != null) {
                        binding.tvPhysicallevel.text = physicalLevel
                    } else {
                        binding.tvPhysicallevel.text = getString(R.string.physical_activity_not_available)
                    }

                    val streak = document.getLong("streak") ?: 0
                    val streakText = getString(R.string.consecutive_daysStreak, streak)
                    binding.RachaDias.text = streakText

                } else {
                    binding.tvAge.text = R.string.not_found.toString()
                    binding.tvPhysicallevel.text = R.string.not_found.toString()
                }
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


