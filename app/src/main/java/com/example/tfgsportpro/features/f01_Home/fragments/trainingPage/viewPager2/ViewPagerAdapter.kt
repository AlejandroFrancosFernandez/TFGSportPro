package com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.viewPager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.TrainingHighFragment
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.TrainingLowFragment
import com.example.tfgsportpro.features.f01_Home.fragments.trainingPage.trainingLevels.TrainingMediumFragment

class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrainingLowFragment()
            1 -> TrainingMediumFragment()
            2 -> TrainingHighFragment()
            else -> TrainingLowFragment()
        }
    }
}