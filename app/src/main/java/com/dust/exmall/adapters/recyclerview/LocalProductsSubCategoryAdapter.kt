package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.CategoryDataClass
import com.squareup.picasso.Picasso

class LocalProductsSubCategoryAdapter(var dataList:List<CategoryDataClass>) : RecyclerView.Adapter<LocalProductsSubCategoryAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val itemImage = itemView.findViewById<ImageView>(R.id.itemImage)
        val itemTitle = itemView.findViewById<CTextView>(R.id.itemTitle)
        val itemCount = itemView.findViewById<CTextView>(R.id.itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_local_products , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemCount.visibility = View.GONE

        Picasso.get().load(dataList[position].image).into(holder.itemImage)
        holder.itemTitle.text = dataList[position].name
    }

    override fun getItemCount(): Int = dataList.size
}