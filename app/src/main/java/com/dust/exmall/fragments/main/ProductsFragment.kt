package com.dust.exmall.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.MyApplication
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.ProductsAdapter
import com.dust.exmall.adapters.viewpager.PopularBrandsViewPager
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.customviews.CViewPager
import com.dust.exmall.dataclasses.AmazingDataClass
import com.google.android.material.tabs.TabLayout

class ProductsFragment : Fragment() {
    private val animations = Animations()
    private lateinit var fadeInAnimation: AlphaAnimation
    private lateinit var fadeOutAnimation: AlphaAnimation

    private lateinit var addLocationContainer: LinearLayout
    private lateinit var digitalProductsRecyclerView: RecyclerView
    private lateinit var giftCartRecyclerView: RecyclerView
    private lateinit var localProductsRecyclerView: RecyclerView
    private lateinit var athleteRecyclerView: RecyclerView
    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var homeAndKitchenRecyclerView: RecyclerView
    private lateinit var beautyRecyclerView: RecyclerView
    private lateinit var superMarketRecyclerView: RecyclerView
    private lateinit var toysRecyclerView: RecyclerView
    private lateinit var dressRecyclerView: RecyclerView
    private lateinit var carsRecyclerView: RecyclerView
    private lateinit var popularBrandsViewPager: CViewPager
    private lateinit var popularBrandsTab: TabLayout

    private lateinit var search_text: CTextView
    private lateinit var exMallText: TextView
    private lateinit var search_image: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpViewsAnimations()
        setUpRecyclerViews()
        setUpPopularBrandsViewPager()
    }

    private fun setUpPopularBrandsViewPager() {
        popularBrandsViewPager.adapter = PopularBrandsViewPager(childFragmentManager)
        popularBrandsTab.setupWithViewPager(popularBrandsViewPager)

        val typeFace = requireActivity().applicationContext as MyApplication
        // apply font on tabLayout
        for (i in 0 until popularBrandsTab.childCount) {
            val viewGroup = popularBrandsTab.getChildAt(i) as ViewGroup
            for (j in 0 until viewGroup.childCount) {
                val viewGroup2 = viewGroup.getChildAt(j) as ViewGroup
                for (k in 0 until viewGroup2.childCount) {
                    if (viewGroup2.getChildAt(k) is TextView) {
                        (viewGroup2.getChildAt(k) as TextView).typeface = typeFace.getTypeFace()
                        (viewGroup2.getChildAt(k) as TextView).textSize = 5f
                    }
                }
            }
        }

        // apply margins on each tab
        for (i in 0 until popularBrandsTab.tabCount) {
            val tab = (popularBrandsTab.getChildAt(0) as ViewGroup).getChildAt(i)
            val params = tab.layoutParams as ViewGroup.MarginLayoutParams
            when (i) {
                0 -> params.setMargins(10, 0, 25, 0)
                (popularBrandsTab.tabCount - 1) -> params.setMargins(25, 0, 10, 0)
                else -> params.setMargins(10, 0, 10, 0)
            }
            tab.setOnTouchListener { v, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                    v.startAnimation(animations.getFadeInAnimation())
                    return@setOnTouchListener false
                }
                if (motionEvent.action == MotionEvent.ACTION_UP)
                    v.startAnimation(animations.getFadeInAnimation())
                else
                    v.startAnimation(animations.getFadeOutAnimation())
                false
            }
            tab.requestLayout()
        }

    }

    private fun setUpViewsAnimations() {
        fadeInAnimation = animations.getFadeInAnimation()
        fadeOutAnimation = animations.getFadeOutAnimation()

        addLocationContainer.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(fadeInAnimation)
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP)
                v.startAnimation(fadeInAnimation)
            else
                v.startAnimation(fadeOutAnimation)
            true
        }

        exMallText.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
            } else {
                exMallText.startAnimation(fadeOutAnimation)
                search_text.startAnimation(fadeOutAnimation)
            }
            true
        }

        search_text.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
            } else {
                exMallText.startAnimation(fadeOutAnimation)
                search_text.startAnimation(fadeOutAnimation)
            }
            true
        }

        search_image.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(fadeInAnimation)
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP)
                v.startAnimation(fadeInAnimation)
            else
                v.startAnimation(fadeOutAnimation)
            true
        }
    }

    private fun setUpRecyclerViews() {
        digitalProductsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        giftCartRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        localProductsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        athleteRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bookRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeAndKitchenRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        beautyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        superMarketRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        toysRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        dressRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        carsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        digitalProductsRecyclerView.adapter = ProductsAdapter(generateFakeData())
        giftCartRecyclerView.adapter = ProductsAdapter(generateFakeData())
        localProductsRecyclerView.adapter = ProductsAdapter(generateFakeData())
        athleteRecyclerView.adapter = ProductsAdapter(generateFakeData())
        bookRecyclerView.adapter = ProductsAdapter(generateFakeData())
        homeAndKitchenRecyclerView.adapter = ProductsAdapter(generateFakeData())
        beautyRecyclerView.adapter = ProductsAdapter(generateFakeData())
        superMarketRecyclerView.adapter = ProductsAdapter(generateFakeData())
        toysRecyclerView.adapter = ProductsAdapter(generateFakeData())
        dressRecyclerView.adapter = ProductsAdapter(generateFakeData())
        carsRecyclerView.adapter = ProductsAdapter(generateFakeData())
    }

    private fun setUpViews(view: View) {
        digitalProductsRecyclerView = view.findViewById(R.id.digitalProductsRecyclerView)
        giftCartRecyclerView = view.findViewById(R.id.giftCartRecyclerView)
        localProductsRecyclerView = view.findViewById(R.id.localProductsRecyclerView)
        athleteRecyclerView = view.findViewById(R.id.athleteRecyclerView)
        bookRecyclerView = view.findViewById(R.id.bookRecyclerView)
        homeAndKitchenRecyclerView = view.findViewById(R.id.homeAndKitchenRecyclerView)
        beautyRecyclerView = view.findViewById(R.id.beautyRecyclerView)
        superMarketRecyclerView = view.findViewById(R.id.superMarketRecyclerView)
        toysRecyclerView = view.findViewById(R.id.toysRecyclerView)
        dressRecyclerView = view.findViewById(R.id.dressRecyclerView)
        carsRecyclerView = view.findViewById(R.id.carsRecyclerView)
        addLocationContainer = view.findViewById(R.id.addLocationContainer)
        search_text = view.findViewById(R.id.search_text)
        search_image = view.findViewById(R.id.search_image)
        exMallText = view.findViewById(R.id.exMallText)
        popularBrandsViewPager = view.findViewById(R.id.popularBrandsViewPager)
        popularBrandsTab = view.findViewById(R.id.popularBrandsTab)
    }

    private fun generateFakeData(): List<AmazingDataClass> {
        val list = arrayListOf<AmazingDataClass>()
        for (i in 0..14) {
            list.add(AmazingDataClass("hello"))
        }
        return list
    }
}