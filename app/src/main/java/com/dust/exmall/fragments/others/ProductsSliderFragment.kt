package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dust.exmall.R
import com.dust.exmall.dataclasses.ProductsDataClass

class ProductsSliderFragment(var listData: List<ProductsDataClass>, var position: Int) : Fragment() {
    private lateinit var countTextOne: TextView
    private lateinit var countTextTwo: TextView
    private lateinit var countTextThree: TextView
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
    }

}