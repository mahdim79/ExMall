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
import com.dust.exmall.fragments.others.CategoryProductsFragment
import com.squareup.picasso.Picasso

class OtherRelatedCategoriesAdapter(
    var dataList: List<String>,
    var currentCategory: String,
    var imageUrl: String = "",
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<OtherRelatedCategoriesAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_other_related_categories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var categoryName = ""
        if (imageUrl == "") {
            holder.categoryName.text = dataList[position]
            holder.categoryImage.visibility = View.GONE
            categoryName = dataList[position]
        } else {
            if (position == 0) {
                holder.categoryName.text = currentCategory
                Picasso.get().load(imageUrl).into(holder.categoryImage)
                categoryName = currentCategory
            } else {
                holder.categoryName.text = dataList[position - 1]
                holder.categoryImage.visibility = View.GONE
                categoryName = dataList[position - 1]
            }
        }

        holder.itemView.setOnClickListener {
            fragmentManager.beginTransaction()
                .add(R.id.mainContainer, CategoryProductsFragment().newInstance(categoryName))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("CategoryProductsFragment")
                .commit()
        }
    }

    override fun getItemCount(): Int {
        if (imageUrl == "")
            return dataList.size
        return dataList.size + 1
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName = itemView.findViewById<CTextView>(R.id.categoryName)
        var categoryImage = itemView.findViewById<ImageView>(R.id.categoryImage)
    }
}