package com.dust.exmall.interfaces.maininterfaces

import com.dust.exmall.dataclasses.ProductsDataClass

interface OnGetProducts {
    fun onGetProducts(data:List<ProductsDataClass>)
    fun onFailureGetProducts(message:String)
}