package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.fragments.others.ProductImagesFragment

class ProductImagesAdapter(fragmentManager: FragmentManager , var urlList:List<String>):FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = urlList.size

    override fun getItem(position: Int): Fragment {
        return ProductImagesFragment().newInstance(urlList[position])
    }
}