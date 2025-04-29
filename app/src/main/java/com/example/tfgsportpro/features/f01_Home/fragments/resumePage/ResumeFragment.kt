package com.example.tfgsportpro.features.f01_Home.fragments.resumePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentResumeBinding

class ResumeFragment : Fragment() {

    private lateinit var binding: FragmentResumeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResumeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerResume, RoutinesCompletedFragment())
            .commit()

        binding.bNavigationResume.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.navRoutinesCompleted -> RoutinesCompletedFragment()
                R.id.navGraphic -> GraphicFragment()
                else -> null
            }
            if (fragment != null) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerResume, fragment)
                    .commit()
                true
            } else false
        }
    }
}
