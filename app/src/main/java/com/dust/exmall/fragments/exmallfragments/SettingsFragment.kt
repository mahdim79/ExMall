package com.dust.exmall.fragments.exmallfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.animation.Animations

class SettingsFragment(): Fragment(),View.OnTouchListener,View.OnClickListener {
    private val animations = Animations()
    private lateinit var faqRelativeLayout:RecyclerView
    private lateinit var privacyRelativeLayout:RecyclerView
    private lateinit var termsOfUsageRelativeLayout:RecyclerView
    private lateinit var contactUsRelativeLayout:RecyclerView
    private lateinit var reportBugRelativeLayout:RecyclerView
    private lateinit var rateRelativeLayout:RecyclerView
    private lateinit var logOutRelativeLayout:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_layout , container ,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpAnimations()
    }

    private fun setUpAnimations() {

    }

    private fun setUpViews(view: View) {
        faqRelativeLayout = view.findViewById(R.id.faqRelativeLayout)
        privacyRelativeLayout = view.findViewById(R.id.privacyRelativeLayout)
        termsOfUsageRelativeLayout = view.findViewById(R.id.termsOfUsageRelativeLayout)
        contactUsRelativeLayout = view.findViewById(R.id.contactUsRelativeLayout)
        reportBugRelativeLayout = view.findViewById(R.id.reportBugRelativeLayout)
        rateRelativeLayout = view.findViewById(R.id.rateRelativeLayout)
        logOutRelativeLayout = view.findViewById(R.id.logOutRelativeLayout)

        privacyRelativeLayout.setOnTouchListener(this)
        termsOfUsageRelativeLayout.setOnTouchListener(this)
        contactUsRelativeLayout.setOnTouchListener(this)
        reportBugRelativeLayout.setOnTouchListener(this)
        rateRelativeLayout.setOnTouchListener(this)
        logOutRelativeLayout.setOnTouchListener(this)

        privacyRelativeLayout.setOnClickListener(this)
        termsOfUsageRelativeLayout.setOnClickListener(this)
        contactUsRelativeLayout.setOnClickListener(this)
        reportBugRelativeLayout.setOnClickListener(this)
        rateRelativeLayout.setOnClickListener(this)
        logOutRelativeLayout.setOnClickListener(this)
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        if (p1!!.action == MotionEvent.ACTION_CANCEL){
            p0!!.startAnimation(animations.getFadeInAnimation())
            return true
        }
        if (p1!!.action == MotionEvent.ACTION_UP){
            p0!!.startAnimation(animations.getFadeInAnimation())
        }else{
            p0!!.startAnimation(animations.getFadeOutAnimation())
        }
        return true
    }

    override fun onClick(p0: View?) {

    }
}