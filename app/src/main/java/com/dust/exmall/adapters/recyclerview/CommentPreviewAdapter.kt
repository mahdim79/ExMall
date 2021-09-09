package com.dust.exmall.adapters.recyclerview

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.CommentDataClass

class CommentPreviewAdapter(var dataList: List<CommentDataClass> , var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val SHOW_ALL_VIEW_TYPE = 0
    private val NORMAL_VIEW_TYPE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SHOW_ALL_VIEW_TYPE)
            return ShowAllViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_show_all_plus_products, parent, false)
            )
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_normal_preview_comment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainViewHolder) {
            holder.comment_title.text = dataList[position].title
            holder.comment_text.text = dataList[position].commentText
            holder.commentDate.text = dataList[position].date
            holder.comment_user.text = dataList[position].userName
            setUpRating(holder , dataList[position].ratingLevel)
        } else {
            val mHolder = holder as ShowAllViewHolder
            mHolder.itemView.setOnClickListener {

            }
        }

    }

    private fun setUpRating(holder: MainViewHolder, ratingLevel: Int) {
        var resId = 0
        var ratingText = ""
        var textColor = 0
        when(ratingLevel){
            0 -> {
                resId = R.drawable.ic_outline_thumb_down_alt_24_red
                ratingText = "خرید این محصول را پیشنهاد نمی کنم"
                textColor = Color.RED
            }
            1 -> {
                resId = R.drawable.ic_outline_thumb_down_alt_24
                ratingText = "در مورد خرید این محصول نظری ندارم"
                textColor = ContextCompat.getColor(context , R.color.light_orange)
            }
            else -> {
                resId = R.drawable.ic_outline_thumb_up_24
                ratingText = "خرید این محصول را پیشنهاد می کنم"
                textColor = Color.GREEN
            }
        }
        holder.ratingIcon.setImageResource(resId)
        holder.ratingText.text = ratingText
        holder.ratingText.setTextColor(textColor)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == dataList.size)
            return SHOW_ALL_VIEW_TYPE
        return NORMAL_VIEW_TYPE
    }

    override fun getItemCount(): Int = dataList.size + 1

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var comment_title = itemView.findViewById<CTextView>(R.id.comment_title)
        var ratingText = itemView.findViewById<CTextView>(R.id.ratingText)
        var comment_text = itemView.findViewById<CTextView>(R.id.comment_text)
        var commentDate = itemView.findViewById<CTextView>(R.id.commentDate)
        var comment_user = itemView.findViewById<CTextView>(R.id.comment_user)
        var ratingIcon = itemView.findViewById<ImageView>(R.id.ratingIcon)
    }

    inner class ShowAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}