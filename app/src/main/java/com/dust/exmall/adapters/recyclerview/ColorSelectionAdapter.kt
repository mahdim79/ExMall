package com.dust.exmall.adapters.recyclerview

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ColorDataClass
import com.dust.exmall.interfaces.local.OnColorChanged

class ColorSelectionAdapter(var onColorChanged: OnColorChanged , var list: List<ColorDataClass>) : RecyclerView.Adapter<ColorSelectionAdapter.MainViewHolder>() {

    private val handler = Handler()
    private lateinit var runnable: Runnable

    private var selectedColorId = 0

    private val animations = Animations()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_selection_color , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        setUpAnimations(holder)
        holder.containerCardView.setOnClickListener {
            if (selectedColorId != list[position].id){
                try {
                    handler.removeCallbacks(runnable)
                }catch (e:Exception){}
                runnable = Runnable {
                    onColorChanged.onColorChanged(list[position].id)
                }
                handler.postDelayed(runnable , 500)
            }
        }

        holder.colorName.text = list[position].name

        holder.colorFrameLayout.setBackgroundColor(Color.parseColor(list[position].hexColor))

        if (list[position].selected){
            holder.selectionIndicatorImage.visibility = View.VISIBLE
            selectedColorId = list[position].id
        } else{
            holder.selectionIndicatorImage.visibility = View.GONE
        }
    }

    private fun setUpAnimations(holder: MainViewHolder) {
        holder.containerCardView.setOnTouchListener { v, motionEvent ->
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

    override fun getItemCount(): Int = list.size

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var containerCardView = itemView.findViewById<CardView>(R.id.containerCardView)
        var selectionIndicatorImage = itemView.findViewById<ImageView>(R.id.selectionIndicatorImage)
        var colorFrameLayout = itemView.findViewById<FrameLayout>(R.id.colorFrameLayout)
        var colorName = itemView.findViewById<CTextView>(R.id.colorName)
    }

}