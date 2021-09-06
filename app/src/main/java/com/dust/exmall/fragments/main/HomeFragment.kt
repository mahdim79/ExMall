package com.dust.exmall.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.*
import com.dust.exmall.adapters.viewpager.MainSliderAdapter
import com.dust.exmall.adapters.viewpager.ProductsSliderAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.apicore.ApiServiceManager
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.dataclasses.TopBrandDataClass
import com.dust.exmall.interfaces.maininterfaces.*
import com.squareup.picasso.Picasso
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import java.util.*

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
    private lateinit var recentlySeenProductsRecyclerView: RecyclerView
    private lateinit var topBrandRecyclerview: RecyclerView
    private lateinit var forSaleRecyclerView: RecyclerView
    private lateinit var ProductsSliderViewPager: ViewPager
    private lateinit var HighReviewedViewPager: ViewPager
    private lateinit var magicImageOne: ImageView
    private lateinit var magicImageTwo: ImageView
    private lateinit var magicImageThree: ImageView
    private lateinit var magicImageFour: ImageView
    private lateinit var magicImageFive: ImageView
    private lateinit var magicImageSix: ImageView
    private lateinit var magicImageSeven: ImageView
    private lateinit var magicImageEight: ImageView
    private lateinit var magicImageNine: ImageView

    private lateinit var popularProductsHeader:TextView
    private lateinit var popularProductsHeaderTwo:TextView
    private lateinit var popularProductsHeaderThree:TextView
    private lateinit var popularProductsHeaderFour:TextView

    private lateinit var popularLinearOne:LinearLayout
    private lateinit var popularLinearTwo:LinearLayout
    private lateinit var popularLinearThree:LinearLayout
    private lateinit var popularLinearFour:LinearLayout

    private var sliderCount = 0

    private var timer: Timer? = null

    private lateinit var apiServiceManager: ApiServiceManager

    private lateinit var addLocationContainer: LinearLayout

    private val animations = Animations()
    private lateinit var fadeInAnimation: AlphaAnimation
    private lateinit var fadeOutAnimation: AlphaAnimation

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
        setUpApiManager()
        setUpViewsAnimations()
        setUpSliderViewPager()
        setUpSuggestionRecyclerView()
        setUpAmazingOffersRecyclerView()
        setUpMagicCards()
        setUpAmazingSuperMarketRecyclerView()
        setUpPlusProductsRecyclerView()
        // popular Products
        setUpPopulars()
        setUpProductsSliderViewPager()
        setUpTopBrandRecyclerView()
        setUpRecentlySeenRecyclerView()
        setUpForSaleRecyclerView()
        setUpHighReviewedViewPager()
    }

    private fun setUpRecentlySeenRecyclerView() {
        recentlySeenProductsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        apiServiceManager.getRecentlySeenProducts(object :OnGetProducts{
            override fun onGetProducts(data: List<ProductsDataClass>) {
                recentlySeenProductsRecyclerView.adapter = RecentlyAdapter(data)
            }

            override fun onFailureGetProducts(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpPopulars() {
        apiServiceManager.getPopularCategories(object : OnGetCategories {
            override fun onGetCategories(categoryList: List<String>) {
                // set Category texts
                popularProductsHeader.text = categoryList[0]
                popularProductsHeaderTwo.text = categoryList[1]
                popularProductsHeaderThree.text = categoryList[2]
                popularProductsHeaderFour.text = categoryList[3]

                setUpPopularProductsRecyclerView(categoryList)
            }

            override fun onFailureGetCategories(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpApiManager() {
        apiServiceManager = ApiServiceManager()
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

    private fun setUpPopularProductsRecyclerView(categories:List<String>) {

        popularProductsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        popularProductsRecyclerViewTwo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        popularProductsRecyclerViewThree.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        popularProductsRecyclerViewFour.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        for (i in 0 until categories.size){
            apiServiceManager.getPopularProductsByCategory(object : OnGetPopularProducts {
                override fun onGetPopularProducts(data: List<ProductsDataClass>, tag: Int) {
                    val adapter = PopularProductsAdapter(data)
                    when(tag){
                        0 -> popularProductsRecyclerView.adapter = adapter
                        1 -> popularProductsRecyclerViewTwo.adapter = adapter
                        2 -> popularProductsRecyclerViewThree.adapter = adapter
                        else -> popularProductsRecyclerViewFour.adapter = adapter
                    }
                }

                override fun onFailureGetPopularProducts(message: String) {
                    Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT)
                        .show()
                }

            }, categories[i], i)
        }
    }

    private fun setUpHighReviewedViewPager() {
        apiServiceManager.getHighReviewedProducts(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                HighReviewedViewPager.adapter =
                    ProductsSliderAdapter(childFragmentManager, data, true)
                HighReviewedViewPager.setCurrentItem(4, false)
            }

            override fun onFailureGetProducts(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpForSaleRecyclerView() {
        forSaleRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        apiServiceManager.getForSaleProducts(object :OnGetProducts{
            override fun onGetProducts(data: List<ProductsDataClass>) {
                forSaleRecyclerView.adapter = ForSaleAdapter(data)
            }

            override fun onFailureGetProducts(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpTopBrandRecyclerView() {
        topBrandRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        apiServiceManager.getTopBrands(object : OnGetTopBrands {
            override fun onGetTopBrands(data: List<TopBrandDataClass>) {
                topBrandRecyclerview.adapter = TopBrandAdapter(data, animations)
            }

            override fun onFailureGetTopBrands(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpProductsSliderViewPager() {
        apiServiceManager.getBestSellersProducts(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                ProductsSliderViewPager.adapter = ProductsSliderAdapter(childFragmentManager, data)
                ProductsSliderViewPager.setCurrentItem(4, false)
            }

            override fun onFailureGetProducts(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpPlusProductsRecyclerView() {
        plusProductsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apiServiceManager.getPlusProducts(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                plusProductsRecyclerView.adapter = PlusProductsAdapter(data)
            }

            override fun onFailureGetProducts(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpAmazingOffersRecyclerView() {
        amazingOffersRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        apiServiceManager.getAmazingOffersProducts(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                amazingOffersRecyclerView.adapter =
                    AmazingAdapter(data, AmazingOffersType, requireContext())
            }

            override fun onFailureGetProducts(message: String) {
                Log.i("errorResponse", message)
                Toast.makeText(requireContext(), "some thing went wrong!", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    private fun setUpAmazingSuperMarketRecyclerView() {
        amazingSuperMarketRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apiServiceManager.getProductsByCategory(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                amazingSuperMarketRecyclerView.adapter =
                    AmazingAdapter(data, AmazingSuperMarketType, requireContext())
            }

            override fun onFailureGetProducts(message: String) {
                Toast.makeText(requireContext(), "some thing went wrong!", Toast.LENGTH_SHORT)
                    .show()
            }

        }, "jewelery")
    }

    private fun setUpMagicCards() {
        apiServiceManager.getMagicCartContents(object : OnGetMagicCartContent {
            override fun onGetMagicCartContents(list: List<ProductsDataClass>) {
                Picasso.get().load(list[0].image).into(magicImageOne)
                Picasso.get().load(list[1].image).into(magicImageTwo)
                Picasso.get().load(list[2].image).into(magicImageThree)
                Picasso.get().load(list[3].image).into(magicImageFour)
                Picasso.get().load(list[4].image).into(magicImageFive)
                Picasso.get().load(list[5].image).into(magicImageSix)
                Picasso.get().load(list[6].image).into(magicImageSeven)
                Picasso.get().load(list[7].image).into(magicImageEight)
                Picasso.get().load(list[8].image).into(magicImageNine)
            }

            override fun onFailureMagicCartContents(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setUpSuggestionRecyclerView() {
        suggestionRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        suggestionRecyclerView.adapter = SuggestionAdapter(animations)
    }

    private fun setUpSliderViewPager() {
        apiServiceManager.getSliderContent(object : OnGetSliderContent {
            override fun onGetSliderContent(list: List<Pair<String, String>>) {
                sliderViewPager.adapter = MainSliderAdapter(childFragmentManager, list)
                sliderDotsIndicator.setViewPager(sliderViewPager)
                sliderCount = list.size
                initSliderTimer()
            }

            override fun onGetFailure(message: String) {
                Toast.makeText(requireContext(), "something went wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initSliderTimer() {
        timer = Timer()
        timer!!.schedule(SliderTimerTask(), 5000, 5000)
    }

    private fun setUpViews(view: View) {
        popularLinearOne = view.findViewById(R.id.popularLinearOne)
        popularLinearTwo = view.findViewById(R.id.popularLinearTwo)
        popularLinearThree = view.findViewById(R.id.popularLinearThree)
        popularLinearFour = view.findViewById(R.id.popularLinearFour)

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
        popularProductsRecyclerView = popularLinearOne.findViewById(R.id.popularProductsRecyclerView)
        popularProductsRecyclerViewTwo = popularLinearTwo.findViewById(R.id.popularProductsRecyclerView)
        popularProductsRecyclerViewThree = popularLinearThree.findViewById(R.id.popularProductsRecyclerView)
        popularProductsRecyclerViewFour = popularLinearFour.findViewById(R.id.popularProductsRecyclerView)
        recentlySeenProductsRecyclerView = view.findViewById(R.id.recentlySeenProductsRecyclerView)
        addLocationContainer = view.findViewById(R.id.addLocationContainer)

        magicImageOne = view.findViewById(R.id.magicImageOne)
        magicImageTwo = view.findViewById(R.id.magicImageTwo)
        magicImageThree = view.findViewById(R.id.magicImageThree)
        magicImageFour = view.findViewById(R.id.magicImageFour)
        magicImageFive = view.findViewById(R.id. magicImageFive)
        magicImageSix = view.findViewById(R.id.  magicImageSix)
        magicImageSeven = view.findViewById(R.id.magicImageSeven)
        magicImageEight = view.findViewById(R.id.magicImageEight)
        magicImageNine = view.findViewById(R.id. magicImageNine)

        popularProductsHeader = popularLinearOne.findViewById(R.id.popularProductsHeader)
        popularProductsHeaderTwo = popularLinearTwo.findViewById(R.id.popularProductsHeader)
        popularProductsHeaderThree = popularLinearThree.findViewById(R.id.popularProductsHeader)
        popularProductsHeaderFour = popularLinearFour.findViewById(R.id.popularProductsHeader)

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

    override fun onStart() {
        if (timer != null) {
            timer!!.purge()
            timer!!.cancel()
            timer = Timer()
            timer!!.schedule(SliderTimerTask(), 5000, 5000)
        }
        super.onStart()
    }

    override fun onStop() {
        if (timer != null) {
            timer!!.purge()
            timer!!.cancel()
        }
        super.onStop()
    }

    inner class SliderTimerTask : TimerTask() {
        override fun run() {
            requireActivity().runOnUiThread {
                val index = if (sliderViewPager.currentItem == (sliderCount - 1))
                    0
                else
                    sliderViewPager.currentItem + 1

                sliderViewPager.setCurrentItem(index, true)
            }
        }

    }
}