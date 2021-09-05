package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.dataclasses.ProductsDataClass

class PopularProductsAdapter(var list: List<ProductsDataClass>) :
    RecyclerView.Adapter<PopularProductsAdapter.MainViewHolder>() {

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var divider = itemView.findViewById<View>(R.id.divider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_popular_products, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (position == 2)
            holder.divider.visibility = View.GONE
    }

    override fun getItemCount(): Int = 3

}