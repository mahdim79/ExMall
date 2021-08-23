package com.dust.exmall.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class CTextView : androidx.appcompat.widget.AppCompatTextView {

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
        typeface = Typeface.createFromAsset(resources.assets, "fonts/yekan.ttf")
    }
}