package com.dust.exmall.fragments.others.specialfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.LocalProductsCategoryAdapter
import com.dust.exmall.adapters.recyclerview.PlusProductsAdapter
import com.dust.exmall.adapters.recyclerview.RecentlyAdapter
import com.dust.exmall.adapters.recyclerview.TopBrandAdapter
import com.dust.exmall.adapters.viewpager.SpecialHeaderAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.apicore.ApiServiceManager
import com.dust.exmall.dataclasses.*
import com.dust.exmall.fragments.others.CategoryProductsFragment
import com.dust.exmall.fragments.others.SearchFragment
import com.dust.exmall.fragments.others.TagProductsFragment
import com.dust.exmall.fragments.others.WebPageFragment
import com.dust.exmall.interfaces.maininterfaces.OnGetLocalData
import com.squareup.picasso.Picasso
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class LocalProductsFragment() : Fragment() {

    private lateinit var headerViewPager: ViewPager
    private lateinit var sliderDotsIndicator: DotsIndicator
    private lateinit var magicImageOne: ImageView
    private lateinit var magicImageTwo: ImageView
    private lateinit var magicImageThree: ImageView
    private lateinit var magicImageFour: ImageView
    private lateinit var magicImageFive: ImageView
    private lateinit var magicImageSix: ImageView
    private lateinit var magicImageSeven: ImageView
    private lateinit var magicImageEight: ImageView

    private lateinit var backImage: ImageView

    private lateinit var apiServiceManager: ApiServiceManager

    private lateinit var favoritesLayout: LinearLayout
    private lateinit var newestLayout: LinearLayout
    private lateinit var cheapestLayout: LinearLayout
    private lateinit var mainLocalProductsLayout: LinearLayout
    private lateinit var wearingLocalProductsLayout: LinearLayout
    private lateinit var playingLocalProductsLayout: LinearLayout
    private lateinit var eatingLocalProductsLayout: LinearLayout
    private lateinit var searchBox: LinearLayout
    private lateinit var search_text_linear: LinearLayout

    private lateinit var favoritesText: TextView
    private lateinit var newestText: TextView
    private lateinit var cheapestText: TextView
    private lateinit var mainLocalProductsText: TextView
    private lateinit var wearingLocalProductsText: TextView
    private lateinit var playingLocalProductsText: TextView
    private lateinit var eatingLocalProductsText: TextView

    private lateinit var favoritesRecyclerView:RecyclerView
    private lateinit var newestRecyclerView:RecyclerView
    private lateinit var cheapestRecyclerView:RecyclerView
    private lateinit var mainLocalProductsRecyclerView:RecyclerView
    private lateinit var wearingLocalProductsRecyclerView:RecyclerView
    private lateinit var playingLocalProductsRecyclerView:RecyclerView
    private lateinit var eatingLocalProductsRecyclerView:RecyclerView
    private lateinit var popularBrandsRecyclerView:RecyclerView

    private lateinit var coordinatorContainer:CoordinatorLayout
    private lateinit var loadingContainer:LinearLayout
    private lateinit var retryText:TextView
    private lateinit var mainProgressBar:ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpFlag()
        setSearchBoxTransparency()
        setUpSearchBox()
        setUpTitles()
        setUpApiCore()
        // get data
        getMainData()

    }

    private fun setUpSearchBox() {
        search_text_linear.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , SearchFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("SearchFragment")
                .commit()
        }
    }

    private fun setSearchBoxTransparency() {
        searchBox.alpha = 0.8f
    }

    private fun setUpTitles() {
        favoritesText.text = "محبوب ترین ها"
        newestText.text = "جدیدترین ها"
        cheapestText.text = "ارزان ترین ها"
        mainLocalProductsText.text = "خانه و کاشانه بومی محلی"
        wearingLocalProductsText.text = "پوشیدنی های بومی محلی"
        playingLocalProductsText.text = "بازی و سرگرمی های بومی محلی"
        eatingLocalProductsText.text = "خوراکی های بومی محلی"
    }

    private fun setUpApiCore() {
        apiServiceManager = ApiServiceManager()
    }

    private fun getMainData() {
        apiServiceManager.getLocalData(object : OnGetLocalData {
            override fun onGetData(data: LocalProductsDataClass) {
                setUpLayout(data)
            }

            override fun onFailureGetData(message: String) {
                retryText.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpLayout(data: LocalProductsDataClass) {
        setUpHeaderViewPager(data.headerList)
        setUpTopCards(data.topList)
        setUpProductsRecyclerViews(
            data.favoritesProductsList,
            data.newestProductsList,
            data.cheapestProductsList
        )
        setUpCategoriesRecyclerViews(
            data.mainCategories,
            data.wearingCategories,
            data.playingCategories,
            data.eatingCategories
        )
        setUpFourCards(data.fourCards)
        setUpTopBrands(data.topBrands)

        showView()
    }

    private fun showView() {
        mainProgressBar.progress = 16
        loadingContainer.visibility = View.GONE
        coordinatorContainer.visibility = View.VISIBLE
    }

    private fun setUpTopBrands(topBrands: List<TopBrandDataClass>) {
        popularBrandsRecyclerView.adapter = TopBrandAdapter( topBrands , Animations())
    }

    private fun setUpFourCards(fourCards: List<CardsDataClass>) {
        Picasso.get().load(fourCards[0].image).into(magicImageFive)
        Picasso.get().load(fourCards[1].image).into(magicImageSix)
        Picasso.get().load(fourCards[2].image).into(magicImageSeven)
        Picasso.get().load(fourCards[3].image).into(magicImageEight)

        magicImageFive.setOnClickListener {
            evaluateCardData(fourCards[0])
        }
        magicImageSix.setOnClickListener {
            evaluateCardData(fourCards[1])
        }
        magicImageSeven.setOnClickListener {
            evaluateCardData(fourCards[2])
        }
        magicImageEight.setOnClickListener {
            evaluateCardData(fourCards[3])
        }
    }

    private fun setUpCategoriesRecyclerViews(
        mainCategories: List<CategoryDataClass>,
        wearingCategories: List<CategoryDataClass>,
        playingCategories: List<CategoryDataClass>,
        eatingCategories: List<CategoryDataClass>
    ) {
        mainLocalProductsRecyclerView.adapter = LocalProductsCategoryAdapter(mainCategories , requireActivity().supportFragmentManager)
        wearingLocalProductsRecyclerView.adapter = LocalProductsCategoryAdapter(wearingCategories, requireActivity().supportFragmentManager)
        playingLocalProductsRecyclerView.adapter = LocalProductsCategoryAdapter(playingCategories, requireActivity().supportFragmentManager)
        eatingLocalProductsRecyclerView.adapter = LocalProductsCategoryAdapter(eatingCategories, requireActivity().supportFragmentManager)
    }

    private fun setUpProductsRecyclerViews(
        favoritesProductsList: List<ProductsDataClass>,
        newestProductsList: List<ProductsDataClass>,
        cheapestProductsList: List<ProductsDataClass>
    ) {
        favoritesRecyclerView.adapter = RecentlyAdapter(favoritesProductsList , requireActivity().supportFragmentManager)
        newestRecyclerView.adapter = RecentlyAdapter(newestProductsList , requireActivity().supportFragmentManager)
        cheapestRecyclerView.adapter = RecentlyAdapter(cheapestProductsList , requireActivity().supportFragmentManager)
    }

    private fun setUpTopCards(data: List<CardsDataClass>) {
        Picasso.get().load(data[0].image).into(magicImageOne)
        Picasso.get().load(data[1].image).into(magicImageTwo)
        Picasso.get().load(data[2].image).into(magicImageThree)
        Picasso.get().load(data[3].image).into(magicImageFour)

        magicImageOne.setOnClickListener {
            evaluateCardData(data[0])
        }
        magicImageTwo.setOnClickListener {
            evaluateCardData(data[1])
        }
        magicImageThree.setOnClickListener {
            evaluateCardData(data[2])
        }
        magicImageFour.setOnClickListener {
            evaluateCardData(data[3])
        }
    }

    private fun setUpBackImage() {
        backImage.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "LocalProductsFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpFlag() {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setUpHeaderViewPager(data: List<CardsDataClass>) {
        headerViewPager.offscreenPageLimit = 3
        headerViewPager.adapter = SpecialHeaderAdapter(
            requireActivity().supportFragmentManager,
            data
        )
        sliderDotsIndicator.setViewPager(headerViewPager)
    }

    private fun setUpViews(view: View) {
        headerViewPager = view.findViewById(R.id.headerViewPager)
        sliderDotsIndicator = view.findViewById(R.id.sliderDotsIndicator)
        magicImageOne = view.findViewById(R.id.magicImageOne)
        magicImageTwo = view.findViewById(R.id.magicImageTwo)
        magicImageThree = view.findViewById(R.id.magicImageThree)
        magicImageFour = view.findViewById(R.id.magicImageFour)
        magicImageFive = view.findViewById(R.id.magicImageFive)
        magicImageSix = view.findViewById(R.id.magicImageSix)
        magicImageSeven = view.findViewById(R.id.magicImageSeven)
        magicImageEight = view.findViewById(R.id.magicImageEight)

        backImage = view.findViewById(R.id.search_image)
        searchBox = view.findViewById(R.id.searchBox)

        search_text_linear = searchBox.findViewById(R.id.search_text_linear)

        favoritesLayout = view.findViewById(R.id.favoritesLayout)
        newestLayout = view.findViewById(R.id.newestLayout)
        cheapestLayout = view.findViewById(R.id.cheapestLayout)
        mainLocalProductsLayout = view.findViewById(R.id.mainLocalProductsLayout)
        wearingLocalProductsLayout = view.findViewById(R.id.wearingLocalProductsLayout)
        playingLocalProductsLayout = view.findViewById(R.id.playingLocalProductsLayout)
        eatingLocalProductsLayout = view.findViewById(R.id.eatingLocalProductsLayout)

        popularBrandsRecyclerView = view.findViewById(R.id.popularBrandsRecyclerView)

        coordinatorContainer = view.findViewById(R.id.coordinatorContainer)
        loadingContainer = view.findViewById(R.id.loadingContainer)
        retryText = view.findViewById(R.id.retryText)
        mainProgressBar = view.findViewById(R.id.mainProgressBar)

        favoritesRecyclerView = favoritesLayout.findViewById(R.id.localProductsRecyclerView)
        newestRecyclerView = newestLayout.findViewById(R.id.localProductsRecyclerView)
        cheapestRecyclerView = cheapestLayout.findViewById(R.id.localProductsRecyclerView)
        mainLocalProductsRecyclerView = mainLocalProductsLayout.findViewById(R.id.localProductsRecyclerView)
        wearingLocalProductsRecyclerView = wearingLocalProductsLayout.findViewById(R.id.localProductsRecyclerView)
        playingLocalProductsRecyclerView = playingLocalProductsLayout.findViewById(R.id.localProductsRecyclerView)
        eatingLocalProductsRecyclerView = eatingLocalProductsLayout.findViewById(R.id.localProductsRecyclerView)

        favoritesText = favoritesLayout.findViewById(R.id.title)
        newestText = newestLayout.findViewById(R.id.title)
        cheapestText = cheapestLayout.findViewById(R.id.title)
        mainLocalProductsText = mainLocalProductsLayout.findViewById(R.id.title)
        wearingLocalProductsText = wearingLocalProductsLayout.findViewById(R.id.title)
        playingLocalProductsText = playingLocalProductsLayout.findViewById(R.id.title)
        eatingLocalProductsText = eatingLocalProductsLayout.findViewById(R.id.title)

        favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        newestRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        cheapestRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        mainLocalProductsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        wearingLocalProductsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        playingLocalProductsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        eatingLocalProductsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        popularBrandsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)

        retryText.setOnClickListener {
            retryText.visibility = View.GONE
            getMainData()
        }

    }

    override fun onDestroy() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDestroy()
    }

    private fun evaluateCardData(data: CardsDataClass) {
        when (data.type) {
            "CATEGORY" -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, CategoryProductsFragment().newInstance(data.name))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("CategoryProductsFragment")
                    .commit()
            }
            "TAG" -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, TagProductsFragment().newInstance(data.tag))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("TagProductsFragment")
                    .commit()
            }
            "LINK" -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, WebPageFragment().newInstance(data.name, data.link))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("WebPageFragment")
                    .commit()
            }
        }
    }

    override fun onStart() {
        Log.i("flagTag" , "onstart")
        super.onStart()
    }

    override fun onStop() {
        Log.i("flagTag" , "onstop")
        super.onStop()
    }

    override fun onPause() {
        Log.i("flagTag" , "onpause")
        super.onPause()
    }

}