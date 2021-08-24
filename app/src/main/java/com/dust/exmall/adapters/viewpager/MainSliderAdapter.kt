package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.fragments.sliderviewpagerfragments.MainSliderItem

class MainSliderAdapter(fragmentManager: FragmentManager):FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 4;
    }

    override fun getItem(position: Int): Fragment {
        return MainSliderItem().newInstance()
    }
}