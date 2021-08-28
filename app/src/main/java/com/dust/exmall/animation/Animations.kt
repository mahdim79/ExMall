package com.dust.exmall.animation

import android.view.animation.AlphaAnimation

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
}