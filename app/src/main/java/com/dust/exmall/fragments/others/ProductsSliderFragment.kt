package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dust.exmall.R
import com.dust.exmall.dataclasses.ProductsDataClass
import com.squareup.picasso.Picasso

class ProductsSliderFragment(var listData: List<ProductsDataClass>, var position: Int , var highReviewedMode:Boolean) : Fragment() {

    private lateinit var countTextOne: TextView
    private lateinit var countTextTwo: TextView
    private lateinit var countTextThree: TextView
    private lateinit var productImageOne: ImageView
    private lateinit var productImageTwo: ImageView
    private lateinit var productImageThree: ImageView
    private lateinit var productTitleOne: TextView
    private lateinit var productTitleTwo: TextView
    private lateinit var productTitleThree: TextView
    private lateinit var reviewCountOne: TextView
    private lateinit var reviewCountTwo: TextView
    private lateinit var reviewCountThree: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_product_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setCounts()
        setImages()
        setTitles()
        setReviewCount()
    }

    private fun setReviewCount() {
        if (!highReviewedMode){
            reviewCountOne.visibility = View.GONE
            reviewCountTwo.visibility = View.GONE
            reviewCountThree.visibility = View.GONE
        }else{

        }
    }

    private fun setTitles() {
        productTitleOne.text = listData[0].title
        productTitleTwo.text = listData[1].title
        productTitleThree.text = listData[2].title
    }

    private fun setImages() {
        Picasso.get().load(listData[0].image).into(productImageOne)
        Picasso.get().load(listData[1].image).into(productImageTwo)
        Picasso.get().load(listData[2].image).into(productImageThree)
    }

    private fun setCounts() {
        var firstCount = ""
        var secondCount = ""
        var thirdCount = ""
        when (position) {
            0 -> {
                firstCount = "13"
                secondCount = "14"
                thirdCount = "15"
            }
            1 -> {
                firstCount = "10"
                secondCount = "11"
                thirdCount = "12"
            }
            2 -> {
                firstCount = "7"
                secondCount = "8"
                thirdCount = "9"
            }
            3 -> {
                firstCount = "4"
                secondCount = "5"
                thirdCount = "6"
            }
            4 -> {
                firstCount = "1"
                secondCount = "2"
                thirdCount = "3"
            }
        }
        countTextOne.text = firstCount
        countTextTwo.text = secondCount
        countTextThree.text = thirdCount
    }

    private fun setUpViews(view: View) {
        countTextOne = view.findViewById(R.id.countTextOne)
        countTextTwo = view.findViewById(R.id.countTextTwo)
        countTextThree = view.findViewById(R.id.countTextThree)
        productImageOne = view.findViewById(R.id.productImageOne)
        productImageTwo = view.findViewById(R.id.productImageTwo)
        productImageThree = view.findViewById(R.id.productImageThree)
        productTitleOne = view.findViewById(R.id.productTitleOne)
        productTitleTwo = view.findViewById(R.id.productTitleTwo)
        productTitleThree = view.findViewById(R.id.productTitleThree)
        reviewCountOne = view.findViewById(R.id.reviewCountOne)
        reviewCountTwo = view.findViewById(R.id.reviewCountTwo)
        reviewCountThree = view.findViewById(R.id.reviewCountThree)
    }

}