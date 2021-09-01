package com.dust.exmall.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import com.dust.exmall.MyApplication

class CButton: androidx.appcompat.widget.AppCompatButton {

    constructor(context: Context) : super(context) {
        setTypeFace()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setTypeFace()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        setTypeFace()
    }

    private fun setTypeFace() {
        val myApplication = context.applicationContext as MyApplication
        typeface = myApplication.getTypeFace()
    }
}