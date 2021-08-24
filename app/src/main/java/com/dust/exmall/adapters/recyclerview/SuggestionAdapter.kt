package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import de.hdodenhof.circleimageview.CircleImageView

class SuggestionAdapter(): RecyclerView.Adapter<SuggestionAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_suggestion , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        when(position){
            0 -> setData(holder , "دعوت صد میلیونی" , R.drawable.bg_one)
            1 -> setData(holder , "مگنت" , R.drawable.bg_one)
            2 -> setData(holder , "خرید اقساطی" , R.drawable.bg_one)
            3 -> setData(holder , "کالای دیجیتال" , R.drawable.bg_one)
            4 -> setData(holder , "خرید سر ماه" , R.drawable.bg_one)
            5 -> setData(holder , "حراج استایل" , R.drawable.bg_one)
            6 -> setData(holder , "خانه و آشپزخانه" , R.drawable.bg_one)
            7 -> setData(holder , "دیجی پلاس" , R.drawable.bg_one)
            else -> setData(holder , "برای خرید اولی ها" , R.drawable.bg_one)
        }
    }

    override fun getItemCount(): Int = 9

    private fun setData(holder: MainViewHolder , text:String , imageResId:Int){
        holder.item_image.setImageResource(imageResId)
        holder.item_text.text = text
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var item_image = itemView.findViewById<CircleImageView>(R.id.item_image)
        var item_text = itemView.findViewById<CTextView>(R.id.item_text)
    }

}