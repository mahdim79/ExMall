package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.squareup.picasso.Picasso

class PlusProductsAdapter(var list:List<ProductsDataClass>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            setUpProductDetails(mHolder , position)
        }else{
            val mHolder = holder as ShowAllViewHolder
        }
    }

    private fun setUpProductDetails(mHolder: MainViewHolder , position: Int) {
        Picasso.get().load(list[position].image).into(mHolder.productImage)
        mHolder.productName.text = list[position].title
        mHolder.productPrice.text = list[position].price
    }

    override fun getItemCount(): Int = list.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == list.size)
            return SHOW_ALL_ITEM
        return NORMAL_ITEM
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productPrice = itemView.findViewById<TextView>(R.id.productPrice)
        val productName = itemView.findViewById<CTextView>(R.id.productName)
        val productImage = itemView.findViewById<ImageView>(R.id.productImage)
    }

    inner class ShowAllViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}