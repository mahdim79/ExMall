package com.dust.exmall.animation

import android.view.animation.AlphaAnimation
import android.view.animation.ScaleAnimation

class Animations() {
    fun getFadeOutAnimation(): AlphaAnimation {
        val fadeOutAnimation = AlphaAnimation(1f, 0.4f)
        fadeOutAnimation.fillAfter = true
        fadeOutAnimation.duration = 500
        return fadeOutAnimation
    }

    fun getFadeInAnimation(): AlphaAnimation {
        val fadeInAnimation = AlphaAnimation(0.4f, 1f)
        fadeInAnimation.fillAfter = true
        fadeInAnimation.duration = 500
        return fadeInAnimation
    }

    fun getScaleAnimation():ScaleAnimation{
        val scaleAnimation = ScaleAnimation(0f , 1f , 0f ,1f ,ScaleAnimation.RELATIVE_TO_SELF , 0.5f , ScaleAnimation.RELATIVE_TO_SELF , 0.5f)
        scaleAnimation.duration = 500
        scaleAnimation.fillAfter = true
        return scaleAnimation
    }
}