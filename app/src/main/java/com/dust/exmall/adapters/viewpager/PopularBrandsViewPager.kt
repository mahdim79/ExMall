package com.dust.exmall.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dust.exmall.fragments.others.PopularBrandsFragment

class PopularBrandsViewPager(fragmentManager: FragmentManager):FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int = 11

    override fun getItem(position: Int): Fragment {
        return PopularBrandsFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "کالای دیجیتال"
            1 -> "خودرو، ابزار و تجهیزات صنعتی"
            2 -> "مد و پوشاک"
            3 -> "اسباب بازی، کودک و نوزاد"
            4 -> "کالاهای سوپرمارکتی"
            5 -> "زیبایی و سلامت"
            6 -> "خانه و آشپزخانه"
            7 -> "کتاب، لوازم تحریر و هنر"
            8 -> "ورزش و سفر"
            9 -> "محصولات بومی و محلی"
            else -> "کارت هدیه"
        }
    }
}