package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.FullReviewDataClass
import com.squareup.picasso.Picasso

class FullReviewAdapter(var list: List<FullReviewDataClass>) :
    RecyclerView.Adapter<FullReviewAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_full_review, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.textDescription.text = list[position].text

        // set Images
        if (list[position].topImageOne != "") {
            holder.imageContainerOne.visibility = View.VISIBLE
            Picasso.get().load(list[position].topImageOne).into(holder.headerImageOne)
        }

        if (list[position].topImageTwo != "") {
            holder.imageContainerTwo.visibility = View.VISIBLE
            Picasso.get().load(list[position].topImageTwo).into(holder.headerImageTwo)
        }


        if (list[position].bottomImageOne != "") {
            holder.imageContainerThree.visibility = View.VISIBLE
            Picasso.get().load(list[position].bottomImageOne).into(holder.bottomImageOne)
        }


        if (list[position].bottomImageTwo != "") {
            holder.imageContainerFour.visibility = View.VISIBLE
            Picasso.get().load(list[position].bottomImageTwo).into(holder.bottomImageTwo)
        }


        // setUp more Details
        if (list[position].moreDetailsText != "") {
            holder.moreDescriptionContainer.visibility = View.VISIBLE
            holder.moreDescription.text = list[position].moreDetailsText
        }
    }

    override fun getItemCount(): Int = list.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<CTextView>(R.id.title)
        var textDescription = itemView.findViewById<CTextView>(R.id.textDescription)
        var moreDescription = itemView.findViewById<CTextView>(R.id.moreDescription)
        var headerImageOne = itemView.findViewById<ImageView>(R.id.headerImageOne)
        var headerImageTwo = itemView.findViewById<ImageView>(R.id.headerImageTwo)
        var bottomImageOne = itemView.findViewById<ImageView>(R.id.bottomImageOne)
        var bottomImageTwo = itemView.findViewById<ImageView>(R.id.bottomImageTwo)
        var moreDescriptionContainer =
            itemView.findViewById<CardView>(R.id.moreDescriptionContainer)
        var imageContainerOne = itemView.findViewById<CardView>(R.id.imageContainerOne)
        var imageContainerTwo = itemView.findViewById<CardView>(R.id.imageContainerTwo)
        var imageContainerThree = itemView.findViewById<CardView>(R.id.imageContainerThree)
        var imageContainerFour = itemView.findViewById<CardView>(R.id.imageContainerFour)
    }

}