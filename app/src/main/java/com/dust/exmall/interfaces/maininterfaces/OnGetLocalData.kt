package com.dust.exmall.interfaces.maininterfaces

import com.dust.exmall.dataclasses.LocalProductsDataClass

interface OnGetLocalData {
    fun onGetData(data:LocalProductsDataClass)
    fun onFailureGetData(message:String)
}