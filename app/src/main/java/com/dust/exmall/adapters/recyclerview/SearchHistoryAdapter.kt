package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.interfaces.local.OnHistorySearchSelected

class SearchHistoryAdapter(
    var dataList: List<String>,
    var onHistorySearchSelected: OnHistorySearchSelected
) : RecyclerView.Adapter<SearchHistoryAdapter.MainViewHolder>() {

    val animations = Animations()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search_history, parent, false
            )
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.wordText.text = dataList[position]
        holder.itemView.setOnClickListener {
            onHistorySearchSelected.onSelect(dataList[position])
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

    override fun getItemCount(): Int = dataList.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordText = itemView.findViewById<CTextView>(R.id.wordText)
    }
}