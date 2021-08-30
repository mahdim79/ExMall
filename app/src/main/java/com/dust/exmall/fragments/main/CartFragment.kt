package com.dust.exmall.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.viewpager.CartMainAdapter
import com.dust.exmall.animation.Animations
import com.google.android.material.tabs.TabLayout

class CartFragment : Fragment() {
    private lateinit var cartViewPager: ViewPager
    private lateinit var cartTabLayout:TabLayout

    private val animations = Animations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpMainViewPager()
        setUpTabLayoutAnimation()
    }

    private fun setUpTabLayoutAnimation() {
        for(i in 0 until cartTabLayout.tabCount){
            val tab = (cartTabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            tab.setOnTouchListener { v, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                    v.startAnimation(animations.getFadeInAnimation())
                    return@setOnTouchListener false
                }
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    v.startAnimation(animations.getFadeInAnimation())
                } else {
                    v.startAnimation(animations.getFadeOutAnimation())
                }
                false
            }
        }
    }

    private fun setUpMainViewPager() {
        cartViewPager.adapter = CartMainAdapter(childFragmentManager)
        cartTabLayout.setupWithViewPager(cartViewPager)
    }

    private fun setUpViews(view: View) {
        cartViewPager = view.findViewById(R.id.cartViewPager)
        cartTabLayout = view.findViewById(R.id.cartTabLayout)
    }
}