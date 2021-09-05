package com.dust.exmall.fragments.sliderviewpagerfragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.dust.exmall.R
import com.squareup.picasso.Picasso

class MainSliderItem:Fragment() {
    private lateinit var sliderImageView:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_mainslider , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpSliderContent()
    }

    private fun setUpSliderContent() {
        Picasso.get().load(requireArguments().getString("IMAGE_URL")).into(sliderImageView)
        sliderImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(requireArguments().getString("URL")))
            requireActivity().startActivity(Intent.createChooser(intent , "select desire app to open link!"))
        }
    }

    private fun setUpViews(view: View) {
        sliderImageView = view.findViewById(R.id.sliderImageView)
    }

    fun newInstance(url:String , imageUrl:String): MainSliderItem {
        val args = Bundle()
        args.putString("URL" , url)
        args.putString("IMAGE_URL" , imageUrl)
        val fragment = MainSliderItem()
        fragment.arguments = args
        return fragment
    }
}