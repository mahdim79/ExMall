package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.dataclasses.CardsDataClass
import com.dust.exmall.fragments.sliderviewpagerfragments.MainSliderItem

class MainSliderAdapter(fragmentManager: FragmentManager , var dataList: List<CardsDataClass>):FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Fragment {
        return MainSliderItem(dataList , position)
    }
}