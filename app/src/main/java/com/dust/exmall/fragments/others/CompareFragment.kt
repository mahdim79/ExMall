package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.MainCompareAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.apicore.ApiServiceManager
import com.dust.exmall.customviews.CButton
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.interfaces.maininterfaces.OnGetProducts
import com.squareup.picasso.Picasso

class CompareFragment() : Fragment() {
    private lateinit var mainCompareRecyclerView: RecyclerView
    private lateinit var selectedProductImage: ImageView
    private lateinit var comparableProductImage: ImageView
    private lateinit var backImage: ImageView
    private lateinit var deleteSelectedImage: ImageView
    private lateinit var addProductButton: CButton
    private lateinit var selectedLinearDetails: LinearLayout
    private lateinit var comparableProductPrice: CTextView
    private lateinit var selectedProductPrice: CTextView
    private lateinit var selectedProductTitle: CTextView
    private lateinit var comparableProductTitle: CTextView
    private lateinit var mainProgressBar: ProgressBar
    private lateinit var retryText: TextView
    private lateinit var componentsContainer: CoordinatorLayout

    private lateinit var apiServiceManager: ApiServiceManager
    private lateinit var selectedProductData: ProductsDataClass
    private lateinit var comparableProductData: ProductsDataClass
    private var dataReceived = 0
    private var failureCount = 0

    private val animations = Animations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compare_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpApiServiceManager()
        setUpBackImage()
        setUpData()
    }

    private fun setViewsAnimations(){
        selectedProductTitle.startAnimation(animations.getScaleAnimation())
        comparableProductTitle.startAnimation(animations.getScaleAnimation())
        comparableProductImage.startAnimation(animations.getScaleAnimation())
        selectedProductImage.startAnimation(animations.getScaleAnimation())
    }

    private fun setHeaderData() {

        Picasso.get().load(comparableProductData.image).into(comparableProductImage)
        comparableProductPrice.text = comparableProductData.price
        comparableProductTitle.text = comparableProductData.title

        Picasso.get().load(selectedProductData.image).into(selectedProductImage)
        selectedProductPrice.text = selectedProductData.price
        selectedProductTitle.text = selectedProductData.title

        addProductButton.setOnClickListener {
            backImage.performClick()
        }

        deleteSelectedImage.setOnClickListener {
            deleteSelectedImage.visibility = View.GONE
            selectedProductImage.visibility = View.GONE
            selectedLinearDetails.visibility = View.GONE
            selectedProductTitle.visibility = View.GONE
            selectedProductTitle.text = ""
            addProductButton.visibility = View.VISIBLE
            mainCompareRecyclerView.adapter =
                MainCompareAdapter(ProductsDataClass(0,"", "" , "-" , "" , ""), comparableProductData, requireContext())
        }

    }

    private fun setUpApiServiceManager() {
        apiServiceManager = ApiServiceManager()
    }

    private fun setUpData() {
        // selected Product Data
        apiServiceManager.getSingleProduct(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                selectedProductData = data[0]
                checkAndStartSettingUpRecyclerView()
            }

            override fun onFailureGetProducts(message: String) {
                checkFailureRequests()
            }

        }, requireArguments().getInt("ID2"))

        // comparable Product Data
        apiServiceManager.getSingleProduct(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                comparableProductData = data[0]
                checkAndStartSettingUpRecyclerView()
            }

            override fun onFailureGetProducts(message: String) {
                checkFailureRequests()
            }

        }, requireArguments().getInt("ID1"))
    }

    private fun checkFailureRequests() {
        failureCount++
        if (failureCount == 2) {
            retryText.visibility = View.VISIBLE
            failureCount = 0
        }
    }

    private fun checkAndStartSettingUpRecyclerView() {
        if (dataReceived != 0) {
            setHeaderData()
            setUpMainCompareRecyclerView()
            mainProgressBar.visibility = View.GONE
            componentsContainer.visibility = View.VISIBLE
            setViewsAnimations()
        } else {
            dataReceived++
        }
        mainProgressBar.progress = 8
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "CompareFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpMainCompareRecyclerView() {
        mainCompareRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mainCompareRecyclerView.adapter =
            MainCompareAdapter(selectedProductData, comparableProductData, requireContext())
    }

    private fun setUpViews(view: View) {
        mainCompareRecyclerView = view.findViewById(R.id.mainCompareRecyclerView)
        selectedProductImage = view.findViewById(R.id.selectedProductImage)
        comparableProductImage = view.findViewById(R.id.comparableProductImage)
        comparableProductPrice = view.findViewById(R.id.comparableProductPrice)
        backImage = view.findViewById(R.id.backImage)
        selectedProductPrice = view.findViewById(R.id.selectedProductPrice)
        selectedProductTitle = view.findViewById(R.id.selectedProductTitle)
        comparableProductTitle = view.findViewById(R.id.comparableProductTitle)
        componentsContainer = view.findViewById(R.id.componentsContainer)
        retryText = view.findViewById(R.id.retryText)
        mainProgressBar = view.findViewById(R.id.mainProgressBar)
        deleteSelectedImage = view.findViewById(R.id.deleteSelectedImage)
        selectedLinearDetails = view.findViewById(R.id.selectedLinearDetails)
        addProductButton = view.findViewById(R.id.addProductButton)

        retryText.setOnClickListener {
            retryText.visibility = View.GONE
            setUpData()
        }
    }

    fun newInstance(id1: Int, id2: Int): CompareFragment {
        val args = Bundle()
        args.putInt("ID1", id1)
        args.putInt("ID2", id2)
        val fragment = CompareFragment()
        fragment.arguments = args
        return fragment
    }
}