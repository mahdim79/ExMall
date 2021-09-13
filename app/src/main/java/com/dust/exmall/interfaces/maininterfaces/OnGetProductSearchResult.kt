package com.dust.exmall.interfaces.maininterfaces

import com.dust.exmall.dataclasses.ProductsDataClass

interface OnGetProductSearchResult {
    fun onGet(list:List<ProductsDataClass>)
    fun onNothingFound()
}