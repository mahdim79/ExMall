package com.dust.exmall.fragments.others

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.OtherRelatedCategoriesAdapter
import com.dust.exmall.adapters.recyclerview.ProductsAdapterTwo
import com.dust.exmall.animation.Animations
import com.dust.exmall.apicore.ApiServiceManager
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.interfaces.maininterfaces.OnGetCategories
import com.dust.exmall.interfaces.maininterfaces.OnGetProducts
import com.google.android.material.appbar.AppBarLayout

class SellerProductsFragment() : Fragment(), View.OnClickListener{
    private lateinit var backImage:ImageView
    private lateinit var sellerInfoImage:ImageView

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var otherRelatedCategoriesRecyclerView: RecyclerView
    private lateinit var categoryName: CTextView
    private lateinit var productCountText: CTextView
    private lateinit var sortRelative: RelativeLayout
    private lateinit var filterLinear: LinearLayout
    private lateinit var sortLinear: LinearLayout
    private lateinit var mainProgressBar: ProgressBar
    private lateinit var retryText: TextView
    private lateinit var homeComponentContainer: CoordinatorLayout
    private lateinit var filterRelativeLayout: RelativeLayout
    private lateinit var footerRelative: RelativeLayout
    private lateinit var footerRelativeSort: RelativeLayout
    private lateinit var toolbar: AppBarLayout
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var closeFilterImage: ImageView
    private lateinit var clearSortImage: ImageView
    private lateinit var clearFilter: CTextView
    private lateinit var sortText: CTextView
    private lateinit var loadingContainer: LinearLayout

    private var animations = Animations()
    private lateinit var apiServiceManager: ApiServiceManager
    private var mainData = arrayListOf<ProductsDataClass>()

    private var LOADED_COMPONENT = 0
    private var FAILURE_LOADED_COMPONENT = 0
    private var TOTAL_COMPONENT_COUNT = 2
    private var TOTAL_FAILURE_COMPONENT_COUNT = 2

    private var SORT_TYPE = 1

    private val mostRelevantSortType = 0
    private val highReviewedSortType = 1
    private val newestSortType = 2
    private val highSellSortType = 3
    private val cheapestSortType = 4
    private val mostExpensiveSortType = 5
    private val fastDeliverSortType = 6
    private val buyersSuggestionSortType = 7

    private lateinit var mostRelevantRelative: RelativeLayout
    private lateinit var highReviewedRelative: RelativeLayout
    private lateinit var newestRelative: RelativeLayout
    private lateinit var highSellRelative: RelativeLayout
    private lateinit var cheapestRelative: RelativeLayout
    private lateinit var mostExpensiveRelative: RelativeLayout
    private lateinit var fastestDeliverRelative: RelativeLayout
    private lateinit var buyersSuggestionRelative: RelativeLayout
    private lateinit var mostRelevantTick:ImageView
    private lateinit var highReviewedTick:ImageView
    private lateinit var newestTick:ImageView
    private lateinit var highSellTick:ImageView
    private lateinit var cheapestTick:ImageView
    private lateinit var mostExpensiveTick:ImageView
    private lateinit var fastestDeliverTick:ImageView
    private lateinit var buyersSuggestionTick:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seller_products , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpSellerInfoImage()
        setUpViewsAnimations()
        setUpApiServiceManager()
        setUpOfflineViews()
        setUpFilterRelativeLayout()
        setUpSortRelativeLayout()
        setUpSortSystem()
        fetchLayoutData()
    }

    private fun setUpSellerInfoImage() {
        sellerInfoImage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , FullSellerInfoFragment().newInstance(requireArguments().getInt("SELLER_ID")))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("FullSellerInfoFragment")
                .commit()
        }
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack( "SellerProductsFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        sellerInfoImage = view.findViewById(R.id.sellerInfoImage)

        mainRecyclerView = view.findViewById(R.id.mainRecyclerView)
        otherRelatedCategoriesRecyclerView =
            view.findViewById(R.id.otherRelatedCategoriesRecyclerView)
        categoryName = view.findViewById(R.id.categoryName)
        productCountText = view.findViewById(R.id.productCountText)
        filterLinear = view.findViewById(R.id.filterLinear)
        sortLinear = view.findViewById(R.id.sortLinear)
        mainProgressBar = view.findViewById(R.id.mainProgressBar)
        retryText = view.findViewById(R.id.retryText)
        loadingContainer = view.findViewById(R.id.loadingContainer)
        filterRelativeLayout = view.findViewById(R.id.filterRelativeLayout)
        toolbar = view.findViewById(R.id.toolbar)
        footerRelative = filterRelativeLayout.findViewById(R.id.footerRelative)
        nestedScrollView = view.findViewById(R.id.nestedScrollView)
        closeFilterImage = view.findViewById(R.id.closeFilter)
        clearFilter = view.findViewById(R.id.clearFilter)
        sortRelative = view.findViewById(R.id.sortRelative)
        clearSortImage = view.findViewById(R.id.clearSortImage)
        sortText = view.findViewById(R.id.sortText)

        mostRelevantRelative = view.findViewById(R.id.mostRelevantRelative)
        highReviewedRelative = view.findViewById(R.id.highReviewedRelative)
        newestRelative = view.findViewById(R.id.newestRelative)
        highSellRelative = view.findViewById(R.id.highSellRelative)
        cheapestRelative = view.findViewById(R.id.cheapestRelative)
        mostExpensiveRelative = view.findViewById(R.id.mostExpensiveRelative)
        fastestDeliverRelative = view.findViewById(R.id.fastestDeliverRelative)
        buyersSuggestionRelative = view.findViewById(R.id.buyersSuggestionRelative)

        mostRelevantTick = view.findViewById(R.id.mostRelevantTick)
        highReviewedTick = view.findViewById(R.id.highReviewedTick)
        newestTick = view.findViewById(R.id.newestTick)
        highSellTick = view.findViewById(R.id.highSellTick)
        cheapestTick = view.findViewById(R.id.cheapestTick)
        mostExpensiveTick = view.findViewById(R.id.mostExpensiveTick)
        fastestDeliverTick = view.findViewById(R.id.fastestDeliverTick)
        buyersSuggestionTick = view.findViewById(R.id.buyersSuggestionTick)



        footerRelativeSort = sortRelative.findViewById(R.id.footerRelativeSort)
        homeComponentContainer = view.findViewById(R.id.homeComponentContainer)

        retryText.setOnClickListener {
            LOADED_COMPONENT = 0
            FAILURE_LOADED_COMPONENT = 0
            mainProgressBar.progress = 0
            retryText.visibility = View.GONE
            fetchLayoutData()
        }

        otherRelatedCategoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        mainRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
    }

    private fun setUpSortSystem() {
        mostRelevantRelative.setOnClickListener(this)
        highReviewedRelative.setOnClickListener(this)
        newestRelative.setOnClickListener(this)
        highSellRelative.setOnClickListener(this)
        cheapestRelative.setOnClickListener(this)
        mostExpensiveRelative.setOnClickListener(this)
        fastestDeliverRelative.setOnClickListener(this)
        buyersSuggestionRelative.setOnClickListener(this)
    }

    private fun setUpSortRelativeLayout() {
        sortLinear.setOnClickListener {
            openSortController()
        }

        // close filter controller
        footerRelativeSort.setOnClickListener {
            closeSortController()
        }

        clearSortImage.setOnClickListener {
            closeSortController()
        }
    }


    private fun setUpFilterRelativeLayout() {
        // open filter controller
        filterLinear.setOnClickListener {
            openFilterController()
        }

        // close filter controller
        footerRelative.setOnClickListener {
            closeFilterController()
        }

        // set up contents
        clearFilter.setOnClickListener {
            closeFilterController()
        }
    }
    private fun openSortController() {
        toolbar.startAnimation(animations.getUpTranslateAnimation())
        nestedScrollView.startAnimation(animations.getDownTranslateAnimation())
        Handler().postDelayed({
            toolbar.visibility = View.GONE
            nestedScrollView.visibility = View.GONE
            sortRelative.visibility = View.VISIBLE
            sortRelative.startAnimation(animations.getFadeInFullTransparentAlphaAnimation())
        }, 1000)
    }

    private fun openFilterController(){
        toolbar.startAnimation(animations.getUpTranslateAnimation())
        nestedScrollView.startAnimation(animations.getDownTranslateAnimation())
        Handler().postDelayed({
            toolbar.visibility = View.GONE
            nestedScrollView.visibility = View.GONE
            filterRelativeLayout.visibility = View.VISIBLE
            filterRelativeLayout.startAnimation(animations.getFadeInFullTransparentAlphaAnimation())
        }, 1000)
    }

    private fun closeSortController() {
        sortRelative.startAnimation(animations.getFadeOutFullTransparentAlphaAnimation())
        toolbar.visibility = View.VISIBLE
        nestedScrollView.visibility = View.VISIBLE
        toolbar.startAnimation(animations.getUpBackTranslateAnimation())
        nestedScrollView.startAnimation(animations.getDownBackTranslateAnimation())

        Handler().postDelayed({
            sortRelative.visibility = View.GONE
        }, 400)
    }

    private fun closeFilterController(){
        filterRelativeLayout.startAnimation(animations.getFadeOutFullTransparentAlphaAnimation())
        toolbar.visibility = View.VISIBLE
        nestedScrollView.visibility = View.VISIBLE
        toolbar.startAnimation(animations.getUpBackTranslateAnimation())
        nestedScrollView.startAnimation(animations.getDownBackTranslateAnimation())

        Handler().postDelayed({
            filterRelativeLayout.visibility = View.GONE
        }, 400)
    }

    private fun setUpOfflineViews() {
        categoryName.text = requireArguments().getString("NAME")
    }

    private fun updateLoadingState(){
        LOADED_COMPONENT++
        mainProgressBar.progress = LOADED_COMPONENT
        if (LOADED_COMPONENT == TOTAL_COMPONENT_COUNT){
            homeComponentContainer.visibility = View.VISIBLE
            loadingContainer.visibility = View.GONE
            homeComponentContainer.startAnimation(animations.getScaleAnimation())
            LOADED_COMPONENT = 0
            mainProgressBar.progress = 0
        }
    }

    private fun updateFailureLoad(){
        FAILURE_LOADED_COMPONENT++
        if (FAILURE_LOADED_COMPONENT == TOTAL_FAILURE_COMPONENT_COUNT){
            retryText.visibility = View.VISIBLE
            FAILURE_LOADED_COMPONENT = 0
        }
    }

    private fun fetchLayoutData() {
        //get main data
        apiServiceManager.getSellerProducts(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                mainData.addAll(data)
                setUpMainRecyclerView()
                productCountText.text = "${data.size} کالا"
                updateLoadingState()
            }

            override fun onFailureGetProducts(message: String) {
                updateFailureLoad()
            }

        } , requireArguments().getInt("SELLER_ID"))
    }

    private fun setUpMainRecyclerView() {
        mainRecyclerView.adapter = ProductsAdapterTwo(mainData , requireActivity().supportFragmentManager)
    }

    private fun setUpApiServiceManager() {
        apiServiceManager = ApiServiceManager()
    }

    fun newInstance(sellerId: Int): SellerProductsFragment {
        val args = Bundle()
        args.putInt("SELLER_ID" , sellerId)
        val fragment = SellerProductsFragment()
        fragment.arguments = args
        return fragment
    }

    private fun startDataSort(dataSortType:Int){
        SORT_TYPE = dataSortType
        // restart fragment
        Handler().postDelayed({
            homeComponentContainer.visibility = View.GONE
            loadingContainer.visibility = View.VISIBLE
            fetchLayoutData()
        } , 1000)
    }

    private fun clearTicks(){
        mostRelevantTick.visibility = View.GONE
        highReviewedTick.visibility = View.GONE
        newestTick.visibility = View.GONE
        highSellTick.visibility = View.GONE
        cheapestTick.visibility = View.GONE
        mostExpensiveTick.visibility = View.GONE
        fastestDeliverTick.visibility = View.GONE
        buyersSuggestionTick.visibility = View.GONE
    }

    private fun setUpViewsAnimations() {

        backImage.setOnTouchListener { v, motionEvent ->
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
        sortLinear.setOnTouchListener { v, motionEvent ->
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
        filterLinear.setOnTouchListener { v, motionEvent ->
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

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.mostRelevantRelative -> {
                startDataSort(mostRelevantSortType)
                clearTicks()
                mostRelevantTick.visibility = View.VISIBLE
                sortText.text = "مرتبط ترین ها"
                closeSortController()
            }
            R.id.highReviewedRelative -> {
                startDataSort(highReviewedSortType)
                clearTicks()
                highReviewedTick.visibility = View.VISIBLE
                sortText.text = "پربازدیدترین ها"
                closeSortController()
            }
            R.id.newestRelative -> {
                startDataSort(newestSortType)
                clearTicks()
                newestTick.visibility = View.VISIBLE
                sortText.text = "جدیدترین ها"
                closeSortController()
            }
            R.id.highSellRelative -> {
                startDataSort(highSellSortType)
                clearTicks()
                highSellTick.visibility = View.VISIBLE
                sortText.text = "پرفروش ترین ها"
                closeSortController()
            }
            R.id.cheapestRelative -> {
                startDataSort(cheapestSortType)
                clearTicks()
                cheapestTick.visibility = View.VISIBLE
                sortText.text = "ارزانترین ها"
                closeSortController()
            }
            R.id.mostExpensiveRelative -> {
                startDataSort(mostExpensiveSortType)
                clearTicks()
                mostExpensiveTick.visibility = View.VISIBLE
                sortText.text = "گرانترین ها"
                closeSortController()
            }
            R.id.fastestDeliverRelative -> {
                startDataSort(fastDeliverSortType)
                clearTicks()
                fastestDeliverTick.visibility = View.VISIBLE
                sortText.text = "سریع ترین ارسال"
                closeSortController()
            }
            R.id.buyersSuggestionRelative -> {
                startDataSort(buyersSuggestionSortType)
                clearTicks()
                buyersSuggestionTick.visibility = View.VISIBLE
                sortText.text = "پیشنهادات خریداران"
                closeSortController()
            }
        }
    }
}