package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations

class UserOrdersAdapter() : RecyclerView.Adapter<UserOrdersAdapter.MainViewHolder>() {
    private var animations = Animations()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_orders , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        setAnimation(holder)
    }

    private fun setAnimation(holder: MainViewHolder) {
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
    }

    override fun getItemCount(): Int = 5

    inner class MainViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }
}