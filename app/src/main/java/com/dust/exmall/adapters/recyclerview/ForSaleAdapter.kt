package com.dust.exmall.adapters.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.dataclasses.AmazingDataClass

class ForSaleAdapter(var list:List<AmazingDataClass>) : RecyclerView.Adapter<ForSaleAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forsale , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (position == 7)
            holder.divider.setBackgroundColor(Color.WHITE)
    }

    override fun getItemCount(): Int = 8

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var divider = itemView.findViewById<View>(R.id.divider)
    }
}