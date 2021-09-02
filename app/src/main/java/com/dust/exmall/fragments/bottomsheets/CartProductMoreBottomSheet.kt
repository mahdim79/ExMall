package com.dust.exmall.fragments.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CartProductMoreBottomSheet:BottomSheetDialogFragment() {
    private lateinit var delete_product:LinearLayout
    private lateinit var delete_product_next:LinearLayout

    private val animations = Animations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.more_bottom_sheet , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpAnimations()
    }

    private fun setUpAnimations() {
        delete_product.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                v.startAnimation(animations.getFadeInAnimation())
            } else {
                v.startAnimation(animations.getFadeOutAnimation())
            }
            true
        }
        delete_product_next.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener true
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                v.startAnimation(animations.getFadeInAnimation())
            } else {
                v.startAnimation(animations.getFadeOutAnimation())
            }
            true
        }

    }

    private fun setUpViews(view: View) {
        delete_product = view.findViewById(R.id.delete_product)
        delete_product_next = view.findViewById(R.id.delete_product_next)
    }
}