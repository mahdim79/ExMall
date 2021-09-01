package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.dataclasses.AmazingDataClass

class CartProductsAdapter(var list:List<AmazingDataClass>): RecyclerView.Adapter<CartProductsAdapter.MainViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart_products , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = list.size

    inner class MainViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }
}