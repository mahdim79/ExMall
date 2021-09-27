package com.dust.exmall.animation

import android.view.animation.AlphaAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation

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

    fun getScaleAnimation(): ScaleAnimation {
        val scaleAnimation = ScaleAnimation(
            0f,
            1f,
            0f,
            1f,
            ScaleAnimation.RELATIVE_TO_SELF,
            0.5f,
            ScaleAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation.duration = 500
        scaleAnimation.fillAfter = true
        return scaleAnimation
    }

    fun getUpTranslateAnimation(): TranslateAnimation {
        val translateAnimation = TranslateAnimation(0.0f, 0.0f, 0f, -1000f)
        translateAnimation.duration = 1000
        return translateAnimation
    }

    fun getDownTranslateAnimation(): TranslateAnimation {
        val translateAnimation = TranslateAnimation(0.0f, 0.0f, 0f, 1500f)
        translateAnimation.duration = 1000
        return translateAnimation
    }

    fun getUpBackTranslateAnimation(): TranslateAnimation {
        val translateAnimation = TranslateAnimation(0.0f, 0.0f, -1000f, 0f)
        translateAnimation.duration = 1000
        return translateAnimation
    }

    fun getDownBackTranslateAnimation(): TranslateAnimation {
        val translateAnimation = TranslateAnimation(0.0f, 0.0f, 1500f, 0f)
        translateAnimation.duration = 1000
        return translateAnimation
    }

    fun getFadeInFullTransparentAlphaAnimation(): AlphaAnimation {
        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.fillAfter = true
        fadeInAnimation.duration = 400
        return fadeInAnimation
    }

    fun getFadeOutFullTransparentAlphaAnimation(): AlphaAnimation {
        val fadeInAnimation = AlphaAnimation(1f, 0f)
        fadeInAnimation.fillAfter = true
        fadeInAnimation.duration = 400
        return fadeInAnimation
    }
}