package com.dust.exmall.fragments.cartfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.CartProductsAdapter
import com.dust.exmall.dataclasses.AmazingDataClass

class NextCartFragment(): Fragment() {
    private lateinit var superMarketRecyclerView:RecyclerView
    private lateinit var otherRecyclerView:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_cart , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpRecyclerViews()
    }

    private fun setUpRecyclerViews() {
        superMarketRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        otherRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)

        superMarketRecyclerView.adapter = CartProductsAdapter(generateFakeData())
        otherRecyclerView.adapter = CartProductsAdapter(generateFakeData())
    }

    private fun setUpViews(view: View) {
        superMarketRecyclerView = view.findViewById(R.id.superMarketRecyclerView)
        otherRecyclerView = view.findViewById(R.id.otherRecyclerView)
    }

    private fun generateFakeData(): List<AmazingDataClass> {
        val list = arrayListOf<AmazingDataClass>()
        for (i in 0..3) {
            list.add(AmazingDataClass("hello"))
        }
        return list
    }
}