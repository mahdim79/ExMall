package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.dataclasses.AmazingDataClass
import com.dust.exmall.fragments.others.ProductsSliderFragment

class ProductsSliderAdapter(fragmentManager: FragmentManager , var list:List<AmazingDataClass>) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        return ProductsSliderFragment(optimizeCurrentItems(position , list) , position)
    }

    private fun optimizeCurrentItems(position: Int ,list:List<AmazingDataClass>):List<AmazingDataClass>{
        return list.subList((position * 3) , (position * 3) + 3)
    }
}