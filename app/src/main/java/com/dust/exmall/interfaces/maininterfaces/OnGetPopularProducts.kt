package com.dust.exmall.interfaces.maininterfaces

import com.dust.exmall.dataclasses.ProductsDataClass

interface OnGetPopularProducts {
    fun onGetPopularProducts(data:List<ProductsDataClass> , tag:Int)
    fun onFailureGetPopularProducts(message:String)
}