package com.example.tfgsportpro.features.f03_profile.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrainingFragment()
            1 -> TrainingMediumFragment()
            2 -> TrainingHighFragment()
            else -> TrainingFragment()
        }
    }
}