package com.dust.exmall.fragments.bottomsheets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.FragmentTransaction
import com.dust.exmall.R
import com.dust.exmall.animation.Animations
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.fragments.others.SearchCompareProductFragment
import com.dust.exmall.fragments.others.WebPageFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductDetailsBottomSheet(var productData: ProductsDataClass) : BottomSheetDialogFragment() {

    private val animations = Animations()
    private lateinit var compareRelative: RelativeLayout
    private lateinit var shareRelative: RelativeLayout
    private lateinit var incredibleAnnouncementRelative: RelativeLayout
    private lateinit var priceChartRelative: RelativeLayout
    private lateinit var incredibleAnnouncementSwitch: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpAnimations()
        setUpRelativeLayouts()
        setUpIncredibleAnnouncementSwitch()
    }

    private fun setUpIncredibleAnnouncementSwitch() {
        // TODO: 10/7/2021 set it up using shared preferences
    }

    private fun setUpRelativeLayouts() {

        // price chart

        priceChartRelative.setOnClickListener {
            dismiss()
            // get price chart url and replace with www.google.com
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , WebPageFragment().newInstance(productData.title , "www.google.com"))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("WebPageFragment")
                .commit()
        }

        // compare

        compareRelative.setOnClickListener {
            dismiss()
            requireActivity().supportFragmentManager.beginTransaction()
                .add(
                    R.id.mainContainer,
                    SearchCompareProductFragment().newInstance(productData.category, productData.id)
                )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("SearchCompareProductFragment")
                .commit()
        }

        // share

        shareRelative.setOnClickListener {
            dismiss()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, productData.title)
            intent.putExtra(Intent.EXTRA_TEXT, "خرید محصول ${productData.title} را پیشنهاد میکنم")
            requireActivity().startActivity(
                Intent.createChooser(
                    intent,
                    "به اشتراک گذاری بوسیله..."
                )
            )
        }

        // incredible Announcement

        incredibleAnnouncementRelative.setOnClickListener {
            incredibleAnnouncementSwitch.isChecked = !incredibleAnnouncementSwitch.isChecked
            // TODO: 10/7/2021 set it up using shared preferences
            dismiss()
        }

        // Switch Compat
        incredibleAnnouncementSwitch.setOnCheckedChangeListener { _, b ->
            dismiss()
            // TODO: 10/7/2021 set it up using shared preferences
        }

    }

    private fun setUpAnimations() {
        compareRelative.setOnTouchListener { v, motionEvent ->
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

        shareRelative.setOnTouchListener { v, motionEvent ->
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

        incredibleAnnouncementRelative.setOnTouchListener { v, motionEvent ->
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

        priceChartRelative.setOnTouchListener { v, motionEvent ->
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

    private fun setUpViews(view: View) {
        compareRelative = view.findViewById(R.id.compareRelative)
        shareRelative = view.findViewById(R.id.shareRelative)
        incredibleAnnouncementRelative = view.findViewById(R.id.incredibleAnnouncementRelative)
        incredibleAnnouncementSwitch = view.findViewById(R.id.incredibleAnnouncementSwitch)
        priceChartRelative = view.findViewById(R.id.priceChartRelative)
    }
}