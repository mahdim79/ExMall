package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.CommentPreviewAdapter
import com.dust.exmall.adapters.recyclerview.OtherRelatedCategoriesAdapter
import com.dust.exmall.adapters.viewpager.ProductImagesAdapter
import com.dust.exmall.dataclasses.CommentDataClass
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlin.math.floor

class ProductDetailsFragment(): Fragment() {
    private lateinit var productImagesViewPager:ViewPager
    private lateinit var productDotsIndicator:DotsIndicator
    private lateinit var otherRelatedCategoriesRecyclerView:RecyclerView
    private lateinit var commentPreviewRecyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpProductImagesViewPager()
        setUpOtherRelatedCategoriesRecyclerView()
        setUpPreviewCommentRecyclerView()
    }

    private fun setUpPreviewCommentRecyclerView() {
        commentPreviewRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        commentPreviewRecyclerView.adapter = CommentPreviewAdapter(generateFakeComments() , requireContext() )
    }

    private fun setUpOtherRelatedCategoriesRecyclerView() {
        otherRelatedCategoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        otherRelatedCategoriesRecyclerView.adapter = OtherRelatedCategoriesAdapter(arrayListOf())
    }

    private fun setUpProductImagesViewPager() {
        // productImagesViewPager.adapter = ProductImagesAdapter()
//        productDotsIndicator.setViewPager(productImagesViewPager)
    }

    private fun setUpViews(view: View) {
        productImagesViewPager = view.findViewById(R.id.productImagesViewPager)
        productDotsIndicator = view.findViewById(R.id.productDotsIndicator)
        otherRelatedCategoriesRecyclerView = view.findViewById(R.id.otherRelatedCategoriesRecyclerView)
        commentPreviewRecyclerView = view.findViewById(R.id.commentPreviewRecyclerView)
    }

    private fun generateFakeComments():List<CommentDataClass>{
        val list = arrayListOf<CommentDataClass>()
        for (i in 0..4)
            list.add(CommentDataClass("زیبا بود$i" , floor(Math.random() * 4).toInt() , "خیلی محصول خوبی بود$i" , "date $i" , "userName $i"))
    return list
    }
    fun newInstance(): ProductDetailsFragment {
        val args = Bundle()
        val fragment = ProductDetailsFragment()
        fragment.arguments = args
        return fragment
    }
}