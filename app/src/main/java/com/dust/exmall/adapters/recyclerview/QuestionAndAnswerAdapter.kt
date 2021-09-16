package com.dust.exmall.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.AnswerAndQuestionDataClass
import com.dust.exmall.fragments.others.WriteReplyFragment

class QuestionAndAnswerAdapter(
    var dataList: List<AnswerAndQuestionDataClass>,
    var context: Context,
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<QuestionAndAnswerAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var replyContainer = itemView.findViewById<LinearLayout>(R.id.replyContainer)
        var questionText = itemView.findViewById<CTextView>(R.id.questionText)
        var addNewReply = itemView.findViewById<CTextView>(R.id.addNewReply)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_question_and_answer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.questionText.text = dataList[position].text

        // setUpReply
        if (dataList[position].reply.id != -1) {
            val replyLayout =
                LayoutInflater.from(context)
                    .inflate(R.layout.item_answer_question_reply, null, false)
            replyLayout.findViewById<CTextView>(R.id.replyText).text = dataList[position].reply.text
            replyLayout.findViewById<CTextView>(R.id.submitterText).text =
                dataList[position].reply.submitter
            replyLayout.findViewById<CTextView>(R.id.submitterType).text =
                dataList[position].reply.submitterType

            // set Likes and dislikes
            if (dataList[position].reply.likes != 0)
                replyLayout.findViewById<CTextView>(R.id.likeCount).text =
                    dataList[position].reply.likes.toString()
            if (dataList[position].reply.disLikes != 0)
                replyLayout.findViewById<CTextView>(R.id.disLikeCount).text =
                    dataList[position].reply.disLikes.toString()

            holder.replyContainer.addView(replyLayout)
        }

        // set addNewReply
        holder.addNewReply.setOnClickListener {
            fragmentManager.beginTransaction()
                .add(R.id.mainContainer , WriteReplyFragment().newInstance(dataList[position].id , dataList[position].text))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("WriteReplyFragment")
                .commit()
        }
    }

    override fun getItemCount(): Int = dataList.size
}