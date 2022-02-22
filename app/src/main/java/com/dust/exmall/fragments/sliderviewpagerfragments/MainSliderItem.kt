package com.dust.exmall.fragments.sliderviewpagerfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dust.exmall.R
import com.dust.exmall.dataclasses.CardsDataClass
import com.dust.exmall.fragments.others.CategoryProductsFragment
import com.dust.exmall.fragments.others.TagProductsFragment
import com.dust.exmall.fragments.others.WebPageFragment
import com.dust.exmall.fragments.others.specialfragments.LocalProductsFragment
import com.squareup.picasso.Picasso

class MainSliderItem(var dataList: List<CardsDataClass>, var position: Int) : Fragment() {
    private lateinit var sliderImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_mainslider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpSliderContent()
    }

    private fun setUpSliderContent() {
        Picasso.get().load(dataList[position].image).into(sliderImageView)
        sliderImageView.setOnClickListener {
            when (dataList[position].type) {
                "LOCAL_PRODUCTS" -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(R.id.mainContainer , LocalProductsFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("LocalProductsFragment")
                        .commit()
                }
                "CATEGORY" -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(
                            R.id.mainContainer,
                            CategoryProductsFragment().newInstance(dataList[position].name)
                        )
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("CategoryProductsFragment")
                        .commit()
                }
                "TAG" -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(
                            R.id.mainContainer,
                            TagProductsFragment().newInstance(dataList[position].tag)
                        )
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("TagProductsFragment")
                        .commit()
                }
                "LINK" -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(
                            R.id.mainContainer,
                            WebPageFragment().newInstance(
                                dataList[position].name,
                                dataList[position].link
                            )
                        )
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("WebPageFragment")
                        .commit()
                }
            }
        }
    }

    private fun setUpViews(view: View) {
        sliderImageView = view.findViewById(R.id.sliderImageView)
    }
}