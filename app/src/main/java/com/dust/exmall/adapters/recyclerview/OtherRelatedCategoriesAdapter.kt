package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.squareup.picasso.Picasso

class OtherRelatedCategoriesAdapter(var dataList:List<Pair<String , String>>):RecyclerView.Adapter<OtherRelatedCategoriesAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_other_related_categories , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.categoryName.text = dataList[position].first
        Picasso.get().load(dataList[position].second).into(holder.categoryImage)
    }

    override fun getItemCount(): Int = dataList.size

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var categoryName = itemView.findViewById<CTextView>(R.id.categoryName)
        var categoryImage = itemView.findViewById<ImageView>(R.id.categoryImage)
    }
}