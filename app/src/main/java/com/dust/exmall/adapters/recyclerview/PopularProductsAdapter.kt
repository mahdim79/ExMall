package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.others.ProductDetailsFragment
import com.squareup.picasso.Picasso

class PopularProductsAdapter(var list: List<ProductsDataClass> , var fragmentManager: FragmentManager) :
    RecyclerView.Adapter<PopularProductsAdapter.MainViewHolder>() {

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var divider = itemView.findViewById<View>(R.id.divider)
        var productImageOne = itemView.findViewById<ImageView>(R.id.productImageOne)
        var productImageTwo = itemView.findViewById<ImageView>(R.id.productImageTwo)
        var productImageThree = itemView.findViewById<ImageView>(R.id.productImageThree)
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

        try {

            Picasso.get().load(list[(position * 3)].image).into(holder.productImageOne)
            holder.productImageOne.setOnClickListener {startDetailsFragment()}
            Picasso.get().load(list[(position * 3) + 1].image).into(holder.productImageTwo)
            holder.productImageTwo.setOnClickListener {startDetailsFragment()}
            Picasso.get().load(list[(position * 3) + 2].image).into(holder.productImageThree)
            holder.productImageThree.setOnClickListener {startDetailsFragment()}

        }catch (e:Exception){}

    }

    private fun startDetailsFragment(){
        fragmentManager.beginTransaction()
            .add(R.id.mainContainer , ProductDetailsFragment().newInstance())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack("ProductDetailsFragment")
            .commit()
    }

    override fun getItemCount(): Int = 3

}