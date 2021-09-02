package com.dust.exmall.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.UserOrdersRecyclerViewAdapter

class MyExMallFragment : Fragment() {
    private lateinit var userOrdersRecyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_myexmall , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpUserOrdersRecyclerView()
    }

    private fun setUpUserOrdersRecyclerView() {
        userOrdersRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        userOrdersRecyclerView.adapter = UserOrdersRecyclerViewAdapter()
    }

    private fun setUpViews(view: View) {
        userOrdersRecyclerView = view.findViewById(R.id.userOrdersRecyclerView)
    }
}