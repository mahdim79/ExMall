package com.dust.exmall.adapters.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView

class GoodAndWeakPointsAdapter(val list: List<String> , var weakType :Boolean) :
    RecyclerView.Adapter<GoodAndWeakPointsAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemIcon = itemView.findViewById<ImageView>(R.id.itemIcon)
        var itemText = itemView.findViewById<CTextView>(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_good_and_weak_points, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if(weakType){
            holder.itemIcon.setImageResource(R.drawable.ic_baseline_remove_24)
            holder.itemIcon.setColorFilter(Color.RED)
        }
        holder.itemText.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}