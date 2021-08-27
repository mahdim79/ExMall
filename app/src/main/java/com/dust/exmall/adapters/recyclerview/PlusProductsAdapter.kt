package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.dataclasses.AmazingDataClass

class PlusProductsAdapter(var list:List<AmazingDataClass>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val NORMAL_ITEM = 0
    private val SHOW_ALL_ITEM: Int = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SHOW_ALL_ITEM)
            return ShowAllViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_show_all_plus_products , parent , false))
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_normal_plus_products , parent , false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainViewHolder){
            val mHolder = holder as MainViewHolder
        }else{
            val mHolder = holder as ShowAllViewHolder
        }
    }

    override fun getItemCount(): Int = list.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == list.size)
            return SHOW_ALL_ITEM
        return NORMAL_ITEM
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    inner class ShowAllViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}