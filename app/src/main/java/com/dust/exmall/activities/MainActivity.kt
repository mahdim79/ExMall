package com.dust.exmall.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.viewpager.MainAdapter
import com.dust.exmall.customviews.CViewPager
import com.dust.exmall.fragments.main.CartFragment
import com.dust.exmall.fragments.main.HomeFragment
import com.dust.exmall.fragments.main.MyExMallFragment
import com.dust.exmall.fragments.main.ProductsFragment
import com.dust.exmall.fragments.others.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var mainViewPager:CViewPager
    private lateinit var startFragment:StartFragment

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

    override fun onStart() {
        startFragment = StartFragment()
        registerReceiver(startFragment , IntentFilter("com.dust.exmall.StartFragment"))
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(startFragment)
        super.onStop()
    }

    inner class StartFragment : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            var fragment:Fragment? = null
            when(p1!!.extras!!.getString("FRAGMENT_NAME")){
                "SearchFragment" -> fragment = SearchFragment()
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , fragment!!)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(p1.extras!!.getString("FRAGMENT_NAME"))
                .commit()
        }
    }
}