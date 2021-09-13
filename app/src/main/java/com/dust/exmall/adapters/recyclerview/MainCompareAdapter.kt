package com.dust.exmall.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass

class MainCompareAdapter(var selectedProduct:ProductsDataClass , var comparableProduct:ProductsDataClass , val context: Context) : RecyclerView.Adapter<MainCompareAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemContainer = itemView.findViewById<LinearLayout>(R.id.itemContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_compare, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_main_compare_part , null , false)
        layout.findViewById<CTextView>(R.id.featureTitle).text = "توضیحات"
        layout.findViewById<CTextView>(R.id.selectedProductFeature).text = selectedProduct.description
        layout.findViewById<CTextView>(R.id.comparableProductFeature).text = comparableProduct.description
        holder.itemContainer.addView(layout)
    }

    override fun getItemCount(): Int = 1
}