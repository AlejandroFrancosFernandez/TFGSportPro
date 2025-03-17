package com.example.tfgsportpro.features.f03_profile.incognita

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tfgsportpro.features.f04_TrainingLevels_Page.TrainingHighFragment
import com.example.tfgsportpro.features.f04_TrainingLevels_Page.TrainingMediumFragment
import com.example.tfgsportpro.features.f04_Training_Page.TrainingFragment

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