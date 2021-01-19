package com.lwseasy.easycustomtab_sample

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainTabAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(
        fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    override fun getItem(position: Int): Fragment {
        return CustomFragment.newInstance(position.toString())
    }

    override fun getCount(): Int {
        return 4
    }
}