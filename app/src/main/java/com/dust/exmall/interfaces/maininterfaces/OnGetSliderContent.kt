package com.dust.exmall.interfaces.maininterfaces

interface OnGetSliderContent {
    fun onGetSliderContent(list: List<Pair<String, String>>)
    fun onGetFailure(message: String)
}