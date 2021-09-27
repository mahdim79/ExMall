package com.dust.exmall.fragments.others

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.FullReviewAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.FullReviewDataClass
import com.google.android.material.tabs.TabLayout
import kotlin.math.abs

class ProductReviewFragment(var fastReviewDescription: String, var productImage: String) :
    Fragment() {
    private lateinit var fastReviewLinear: LinearLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var fastReviewText: CTextView
    private lateinit var fullReviewRecyclerView: RecyclerView
    private lateinit var backImage: ImageView

    private val animations = Animations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpTabLayout()
        setUpFastReviewDescription()
        setUpFullReviewRecyclerView()
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setUpFullReviewRecyclerView() {
        fullReviewRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        fullReviewRecyclerView.adapter = FullReviewAdapter(generateFakeFullReviewData())
    }

    private fun setUpFastReviewDescription() {
        fastReviewText.text = fastReviewDescription
    }

    private fun setUpTabLayout() {
        val tabOne = tabLayout.newTab()
        val tabTwo = tabLayout.newTab()

        tabOne.text = "بررسی اجمالی محصول"
        tabTwo.text = "بررسی تخصصی محصول"

        tabLayout.addTab(tabOne)
        tabLayout.addTab(tabTwo)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    nestedScrollView.smoothScrollTo(0, 0 , 200)
                } else {
                    nestedScrollView.smoothScrollTo(0, fastReviewLinear.measuredHeight , 200)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    nestedScrollView.smoothScrollTo(0, 0 , 30)
                } else {
                    nestedScrollView.smoothScrollTo(0, fastReviewLinear.measuredHeight , 30)
                }
            }

        })

        // set margin on each tab
        for (i in 0 until tabLayout.childCount) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val params = tab.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(5, 0, 5, 0)
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

        val handler = Handler()
        var runnable:Runnable? = null


        nestedScrollView.setOnScrollChangeListener { v, _, scrollY, _, _ ->
            try {
                handler.removeCallbacks(runnable!!)
            }catch (e:Exception){}
            runnable = Runnable {
                    if (abs(scrollY) < fastReviewLinear.measuredHeight){
                        if (!tabOne.isSelected)
                            tabOne.select()
                    }else{
                        if (!tabTwo.isSelected)
                            tabTwo.select()
                    }
            }
            handler.postDelayed(runnable!!, 201)
        }
    }

    private fun setUpViews(view: View) {
        fastReviewLinear = view.findViewById(R.id.fastReviewLinear)
        tabLayout = view.findViewById(R.id.tabLayout)
        nestedScrollView = view.findViewById(R.id.nestedScrollView)
        fastReviewText = view.findViewById(R.id.fastReviewText)
        fullReviewRecyclerView = view.findViewById(R.id.fullReviewRecyclerView)
        backImage = view.findViewById(R.id.backImage)
    }

    private fun generateFakeFullReviewData(): List<FullReviewDataClass> {
        val list = arrayListOf<FullReviewDataClass>()
        for (i in 0..5) {
            list.add(
                FullReviewDataClass(
                    "باز هم کمبود باتری!",
                    fastReviewDescription,
                    productImage,
                    "",
                    "",
                    "",
                    "بقیه توضیحات"
                )
            )
        }
        return list
    }
}