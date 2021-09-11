package com.dust.exmall.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.FeatureDataClass

class TechnicalFeatureAdapter(var list:List<FeatureDataClass> , var context: Context) : RecyclerView.Adapter<TechnicalFeatureAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_technical_feature , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = list[position].title
        for (k in 0 until list[position].dataList.size){
            val layout = LayoutInflater.from(context).inflate(R.layout.item_single_feature , null , false)
            if (k == 0){
                layout.findViewById<View>(R.id.divider).visibility = View.GONE
            }
            //set title
            val title = layout.findViewById<CTextView>(R.id.title)
            title.text = list[position].dataList[k].title
            //set child's
            val oneFeatureContainer = layout.findViewById<LinearLayout>(R.id.oneFeatureContainer)
            list[position].dataList[k].featureList.forEach { featureItem ->
                val nestedLayout = LayoutInflater.from(context).inflate(R.layout.item_one_feature , null , false)
                val feature = nestedLayout.findViewById<CTextView>(R.id.feature)
                feature.text = featureItem
                if (list[position].dataList[k].featureList.size == 1){
                    nestedLayout.findViewById<ImageView>(R.id.dotImage).visibility = View.GONE
                }
                oneFeatureContainer.addView(nestedLayout)
            }
            holder.mainFeatureContainer.addView(layout)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mainFeatureContainer = itemView.findViewById<LinearLayout>(R.id.mainFeatureContainer)
        var title = itemView.findViewById<CTextView>(R.id.title)
    }
}