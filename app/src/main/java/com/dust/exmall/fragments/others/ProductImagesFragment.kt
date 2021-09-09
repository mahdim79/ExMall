package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dust.exmall.R

class ProductImagesFragment():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_images , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    fun newInstance(url: String): ProductImagesFragment {
        val args = Bundle()
        args.putString("URL", url)
        val fragment = ProductImagesFragment()
        fragment.arguments = args
        return fragment
    }
}