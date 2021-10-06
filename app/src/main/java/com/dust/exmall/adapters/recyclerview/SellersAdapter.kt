package com.dust.exmall.adapters.recyclerview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CButton
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.SellerDataClass
import com.dust.exmall.fragments.others.FullSellerInfoFragment

class SellersAdapter(var listData: List<SellerDataClass>, var fragmentManager: FragmentManager , var context: Context) :
    RecyclerView.Adapter<SellersAdapter.MainViewHolder>() {

    private val animations = Animations()
    private var handler = Handler()
    private lateinit var runnable: Runnable

    private var productCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sellers, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.titleText.text = listData[position].title
        setUpShowCardText(holder)
        holder.addToCardButton.setOnClickListener {
            // add product to card
            holder.addToCardButton.visibility = View.GONE
            holder.purchaseController.visibility = View.VISIBLE
            startRemovalTimer(holder)
            holder.count.text = "1"
            productCount = 1
        }

        holder.productCountCircle.setOnClickListener {
            holder.showCardLinear.visibility = View.GONE
            holder.purchaseController.visibility = View.VISIBLE
            startRemovalTimer(holder)
        }

        holder.showInCardLinear.setOnClickListener {
            for(i in 0 until fragmentManager.backStackEntryCount)
                fragmentManager.popBackStack()
            val intent = Intent("com.dust.exmall.ChangeViewPagerPosition")
            intent.putExtra("POSITION" , 2)
            context.sendBroadcast(intent)
        }

        holder.imageAdd.setOnClickListener {
            handler.removeCallbacks(runnable)
            // add more to card
            holder.count.text = (holder.count.text.toString().toInt() + 1).toString()
            productCount++

            startRemovalTimer(holder)
        }

        holder.imageDelete.setOnClickListener {
            handler.removeCallbacks(runnable)

            if (productCount == 1){
                holder.purchaseController.visibility = View.GONE
                holder.addToCardButton.visibility = View.VISIBLE
            }else{
                // start deleting from card
                holder.count.text = (holder.count.text.toString().toInt() - 1).toString()
                productCount--
                startRemovalTimer(holder)
            }
        }

        holder.sellerRelativeLayout.setOnClickListener {
            fragmentManager.beginTransaction()
                .add(
                    R.id.mainContainer,
                    FullSellerInfoFragment().newInstance(listData[position].id)
                )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("FullSellerInfoFragment")
                .commit()
        }

        setUpAnimations(holder)
    }

    private fun setUpAnimations(holder: MainViewHolder) {
        holder.sellerRelativeLayout.setOnTouchListener { v, motionEvent ->
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

    private fun startRemovalTimer(holder: MainViewHolder) {
        runnable = Runnable {
            holder.purchaseController.visibility = View.GONE
            holder.showCardLinear.visibility = View.VISIBLE
        }
        handler.postDelayed(runnable, 2000)
    }

    private fun setUpShowCardText(holder: MainViewHolder) {
        val spannableString = SpannableString("مشاهده سبد خرید")
        spannableString.setSpan(
            ForegroundColorSpan(Color.RED),
            7,
            spannableString.length,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
        holder.watchCardText.text = spannableString
    }

    override fun getItemCount(): Int = listData.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleText = itemView.findViewById<TextView>(R.id.titleText)
        var count = itemView.findViewById<CTextView>(R.id.count)
        var watchCardText = itemView.findViewById<TextView>(R.id.watchCardText)
        var addToCardButton = itemView.findViewById<CButton>(R.id.addToCardButton)
        var purchaseController = itemView.findViewById<LinearLayout>(R.id.purchaseController)
        var showCardLinear = itemView.findViewById<LinearLayout>(R.id.showCardLinear)
        var showInCardLinear = itemView.findViewById<LinearLayout>(R.id.showInCardLinear)
        var sellerRelativeLayout = itemView.findViewById<RelativeLayout>(R.id.sellerRelativeLayout)
        var productCountCircle = itemView.findViewById<FrameLayout>(R.id.productCountCircle)
        var imageDelete = itemView.findViewById<ImageView>(R.id.imageDelete)
        var imageAdd = itemView.findViewById<ImageView>(R.id.imageAdd)
    }
}