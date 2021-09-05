package com.dust.exmall.fragments.cartfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.fragments.bottomsheets.CartProductMoreBottomSheet

class NextCartFragment(): Fragment() {
    private lateinit var superMarketRecyclerView:RecyclerView
    private lateinit var otherRecyclerView:RecyclerView
    private lateinit var loginAdviseContainer:RelativeLayout

    private lateinit var more_supermarket: ImageView
    private lateinit var more_others: ImageView

    private val animations = Animations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_cart , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpRecyclerViews()
        setUpAnimations()
        setUpMoreIcons()
    }

    private fun setUpMoreIcons() {
        more_supermarket.setOnClickListener {
            startMoreBottomSheetFragment()
        }
        more_others.setOnClickListener {
            startMoreBottomSheetFragment()
        }
    }

    private fun startMoreBottomSheetFragment() {
        val dialog = CartProductMoreBottomSheet()
        dialog.show(requireActivity().supportFragmentManager , dialog.tag)
    }

    private fun setUpAnimations() {
        loginAdviseContainer.setOnTouchListener { v, motionEvent ->
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
        more_supermarket.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener false
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                v.startAnimation(animations.getFadeInAnimation())
            } else {
                v.startAnimation(animations.getFadeOutAnimation())
            }
            false
        }
        more_others.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener false
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                v.startAnimation(animations.getFadeInAnimation())
            } else {
                v.startAnimation(animations.getFadeOutAnimation())
            }
            false
        }
    }

    private fun setUpRecyclerViews() {
        superMarketRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        otherRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)

      //  superMarketRecyclerView.adapter = CartProductsAdapter(generateFakeData())
     //   otherRecyclerView.adapter = CartProductsAdapter(generateFakeData())
    }

    private fun setUpViews(view: View) {
        superMarketRecyclerView = view.findViewById(R.id.superMarketRecyclerView)
        otherRecyclerView = view.findViewById(R.id.otherRecyclerView)
        loginAdviseContainer = view.findViewById(R.id.loginAdviseContainer)
        more_others = view.findViewById(R.id.more_others)
        more_supermarket = view.findViewById(R.id.more_supermarket)
    }

}