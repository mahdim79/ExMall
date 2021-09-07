package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R

class SearchAdapter(var mainViewType: Int): RecyclerView.Adapter<SearchAdapter.MainViewHolder>() {
    private val CATEGORY_VIEW_TYPE = 0
    private val RELATIVE_VIEW_TYPE = 1

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}