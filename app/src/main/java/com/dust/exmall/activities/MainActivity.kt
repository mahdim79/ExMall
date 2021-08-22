package com.dust.exmall.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dust.exmall.R
import com.dust.exmall.fragments.main.CartFragment
import com.dust.exmall.fragments.main.HomeFragment
import com.dust.exmall.fragments.main.MyExMallFragment
import com.dust.exmall.fragments.main.ProductsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var PAGE_NUMBER = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
        setUpBottomNavigationView()
    }

    /* fun checkCurrentPage(pageNum: Int): Boolean = PAGE_NUMBER == pageNum

     fun setCurrentPage(pageNum: Int) {
         PAGE_NUMBER = pageNum
     }*/

    private fun setUpBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener { item ->

            if (!item.isChecked) {
                when (item.itemId) {
                    R.id.nav_home -> {
                        startFragment(HomeFragment())
                    }
                    R.id.nav_products -> {
                        startFragment(ProductsFragment())
                    }
                    R.id.nav_cart -> {
                        startFragment(CartFragment())
                    }
                    else -> {
                        startFragment(MyExMallFragment())
                    }
                }
                true
            } else {
                false
            }
        }
    }

    fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun setUpViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
    }
}