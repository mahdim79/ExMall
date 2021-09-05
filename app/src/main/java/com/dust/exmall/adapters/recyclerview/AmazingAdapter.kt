package com.dust.exmall.adapters.recyclerview

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.squareup.picasso.Picasso

class AmazingAdapter(var list: List<ProductsDataClass>, var type: Int, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var AmazingOffersType: Int = 0
    private var AmazingSuperMarketType: Int = 1

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val NORMAL_ITEM = 0
    private val HEADER_ITEM: Int = 1
    private val SHOW_ALL_ITEM = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NORMAL_ITEM -> MainViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_amazing, parent, false)
            )
            HEADER_ITEM -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_amazing_header, parent, false)
            )
            else /*show all item*/ -> ShowAllViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_amazing_show_all, parent, false)
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainViewHolder) {
            val mHolder = holder as MainViewHolder
            setProductImage(mHolder , position)
            setProductTitle(mHolder , position)
            setProductPrice(mHolder , position)
            setOldPrice(mHolder, "255,000")
            setSoldAmount(mHolder, "69%")
            setUpAddCircle(mHolder)
            setUpPurchaseAdder(mHolder)
        } else if (holder is HeaderViewHolder) {
            val mHolder = holder as HeaderViewHolder
            setUpHeaderItem(mHolder)
        } else {
            val mHolder = holder as ShowAllViewHolder
            setUpShowAllItem(mHolder)
        }
    }

    private fun setProductPrice(holder: MainViewHolder, position: Int) {
        holder.price.text = list[position - 1].price
    }

    private fun setProductTitle(holder: MainViewHolder, position: Int) {
        holder.title.text = list[position - 1].title
    }

    private fun setProductImage(holder: MainViewHolder , position: Int) {
        Picasso.get().load(list[position-1].image).into(holder.productImage)
    }

    private fun setUpPurchaseAdder(mHolder: MainViewHolder) {
        mHolder.imageAdd.setOnClickListener {
            resetRunnable(mHolder)
        }
        mHolder.imageDelete.setOnClickListener {
            resetRunnable(mHolder)
        }
    }

    private fun resetRunnable(mHolder: MainViewHolder) {
            handler.removeCallbacks(runnable)
            setUpHandler(mHolder)
    }

    private fun setUpAddCircle(mHolder: MainViewHolder) {
        mHolder.addCircle.setOnClickListener {
            mHolder.purchaseController.visibility = View.VISIBLE
            setUpHandler(mHolder)
        }
    }

    private fun setUpHandler(mHolder: MainViewHolder) {

        handler = Handler()
        runnable = Runnable {
            mHolder.purchaseController.visibility = View.GONE
        }
        handler.postDelayed(runnable, 3000)
    }

    private fun setSoldAmount(mHolder: AmazingAdapter.MainViewHolder, soldAmount: String) {
        val text = context.resources.getString(R.string.soldAmount, soldAmount)
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            3,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
        mHolder.soldPercentageText.text = spannableString
        
        // setUpProgressBar
        val percentage = soldAmount.replace("%" , "").toInt()
        mHolder.soldProgressBar.progress = percentage
    }

    private fun setUpHeaderItem(mHolder: HeaderViewHolder) {
        when (type) {
            AmazingOffersType -> {
                mHolder.headerText.text = "پیشنهاد شگفت انگیز"
                mHolder.headerImage.setImageResource(R.drawable.gift_box_pic)
            }
            else -> {
                mHolder.headerText.text = "شگفت انگیز زنانه"
                mHolder.headerImage.setImageResource(R.drawable.jeweley_pic)
            }
        }
    }

    private fun setUpShowAllItem(mHolder: ShowAllViewHolder) {
        when (type) {
            AmazingOffersType -> {
            }
            else -> {
            }
        }
    }

    override fun getItemCount(): Int = list.size + 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                HEADER_ITEM
            }
            (list.size + 1) -> {
                SHOW_ALL_ITEM
            }
            else -> {
                NORMAL_ITEM
            }
        }
    }

    private fun setOldPrice(holder: MainViewHolder, oldPrice: String) {
        val spannable = SpannableString(oldPrice)
        spannable.setSpan(
            StrikethroughSpan(),
            0,
            oldPrice.length,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
        holder.txtOldPrice.text = spannable
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtOldPrice = itemView.findViewById<TextView>(R.id.txtOldPrice)
        var soldPercentageText = itemView.findViewById<CTextView>(R.id.soldPercentageText)
        var addCircle = itemView.findViewById<RelativeLayout>(R.id.addCircle)
        var purchaseController = itemView.findViewById<LinearLayout>(R.id.purchaseController)
        var imageDelete = itemView.findViewById<ImageView>(R.id.imageDelete)
        var imageAdd = itemView.findViewById<ImageView>(R.id.imageAdd)
        var soldProgressBar = itemView.findViewById<ProgressBar>(R.id.soldProgressBar)
        var productImage = itemView.findViewById<ImageView>(R.id.productImage)
        var title = itemView.findViewById<CTextView>(R.id.title)
        var price = itemView.findViewById<TextView>(R.id.price)
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var headerText = itemView.findViewById<TextView>(R.id.headerText)
        var headerImage = itemView.findViewById<ImageView>(R.id.headerImage)
    }

    inner class ShowAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}