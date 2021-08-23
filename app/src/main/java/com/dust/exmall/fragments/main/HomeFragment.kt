package com.dust.exmall.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView

class HomeFragment : Fragment() , View.OnClickListener {
    private lateinit var search_text: CTextView
    private lateinit var exMallText: TextView
    private lateinit var search_image: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
    }

    private fun setUpViews(view: View) {
        search_text = view.findViewById(R.id.search_text)
        search_image = view.findViewById(R.id.search_image)
        exMallText = view.findViewById(R.id.exMallText)

        search_text.setOnClickListener(this)
        search_image.setOnClickListener(this)
        exMallText.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.search_text -> {}
            R.id.search_image -> {}
            R.id.exMallText -> {}
        }
    }

}