package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.others.ProductsSliderFragment

class ProductsSliderAdapter(fragmentManager: FragmentManager , var list:List<ProductsDataClass> , var highReviewedMode:Boolean = false) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        return ProductsSliderFragment(optimizeCurrentItems(position , list) , position , highReviewedMode)
    }

    private fun optimizeCurrentItems(position: Int ,list:List<ProductsDataClass>):List<ProductsDataClass>{
        return list.subList((position * 3) , (position * 3) + 3)
    }
}