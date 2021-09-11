package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.dust.exmall.R
import com.squareup.picasso.Picasso

class ProductImagesFragment():Fragment() {
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
        Picasso.get().load(requireArguments().getString("URL")).into(productImage)
    }

    private fun setUpViews(view: View) {
        productImage = view.findViewById(R.id.productImage)
    }

    fun newInstance(url: String): ProductImagesFragment {
        val args = Bundle()
        args.putString("URL", url)
        val fragment = ProductImagesFragment()
        fragment.arguments = args
        return fragment
    }
}