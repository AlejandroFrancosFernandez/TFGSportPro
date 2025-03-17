package com.example.tfgsportpro.features.f02_resume_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentResumeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ResumeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResumeFragment : Fragment() {

    lateinit var binding: FragmentResumeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

}