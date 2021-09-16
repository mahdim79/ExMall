package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.others.ProductDetailsFragment
import com.squareup.picasso.Picasso

class RecentlyAdapter(var list:List<ProductsDataClass> , var fragmentManager: FragmentManager) : RecyclerView.Adapter<RecentlyAdapter.MainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_normal_plus_products , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        setUpProductDetails(holder , position)
    }

    private fun setUpProductDetails(mHolder: RecentlyAdapter.MainViewHolder, position: Int) {
        Picasso.get().load(list[position].image).into(mHolder.productImage)
        mHolder.productName.text = list[position].title
        mHolder.productPrice.text = list[position].price

        mHolder.itemView.setOnClickListener {
            startDetailsFragment(list[position].id)
        }
    }

    private fun startDetailsFragment(id:Int){
        fragmentManager.beginTransaction()
            .add(R.id.mainContainer , ProductDetailsFragment().newInstance(id))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun getItemCount(): Int = list.size

    inner class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val productPrice = itemView.findViewById<TextView>(R.id.productPrice)
        val productName = itemView.findViewById<CTextView>(R.id.productName)
        val productImage = itemView.findViewById<ImageView>(R.id.productImage)
    }
}