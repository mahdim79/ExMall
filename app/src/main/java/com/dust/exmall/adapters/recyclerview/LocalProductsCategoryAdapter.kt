package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.CategoryDataClass
import com.dust.exmall.fragments.others.specialfragments.LocalProductsCategoryFragment
import com.squareup.picasso.Picasso

class LocalProductsCategoryAdapter(var dataList:List<CategoryDataClass> , var fragmentManager: FragmentManager):RecyclerView.Adapter<LocalProductsCategoryAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_local_products , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemTitle.text = dataList[position].name
        holder.itemCount.text = "+${dataList[position].productCount} کالا"
        Picasso.get().load(dataList[position].image).into(holder.itemImage)

        holder.itemView.setOnClickListener {
            fragmentManager.beginTransaction()
                .add(R.id.mainContainer , LocalProductsCategoryFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("LocalProductsCategoryFragment")
                .commit()
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var itemImage = itemView.findViewById<ImageView>(R.id.itemImage)
        var itemTitle = itemView.findViewById<CTextView>(R.id.itemTitle)
        var itemCount = itemView.findViewById<CTextView>(R.id.itemCount)
    }

}