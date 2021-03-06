package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.dataclasses.TopBrandDataClass
import com.squareup.picasso.Picasso

class TopBrandAdapter(var list:List<TopBrandDataClass> , var animations: Animations):RecyclerView.Adapter<TopBrandAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_brand , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL){
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP)
                v.startAnimation(animations.getFadeInAnimation())
            else
                v.startAnimation(animations.getFadeOutAnimation())
            true
        }
        Picasso.get().load(list[position].image).into(holder.topBrandImage)
        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int = list.size

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var topBrandImage = itemView.findViewById<ImageView>(R.id.topBrandImage)
    }
}