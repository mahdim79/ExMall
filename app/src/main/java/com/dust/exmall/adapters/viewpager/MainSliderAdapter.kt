package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.fragments.sliderviewpagerfragments.MainSliderItem

class MainSliderAdapter(fragmentManager: FragmentManager , var pairList:List<Pair<String , String>>):FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return pairList.size
    }

    override fun getItem(position: Int): Fragment {
        return MainSliderItem().newInstance(pairList[position].first , pairList[position].second)
    }
}