package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.TechnicalFeatureAdapter
import com.dust.exmall.dataclasses.FeatureDataClass
import com.dust.exmall.dataclasses.SingleFeatureDataClass

class TechnicalFeaturesFragment(): Fragment() {
    private lateinit var backImage:ImageView
    private lateinit var featuresRecyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_technical_features , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpMainRecyclerView()
    }

    private fun setUpMainRecyclerView() {
        featuresRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        featuresRecyclerView.adapter = TechnicalFeatureAdapter(generateFakeFeatures() , requireContext())
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack("TechnicalFeaturesFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        featuresRecyclerView = view.findViewById(R.id.featuresRecyclerView)
    }

    private fun generateFakeFeatures():List<FeatureDataClass>{
        val list = arrayListOf<FeatureDataClass>()
        for (i in 0..5){
            val dataList = arrayListOf<SingleFeatureDataClass>()
            for (j in 0..2)
                dataList.add(SingleFeatureDataClass("وزن$j" , arrayListOf("1100kg" , "1200kg")))
            list.add(FeatureDataClass("مشخصات$i" , dataList))
        }
        return list
    }

    fun newInstance(id: Int, descriptions: String): TechnicalFeaturesFragment {
        val args = Bundle()
        args.putString("DESCRIPTIONS", descriptions)
        args.putInt("ID" , id)
        val fragment = TechnicalFeaturesFragment()
        fragment.arguments = args
        return fragment
    }
}