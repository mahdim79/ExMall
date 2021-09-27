package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.others.ProductDetailsFragment
import com.squareup.picasso.Picasso

class ProductsAdapterTwo(var list:List<ProductsDataClass>, var fragmentManager: FragmentManager) : RecyclerView.Adapter<ProductsAdapterTwo.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_compare_products , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.priceText.text = list[position].price
        Picasso.get().load(list[position].image).into(holder.productImage)
        holder.itemView.setOnClickListener {
            fragmentManager.beginTransaction()
                .add(R.id.mainContainer , ProductDetailsFragment().newInstance(list[position].id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int = list.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var priceText = itemView.findViewById<CTextView>(R.id.priceText)
        var rate = itemView.findViewById<CTextView>(R.id.rate)
        var title = itemView.findViewById<TextView>(R.id.title)
        var productImage = itemView.findViewById<ImageView>(R.id.productImage)
    }
}