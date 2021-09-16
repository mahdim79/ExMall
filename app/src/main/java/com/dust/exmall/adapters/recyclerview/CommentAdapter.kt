package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.CommentDataClass

class CommentAdapter(var list:List<CommentDataClass>) : RecyclerView.Adapter<CommentAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<CTextView>(R.id.title)
        var commentText = itemView.findViewById<CTextView>(R.id.commentText)
        var commentDate = itemView.findViewById<CTextView>(R.id.commentDate)
        var comment_user = itemView.findViewById<CTextView>(R.id.comment_user)
        var rate = itemView.findViewById<CTextView>(R.id.rate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_whole_comment , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.commentText.text = list[position].commentText
        holder.commentDate.text = list[position].date
        holder.comment_user.text = list[position].userName
    }

    override fun getItemCount(): Int = list.size
}