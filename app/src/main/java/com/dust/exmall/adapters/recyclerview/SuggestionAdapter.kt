package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import de.hdodenhof.circleimageview.CircleImageView

class SuggestionAdapter(var animations:Animations): RecyclerView.Adapter<SuggestionAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_suggestion , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        // set OnClick Opacity
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
        when(position){
            0 -> setData(holder , "دعوت صد میلیونی" , R.drawable.school)
            1 -> setData(holder , "مگنت" , R.drawable.magnet)
            2 -> setData(holder , "سوپر مارکت" , R.drawable.supermarket)
            3 -> setData(holder , "کالای دیجیتال" , R.drawable.digital)
            4 -> setData(holder , "مد و پوشاک" , R.drawable.dress)
            5 -> setData(holder , "خانه و آشپزخانه" , R.drawable.home_stuff)
            6 -> setData(holder , "دیجی پلاس" , R.drawable.plus)
            else -> setData(holder , "برای خرید اولی ها" , R.drawable.pay)
        }
    }

    override fun getItemCount(): Int = 8

    private fun setData(holder: MainViewHolder , text:String , imageResId:Int){
        holder.item_image.setImageResource(imageResId)
        holder.item_text.text = text
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var item_image = itemView.findViewById<CircleImageView>(R.id.item_image)
        var item_text = itemView.findViewById<CTextView>(R.id.item_text)
    }

}