package com.dust.exmall.interfaces.maininterfaces

interface OnGetCategories {
    fun onGetCategories(categoryList:List<String>)
    fun onFailureGetCategories(message:String)
}