package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.fragments.main.CartFragment
import com.dust.exmall.fragments.main.HomeFragment
import com.dust.exmall.fragments.main.MyExMallFragment
import com.dust.exmall.fragments.main.ProductsFragment

class MainAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> ProductsFragment()
            2 -> CartFragment()
            else -> MyExMallFragment()
        }
    }
}