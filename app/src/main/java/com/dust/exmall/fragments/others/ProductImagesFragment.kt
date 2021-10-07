package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dust.exmall.R
import com.squareup.picasso.Picasso

class ProductImagesFragment(var imageList:List<String> , var position:Int , var fullImageMode:Boolean):Fragment() {
    private lateinit var productImage:ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_images , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpImage()
    }

    private fun setUpImage() {
        Picasso.get().load(imageList[position]).into(productImage)
        if (!fullImageMode){
            productImage.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer , ProductFullImagesFragment(imageList , position))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("ProductFullImagesFragment")
                    .commit()
            }
        }
    }

    private fun setUpViews(view: View) {
        productImage = view.findViewById(R.id.productImage)
    }
}