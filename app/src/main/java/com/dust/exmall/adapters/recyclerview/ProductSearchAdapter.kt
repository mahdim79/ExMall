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
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.others.ProductDetailsFragment
import com.squareup.picasso.Picasso

class ProductSearchAdapter(var dataList:List<ProductsDataClass> , var fragmentManager: FragmentManager) :RecyclerView.Adapter<ProductSearchAdapter.MainViewHolder>() {

    private val animations = Animations()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_search , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textTitle.text = dataList[position].title
        Picasso.get().load(dataList[position].image).into(holder.productImage)
        holder.itemView.setOnClickListener {
            fragmentManager.beginTransaction()
                .add(R.id.mainContainer , ProductDetailsFragment().newInstance(dataList[position].id))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
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
        return dataList.size
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textTitle = itemView.findViewById<CTextView>(R.id.textTitle)
        val productImage = itemView.findViewById<ImageView>(R.id.productImage)
    }
}