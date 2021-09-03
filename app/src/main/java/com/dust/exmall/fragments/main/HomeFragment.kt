package com.dust.exmall.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.*
import com.dust.exmall.adapters.viewpager.MainSliderAdapter
import com.dust.exmall.adapters.viewpager.ProductsSliderAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.AmazingDataClass
import com.dust.exmall.dataclasses.TopBrandDataClass
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var search_text: CTextView
    private lateinit var exMallText: TextView
    private lateinit var search_image: ImageView
    private lateinit var sliderViewPager: ViewPager
    private lateinit var sliderDotsIndicator: DotsIndicator
    private lateinit var suggestionRecyclerView: RecyclerView
    private lateinit var amazingOffersRecyclerView: RecyclerView
    private lateinit var amazingSuperMarketRecyclerView: RecyclerView
    private lateinit var plusProductsRecyclerView: RecyclerView
    private lateinit var popularProductsRecyclerView: RecyclerView
    private lateinit var popularProductsRecyclerViewTwo: RecyclerView
    private lateinit var popularProductsRecyclerViewThree: RecyclerView
    private lateinit var popularProductsRecyclerViewFour: RecyclerView
    private lateinit var topBrandRecyclerview: RecyclerView
    private lateinit var forSaleRecyclerView: RecyclerView
    private lateinit var ProductsSliderViewPager: ViewPager
    private lateinit var HighReviewedViewPager: ViewPager
    private lateinit var magicImageOne: ImageView
    private lateinit var magicImageTwo: ImageView
    private lateinit var magicImageThree: ImageView
    private lateinit var magicImageFour: ImageView

    private lateinit var addLocationContainer:LinearLayout

    private val animations = Animations()
    private lateinit var fadeInAnimation:AlphaAnimation
    private lateinit var fadeOutAnimation:AlphaAnimation

    private var AmazingOffersType: Int = 0
    private var AmazingSuperMarketType: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpViewsAnimations()
        setUpSliderViewPager()
        setUpSuggestionRecyclerView()
        setUpAmazingOffersRecyclerView()
        setUpMagicCards()
        setUpAmazingSuperMarketRecyclerView()
        setUpPlusProductsRecyclerView()
        // popular Products
        setUpPopularProductsRecyclerView()
        setUpProductsSliderViewPager()
        setUpTopBrandRecyclerView()
        setUpForSaleRecyclerView()
        setUpHighReviewedViewPager()
    }

    private fun setUpViewsAnimations() {
        fadeInAnimation = animations.getFadeInAnimation()
        fadeOutAnimation = animations.getFadeOutAnimation()

        addLocationContainer.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL){
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
            if (motionEvent.action == MotionEvent.ACTION_CANCEL){
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP){
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
            }else{
                exMallText.startAnimation(fadeOutAnimation)
                search_text.startAnimation(fadeOutAnimation)
            }
            true
        }

        search_text.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL){
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP){
                exMallText.startAnimation(fadeInAnimation)
                search_text.startAnimation(fadeInAnimation)
            }else{
                exMallText.startAnimation(fadeOutAnimation)
                search_text.startAnimation(fadeOutAnimation)
            }
            true
        }

        search_image.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL){
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

    private fun setUpPopularProductsRecyclerView() {
        // one
        popularProductsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        popularProductsRecyclerView.adapter = PopularProductsAdapter(generateFakeData())

        // two
        popularProductsRecyclerViewTwo.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        popularProductsRecyclerViewTwo.adapter = PopularProductsAdapter(generateFakeData())

        //three
        popularProductsRecyclerViewThree.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        popularProductsRecyclerViewThree.adapter = PopularProductsAdapter(generateFakeData())

        //four
        popularProductsRecyclerViewFour.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        popularProductsRecyclerViewFour.adapter = PopularProductsAdapter(generateFakeData())
    }

    private fun setUpHighReviewedViewPager() {
        HighReviewedViewPager.adapter = ProductsSliderAdapter(childFragmentManager , generateFakeData())
        HighReviewedViewPager.setCurrentItem(4 , false)
    }

    private fun setUpForSaleRecyclerView() {
        forSaleRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        forSaleRecyclerView.adapter = ForSaleAdapter(generateFakeData())

    }

    private fun setUpTopBrandRecyclerView() {
        topBrandRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topBrandRecyclerview.adapter = TopBrandAdapter(generateFakeTopData() , animations)
    }

    private fun setUpProductsSliderViewPager() {
        ProductsSliderViewPager.adapter = ProductsSliderAdapter(childFragmentManager , generateFakeData())
        ProductsSliderViewPager.setCurrentItem(4 , false)
    }

    private fun setUpPlusProductsRecyclerView(){
        plusProductsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        plusProductsRecyclerView.adapter = PlusProductsAdapter(generateFakeData())
    }

    private fun setUpAmazingOffersRecyclerView() {
        amazingOffersRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        amazingOffersRecyclerView.adapter =
            AmazingAdapter(generateFakeData(), AmazingOffersType, requireContext())
    }

    private fun setUpAmazingSuperMarketRecyclerView() {
        amazingSuperMarketRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        amazingSuperMarketRecyclerView.adapter =
            AmazingAdapter(generateFakeData(), AmazingSuperMarketType, requireContext())
    }

    private fun setUpMagicCards() {

    }

    private fun setUpSuggestionRecyclerView() {
        suggestionRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        suggestionRecyclerView.adapter = SuggestionAdapter(animations)
    }

    private fun setUpSliderViewPager() {
        sliderViewPager.adapter = MainSliderAdapter(childFragmentManager)
        sliderDotsIndicator.setViewPager(sliderViewPager)
    }

    private fun setUpViews(view: View) {
        search_text = view.findViewById(R.id.search_text)
        search_image = view.findViewById(R.id.search_image)
        exMallText = view.findViewById(R.id.exMallText)
        sliderViewPager = view.findViewById(R.id.sliderViewPager)
        sliderDotsIndicator = view.findViewById(R.id.sliderDotsIndicator)
        suggestionRecyclerView = view.findViewById(R.id.suggestionRecyclerView)
        amazingOffersRecyclerView = view.findViewById(R.id.amazingOffersRecyclerView)
        amazingSuperMarketRecyclerView = view.findViewById(R.id.amazingSuperMarketRecyclerView)
        plusProductsRecyclerView = view.findViewById(R.id.plusProductsRecyclerView)
        ProductsSliderViewPager = view.findViewById(R.id.ProductsSliderViewPager)
        topBrandRecyclerview = view.findViewById(R.id.topBrandRecyclerview)
        forSaleRecyclerView = view.findViewById(R.id.forSaleRecyclerView)
        HighReviewedViewPager = view.findViewById(R.id.HighReviewedViewPager)
        popularProductsRecyclerView = view.findViewById(R.id.popularProductsRecyclerView)
        popularProductsRecyclerViewTwo = view.findViewById(R.id.popularProductsRecyclerViewTwo)
        popularProductsRecyclerViewThree = view.findViewById(R.id.popularProductsRecyclerViewThree)
        popularProductsRecyclerViewFour = view.findViewById(R.id.popularProductsRecyclerViewFour)
        addLocationContainer = view.findViewById(R.id.addLocationContainer)

        search_text.setOnClickListener(this)
        search_image.setOnClickListener(this)
        exMallText.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.search_text -> {
            }
            R.id.search_image -> {
            }
            R.id.exMallText -> {
            }
        }
    }

    private fun generateFakeData(): List<AmazingDataClass> {
        val list = arrayListOf<AmazingDataClass>()
        for (i in 0..14) {
            list.add(AmazingDataClass("hello"))
        }
        return list
    }

    private fun generateFakeTopData(): List<TopBrandDataClass> {
        val list = arrayListOf<TopBrandDataClass>()
        for (i in 0..14) {
            list.add(TopBrandDataClass("hello"))
        }
        return list
    }
}