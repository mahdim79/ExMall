package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.dataclasses.ProductsDataClass

class PlusRegistrationAdapter(var list:List<ProductsDataClass>):RecyclerView.Adapter<PlusRegistrationAdapter.MainViewHolder>() {

    inner class MainViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plus_registration , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

    }

    override fun getItemCount(): Int =list.size
}