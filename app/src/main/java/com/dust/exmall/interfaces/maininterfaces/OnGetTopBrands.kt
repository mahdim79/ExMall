package com.dust.exmall.interfaces.maininterfaces

import com.dust.exmall.dataclasses.TopBrandDataClass

interface OnGetTopBrands {
    fun onGetTopBrands(data:List<TopBrandDataClass>)
    fun onFailureGetTopBrands(message:String)
}