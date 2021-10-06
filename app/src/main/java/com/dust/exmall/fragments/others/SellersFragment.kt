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
import com.dust.exmall.adapters.recyclerview.SellersAdapter
import com.dust.exmall.dataclasses.SellerDataClass

class SellersFragment() : Fragment() {
    private lateinit var backImage: ImageView
    private lateinit var sellersRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sellers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpSellersRecyclerView()
    }

    private fun setUpSellersRecyclerView() {
        sellersRecyclerView.adapter =
            SellersAdapter(generateFakeSellers(), requireActivity().supportFragmentManager , requireContext())
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "SellersFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        sellersRecyclerView = view.findViewById(R.id.sellersRecyclerView)

        sellersRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun generateFakeSellers(): List<SellerDataClass> {
        val list = arrayListOf<SellerDataClass>()

        for (i in 0..7) {
            list.add(SellerDataClass(i, "دیجی کالا"))
        }

        return list
    }
}