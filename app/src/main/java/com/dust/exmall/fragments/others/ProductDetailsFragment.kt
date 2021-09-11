package com.dust.exmall.fragments.others

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.CommentPreviewAdapter
import com.dust.exmall.adapters.recyclerview.OtherRelatedCategoriesAdapter
import com.dust.exmall.adapters.recyclerview.RecentlyAdapter
import com.dust.exmall.adapters.viewpager.ProductImagesAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.apicore.ApiServiceManager
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.CommentDataClass
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.bottomsheets.ProductDetailsBottomSheet
import com.dust.exmall.interfaces.maininterfaces.OnGetProducts
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlin.math.floor

class ProductDetailsFragment() : Fragment() {
    private lateinit var productImagesViewPager: ViewPager
    private lateinit var productDotsIndicator: DotsIndicator
    private lateinit var otherRelatedCategoriesRecyclerView: RecyclerView
    private lateinit var commentPreviewRecyclerView: RecyclerView
    private lateinit var similarProductsRecyclerView: RecyclerView
    private lateinit var usersBuySimilarRecyclerView: RecyclerView
    private lateinit var technicalFeatures: RelativeLayout
    private lateinit var sellerRelativeLayout: RelativeLayout
    private lateinit var closeButton: ImageView
    private lateinit var moreButton: ImageView
    private lateinit var likeButton: ImageView
    private lateinit var priceText: CTextView
    private lateinit var categoryText: CTextView
    private lateinit var titleText: TextView
    private lateinit var similarProductsTitle: TextView
    private lateinit var usersBuySimilarTitle: TextView
    private lateinit var coordinatorContainer: CoordinatorLayout
    private lateinit var loadingContainer: LinearLayout
    private lateinit var similarProducts: LinearLayout
    private lateinit var usersBuySimilar: LinearLayout
    private lateinit var strongPointsContainer: LinearLayout
    private lateinit var weakPointsContainer: LinearLayout
    private lateinit var writeYourComment: RelativeLayout
    private lateinit var answerAndAsk: RelativeLayout
    private lateinit var backIntroductions: RelativeLayout
    private lateinit var retryText: TextView

    private val animations = Animations()

    private lateinit var productData: ProductsDataClass
    private lateinit var apiServiceManager: ApiServiceManager

    private var IS_LIKED = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpViewsAnimations()
        setUpLoadingProgressBar()
        setUpApiServiceManager()
        getProductData()
        setUpCloseButton()
        setUpMoreButton()
        setUpFavoriteButton()
    }

    private fun setUpViewsAnimations() {
        closeButton.setOnTouchListener { v, motionEvent ->
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
        moreButton.setOnTouchListener { v, motionEvent ->
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
        likeButton.setOnTouchListener { v, motionEvent ->
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
        sellerRelativeLayout.setOnTouchListener { v, motionEvent ->
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
        technicalFeatures.setOnTouchListener { v, motionEvent ->
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
        strongPointsContainer.setOnTouchListener { v, motionEvent ->
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
        weakPointsContainer.setOnTouchListener { v, motionEvent ->
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
        answerAndAsk.setOnTouchListener { v, motionEvent ->
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
        writeYourComment.setOnTouchListener { v, motionEvent ->
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
        backIntroductions.setOnTouchListener { v, motionEvent ->
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

    private fun setUpLoadingProgressBar() {
        loadingContainer.findViewById<ProgressBar>(R.id.mainProgressBar).isIndeterminate = true
    }

    private fun setUpApiServiceManager() {
        apiServiceManager = ApiServiceManager()
    }

    private fun getProductData() {
        apiServiceManager.getSingleProduct(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                productData = data[0]
                Log.i("dataReceive" , "$productData")
                setUpProduct()
            }

            override fun onFailureGetProducts(message: String) {
                retryText.visibility = View.VISIBLE
            }

        }, requireArguments().getInt("ID"))
    }

    private fun setUpProduct() {
        setUpGlobalFeatures()
        setUpProductImagesViewPager()
        setUpTechnicalFeatures()
        setUpOtherRelatedCategoriesRecyclerView()
        setUpSimilarProductsLayOut()
        setUpUserBuySimilarLayOut()
        setUpPreviewCommentRecyclerView()
        coordinatorContainer.visibility = View.VISIBLE
        loadingContainer.visibility = View.GONE
    }

    private fun setUpTechnicalFeatures() {
        technicalFeatures.setOnClickListener {
            Log.i("tech" , "done")
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , TechnicalFeaturesFragment().newInstance(productData.id , productData.description))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("TechnicalFeaturesFragment")
                .commit()
        }
    }

    private fun setUpUserBuySimilarLayOut() {
        usersBuySimilarTitle.text = "خریداران این محصول، این کالا را هم خریده اند"
        usersBuySimilarRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apiServiceManager.getUserBuySimilarProducts(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                val listData = arrayListOf<ProductsDataClass>()
                try {
                    listData.addAll(data.subList(0, 9))
                } catch (e: Exception) {
                    listData.addAll(data)
                }
                usersBuySimilarRecyclerView.adapter = RecentlyAdapter(listData)
            }

            override fun onFailureGetProducts(message: String) {

            }

        }, productData.category)
    }

    private fun setUpSimilarProductsLayOut() {
        similarProductsTitle.text = "محصولات مشابه"
        similarProductsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apiServiceManager.getProductsByCategory(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                similarProductsRecyclerView.adapter = RecentlyAdapter(data)
            }

            override fun onFailureGetProducts(message: String) {

            }

        }, productData.category)
    }

    private fun setUpGlobalFeatures() {
        titleText.text = productData.title
        priceText.text = productData.price
        categoryText.text = productData.category
    }

    private fun setUpFavoriteButton() {
        likeButton.setOnClickListener {
            if (IS_LIKED) {
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                likeButton.setColorFilter(Color.BLACK)
                IS_LIKED = false
            } else {
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_24_filled)
                likeButton.setColorFilter(Color.RED)
                IS_LIKED = true
            }
        }
    }

    private fun setUpMoreButton() {
        moreButton.setOnClickListener {
            val dialog = ProductDetailsBottomSheet()
            dialog.show(requireActivity().supportFragmentManager, "ProductDetailsBottomSheet")
        }
    }

    private fun setUpCloseButton() {
        closeButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "ProductDetailsFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpPreviewCommentRecyclerView() {
        commentPreviewRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        commentPreviewRecyclerView.adapter =
            CommentPreviewAdapter(generateFakeComments(), requireContext())
    }

    private fun setUpOtherRelatedCategoriesRecyclerView() {
        otherRelatedCategoriesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        otherRelatedCategoriesRecyclerView.adapter = OtherRelatedCategoriesAdapter(arrayListOf())
    }

    private fun setUpProductImagesViewPager() {
        productImagesViewPager.adapter = ProductImagesAdapter(
            requireActivity().supportFragmentManager,
            arrayListOf(productData.image, productData.image, productData.image)
        )
        productDotsIndicator.setViewPager(productImagesViewPager)
    }

    private fun setUpViews(view: View) {
        productImagesViewPager = view.findViewById(R.id.productImagesViewPager)
        productDotsIndicator = view.findViewById(R.id.productDotsIndicator)
        otherRelatedCategoriesRecyclerView =
            view.findViewById(R.id.otherRelatedCategoriesRecyclerView)
        commentPreviewRecyclerView = view.findViewById(R.id.commentPreviewRecyclerView)
        closeButton = view.findViewById(R.id.closeButton)
        moreButton = view.findViewById(R.id.moreButton)
        likeButton = view.findViewById(R.id.likeButton)
        priceText = view.findViewById(R.id.priceText)
        categoryText = view.findViewById(R.id.categoryText)
        titleText = view.findViewById(R.id.titleText)
        coordinatorContainer = view.findViewById(R.id.coordinatorContainer)
        similarProducts = view.findViewById(R.id.similarProducts)
        usersBuySimilar = view.findViewById(R.id.usersBuySimilar)
        similarProductsTitle = similarProducts.findViewById(R.id.title)
        similarProductsRecyclerView =
            similarProducts.findViewById(R.id.recentlySeenProductsRecyclerView)
        usersBuySimilarTitle = usersBuySimilar.findViewById(R.id.title)
        usersBuySimilarRecyclerView =
            usersBuySimilar.findViewById(R.id.recentlySeenProductsRecyclerView)
        loadingContainer = view.findViewById(R.id.loadingContainer)
        sellerRelativeLayout = view.findViewById(R.id.sellerRelativeLayout)
        technicalFeatures = view.findViewById(R.id.technicalFeatures)
        strongPointsContainer = view.findViewById(R.id.strongPointsContainer)
        weakPointsContainer = view.findViewById(R.id.weakPointsContainer)
        writeYourComment = view.findViewById(R.id.writeYourComment)
        answerAndAsk = view.findViewById(R.id.answerAndAsk)
        backIntroductions = view.findViewById(R.id.backIntroductions)

        retryText = loadingContainer.findViewById(R.id.retryText)
        retryText.setOnClickListener {
            retryText.visibility = View.GONE
            getProductData()
        }
    }

    private fun generateFakeComments(): List<CommentDataClass> {
        val list = arrayListOf<CommentDataClass>()
        for (i in 0..4)
            list.add(
                CommentDataClass(
                    "زیبا بود$i",
                    floor(Math.random() * 4).toInt(),
                    "خیلی محصول خوبی بود$i",
                    "date $i",
                    "userName $i"
                )
            )
        return list
    }

    fun newInstance(id: Int): ProductDetailsFragment {
        val args = Bundle()
        args.putInt("ID", id)
        val fragment = ProductDetailsFragment()
        fragment.arguments = args
        return fragment
    }
}