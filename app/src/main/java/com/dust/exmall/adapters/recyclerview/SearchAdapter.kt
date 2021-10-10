package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.fragments.others.CategoryProductsFragment

class SearchAdapter(var mainViewType: Int , var categoryList:List<String> , var fragmentManager: FragmentManager): RecyclerView.Adapter<SearchAdapter.MainViewHolder>() {
    private val CATEGORY_VIEW_TYPE = 0
    private val RELATIVE_VIEW_TYPE = 1

    private val animations = Animations()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.tagTitle.text = categoryList[position]
        if (mainViewType == CATEGORY_VIEW_TYPE)
            holder.relativeImage.visibility = View.GONE

        holder.itemView.setOnClickListener {
            fragmentManager.beginTransaction()
                .add(R.id.mainContainer , CategoryProductsFragment().newInstance(categoryList[position]))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("CategoryProductsFragment")
                .commit()
        }

        setUpAnimations(holder)
    }

    private fun setUpAnimations(holder: MainViewHolder) {
        holder.itemView.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener false
            }
            if (motionEvent.action == MotionEvent.ACTION_UP)
                v.startAnimation(animations.getFadeInAnimation())
            else
                v.startAnimation(animations.getFadeOutAnimation())
            false
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tagTitle = itemView.findViewById<CTextView>(R.id.tagTitle)
        val relativeImage = itemView.findViewById<ImageView>(R.id.relativeImage)
    }

}