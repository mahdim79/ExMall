package com.dust.exmall.interfaces.maininterfaces

import com.dust.exmall.dataclasses.ProductsDataClass

interface OnGetMagicCartContent {
    fun onGetMagicCartContents(list: List<ProductsDataClass>)
    fun onFailureMagicCartContents(message:String)
}