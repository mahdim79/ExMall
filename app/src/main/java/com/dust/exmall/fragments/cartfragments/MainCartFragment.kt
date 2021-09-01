package com.dust.exmall.fragments.cartfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.CartProductsAdapter
import com.dust.exmall.adapters.recyclerview.ForSaleAdapter
import com.dust.exmall.adapters.recyclerview.PlusRegistrationAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CButton
import com.dust.exmall.dataclasses.AmazingDataClass

class MainCartFragment() : Fragment() {
    private lateinit var headerText: TextView
    private lateinit var forSaleRecyclerView: RecyclerView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var superMarketRecyclerView: RecyclerView
    private lateinit var otherRecyclerView: RecyclerView
    private lateinit var loginAdviseContainer: RelativeLayout
    private lateinit var continueBuyButton: CButton

    private val animations = Animations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_cart , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpSuggestionRecyclerView()
        setUpAnimations()
        setUpMainRecyclerViews()
        setUpProductsRecyclerView()
    }

    private fun setUpProductsRecyclerView() {
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext() , 3)
        productsRecyclerView.adapter = PlusRegistrationAdapter(generateFakeData())
    }

    private fun setUpMainRecyclerViews() {
        superMarketRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        otherRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)

        superMarketRecyclerView.adapter = CartProductsAdapter(generateFakeData())
        otherRecyclerView.adapter = CartProductsAdapter(generateFakeData())
    }

    private fun setUpAnimations() {
        loginAdviseContainer.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                v.startAnimation(animations.getFadeInAnimation())
            } else {
                v.startAnimation(animations.getFadeOutAnimation())
            }
            true
        }
    }

    private fun setUpSuggestionRecyclerView() {
        forSaleRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL ,false)
        forSaleRecyclerView.adapter = ForSaleAdapter(generateFakeData())
        headerText.text = "پیشنهاد برای شما"
    }

    private fun setUpViews(view: View) {
        headerText = view.findViewById(R.id.headerText)
        forSaleRecyclerView = view.findViewById(R.id.forSaleRecyclerView)
        loginAdviseContainer = view.findViewById(R.id.loginAdviseContainer)
        continueBuyButton = view.findViewById(R.id.continueBuyButton)
        otherRecyclerView = view.findViewById(R.id.otherRecyclerView)
        superMarketRecyclerView = view.findViewById(R.id.superMarketRecyclerView)
        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)

        continueBuyButton.setOnClickListener {

        }

    }
    private fun generateFakeData(): List<AmazingDataClass> {
        val list = arrayListOf<AmazingDataClass>()
        for (i in 0..14) {
            list.add(AmazingDataClass("hello"))
        }
        return list
    }
}