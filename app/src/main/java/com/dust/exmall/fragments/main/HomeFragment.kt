package com.dust.exmall.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.AmazingAdapter
import com.dust.exmall.adapters.recyclerview.SuggestionAdapter
import com.dust.exmall.adapters.viewpager.MainSliderAdapter
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.AmazingDataClass
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
    private lateinit var magicImageOne: ImageView
    private lateinit var magicImageTwo: ImageView
    private lateinit var magicImageThree: ImageView
    private lateinit var magicImageFour: ImageView

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
        setUpSliderViewPager()
        setUpSuggestionRecyclerView()
        setUpAmazingOffersRecyclerView()
        setUpMagicCards()
        setUpAmazingSuperMarketRecyclerView()
    }

    private fun setUpAmazingOffersRecyclerView() {
        amazingOffersRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        amazingOffersRecyclerView.adapter = AmazingAdapter(generateFakeData(), AmazingOffersType , requireContext())
    }

    private fun setUpAmazingSuperMarketRecyclerView() {
        amazingSuperMarketRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        amazingSuperMarketRecyclerView.adapter = AmazingAdapter(generateFakeData(), AmazingSuperMarketType , requireContext())
    }

    private fun setUpMagicCards() {

    }

    private fun setUpSuggestionRecyclerView() {
        suggestionRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        suggestionRecyclerView.adapter = SuggestionAdapter()
    }

    private fun setUpSliderViewPager() {
        sliderViewPager.adapter = MainSliderAdapter(requireActivity().supportFragmentManager)
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

    private fun generateFakeData():List<AmazingDataClass> {
        val list = arrayListOf<AmazingDataClass>()
        list.add(AmazingDataClass("hello"))
        list.add(AmazingDataClass("hello"))
        list.add(AmazingDataClass("hello"))
        list.add(AmazingDataClass("hello"))
        return list
    }
}