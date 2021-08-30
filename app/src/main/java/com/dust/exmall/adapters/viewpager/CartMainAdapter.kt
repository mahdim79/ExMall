package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.fragments.cartfragments.MainCartFragment
import com.dust.exmall.fragments.cartfragments.NextCartFragment

class CartMainAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return if (position == 0)
            NextCartFragment()
        else
            MainCartFragment()

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0)
            "سبد خرید بعدی"
        else
            "سبد خرید"

    }
}