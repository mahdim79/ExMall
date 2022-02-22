package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.dataclasses.CardsDataClass
import com.dust.exmall.fragments.others.specialfragments.innerfragments.SpecialHeaderViewPagerFragment

class SpecialHeaderAdapter(fragmentManager: FragmentManager, var dataList: List<CardsDataClass>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = dataList.size

    override fun getItem(position: Int): Fragment =
        SpecialHeaderViewPagerFragment(dataList, position)
}