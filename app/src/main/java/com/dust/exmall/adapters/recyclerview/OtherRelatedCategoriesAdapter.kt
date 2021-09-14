package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.squareup.picasso.Picasso

class OtherRelatedCategoriesAdapter(var dataList:List<String> , var currentCategory:String , var imageUrl:String):RecyclerView.Adapter<OtherRelatedCategoriesAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_other_related_categories , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (position == 0){
            holder.categoryName.text = currentCategory
            Picasso.get().load(imageUrl).into(holder.categoryImage)
        }else{
            holder.categoryName.text = dataList[position -1]
            holder.categoryImage.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = dataList.size + 1

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var categoryName = itemView.findViewById<CTextView>(R.id.categoryName)
        var categoryImage = itemView.findViewById<ImageView>(R.id.categoryImage)
    }
}