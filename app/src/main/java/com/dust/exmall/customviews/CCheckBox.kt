package com.dust.exmall.customviews

import android.content.Context
import android.util.AttributeSet
import com.dust.exmall.MyApplication

class CCheckBox : androidx.appcompat.widget.AppCompatCheckBox {
    constructor(context: Context) : super(context) {
        setCheckTypeFace()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setCheckTypeFace()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setCheckTypeFace()
    }

    private fun setCheckTypeFace(){
        typeface = (context.applicationContext as MyApplication).getTypeFace()
    }
}