package com.example.myscreensaver

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, var activity: Activity): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return Utils.getCategories().size
    }

    override fun createFragment(position: Int): Fragment {
        return MainViewFragment(activity, position)
    }
}