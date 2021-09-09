package com.dust.exmall

import android.app.Application
import android.graphics.Typeface

class MyApplication : Application() {

    override fun onCreate() {
        getTypeFace()
        super.onCreate()
    }

    fun getTypeFace(): Typeface {
        return Typeface.createFromAsset(resources.assets, "fonts/far_mitra.ttf");
    }
}