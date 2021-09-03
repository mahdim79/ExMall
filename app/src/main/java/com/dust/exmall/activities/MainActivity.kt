package com.dust.exmall.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.viewpager.MainAdapter
import com.dust.exmall.customviews.CViewPager
import com.dust.exmall.fragments.main.CartFragment
import com.dust.exmall.fragments.main.HomeFragment
import com.dust.exmall.fragments.main.MyExMallFragment
import com.dust.exmall.fragments.main.ProductsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var mainViewPager:CViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
        setUpMainViewPager()
        setUpBottomNavigationView()
    }

    private fun setUpMainViewPager() {
        mainViewPager.offscreenPageLimit = 3
        mainViewPager.adapter = MainAdapter(supportFragmentManager)
    }

    private fun setUpBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener { item ->
             when (item.itemId) {
                    R.id.nav_home -> mainViewPager.currentItem = 0
                    R.id.nav_products -> mainViewPager.currentItem = 1
                    R.id.nav_cart -> mainViewPager.currentItem = 2
                    else -> mainViewPager.currentItem = 3
                }
                true
        }
    }


    private fun setUpViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        mainViewPager = findViewById(R.id.mainViewPager)

    }
}