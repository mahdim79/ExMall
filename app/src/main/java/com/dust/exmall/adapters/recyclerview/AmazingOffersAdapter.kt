package com.dust.exmall.adapters.recyclerview

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.dataclasses.AmazingOffersDataClass

class AmazingOffersAdapter(var list: List<AmazingOffersDataClass>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val NORMAL_ITEM = 0
    private val HEADER_ITEM: Int = 1
    private val SHOW_ALL_ITEM = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NORMAL_ITEM -> MainViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_amazing_offers, parent, false)
            )
            HEADER_ITEM -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header_offers, parent, false)
            )
            else /*show all item*/ -> ShowAllViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_show_all_offers, parent, false)
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainViewHolder) {
            val mHolder = holder as MainViewHolder
            setOldPrice(mHolder , "255,000")
        } else if (holder is HeaderViewHolder) {
            val mHolder = holder as HeaderViewHolder
        } else {
            val mHolder = holder as ShowAllViewHolder
        }
    }

    override fun getItemCount(): Int = list.size + 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                HEADER_ITEM
            }
            (list.size + 1) -> {
                SHOW_ALL_ITEM
            }
            else -> {
                NORMAL_ITEM
            }
        }
    }

    private fun setOldPrice(holder: MainViewHolder , oldPrice:String){
        val spannable = SpannableString(oldPrice)
        spannable.setSpan(StrikethroughSpan() , 0 , oldPrice.length , SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
        holder.txtOldPrice.text = spannable
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtOldPrice = itemView.findViewById<TextView>(R.id.txtOldPrice)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class ShowAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}