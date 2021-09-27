package com.dust.exmall.adapters.recyclerview

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ImportantFeatureDataClass

class FeatureAdapter(var featureList: List<ImportantFeatureDataClass>, var context: Context) :
    RecyclerView.Adapter<FeatureAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_feature, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val spannableString =
            SpannableString("${featureList[position].title}: ${featureList[position].feature}")
        spannableString.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.black_normal
                )
            ), 0, featureList[position].title.length + 1, SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )

        holder.textFeature.text = spannableString
    }

    override fun getItemCount(): Int = featureList.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textFeature: CTextView = itemView.findViewById(R.id.textFeature)
    }

}