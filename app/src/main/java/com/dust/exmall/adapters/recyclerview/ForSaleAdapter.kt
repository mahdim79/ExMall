package com.dust.exmall.adapters.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.others.ProductDetailsFragment
import com.squareup.picasso.Picasso
import kotlin.math.floor

class ForSaleAdapter(var list: List<ProductsDataClass>, var fragmentManager: FragmentManager) :
    RecyclerView.Adapter<ForSaleAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_forsale, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (position == initItemCount() - 1)
            holder.divider.setBackgroundColor(Color.WHITE)

        try {
            Picasso.get().load(list[position * 2].image).into(holder.imageProductOne)
            holder.titleProductOne.text = list[position * 2].title
            holder.priceProductOne.text = list[position * 2].price
            holder.productContainerOne.setOnClickListener {
                startDetailsFragment(list[position * 2].id)
            }

            Picasso.get().load(list[(position * 2 + 1)].image).into(holder.imageProductTwo)
            holder.titleProductTwo.text = list[(position * 2 + 1)].title
            holder.priceProductTwo.text = list[(position * 2 + 1)].price
            holder.productContainerTwo.setOnClickListener {
                startDetailsFragment(list[(position * 2 + 1)].id)
            }
        } catch (e: Exception) {
            holder.titleProductTwo.visibility = View.GONE
            holder.priceLinearTwo.visibility = View.GONE
            holder.noteLinearTwo.visibility = View.GONE
            holder.imageProductTwo.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = initItemCount()

    private fun initItemCount(): Int {
        if (list.size % 2 == 0)
            return (list.size / 2)
        return (floor((list.size / 2).toDouble()) + 1).toInt()
    }

    private fun startDetailsFragment(id: Int) {
        fragmentManager.beginTransaction()
            .add(R.id.mainContainer, ProductDetailsFragment().newInstance(id))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var divider = itemView.findViewById<View>(R.id.divider)
        var imageProductOne = itemView.findViewById<ImageView>(R.id.imageProductOne)
        var imageProductTwo = itemView.findViewById<ImageView>(R.id.imageProductTwo)
        var titleProductOne = itemView.findViewById<CTextView>(R.id.titleProductOne)
        var titleProductTwo = itemView.findViewById<CTextView>(R.id.titleProductTwo)
        var priceProductOne = itemView.findViewById<TextView>(R.id.priceProductOne)
        var priceProductTwo = itemView.findViewById<TextView>(R.id.priceProductTwo)
        var priceLinearTwo = itemView.findViewById<LinearLayout>(R.id.priceLinearTwo)
        var noteLinearTwo = itemView.findViewById<LinearLayout>(R.id.noteLinearTwo)
        var productContainerOne = itemView.findViewById<LinearLayout>(R.id.productContainerOne)
        var productContainerTwo = itemView.findViewById<LinearLayout>(R.id.productContainerTwo)
    }
}