package com.dust.exmall.search

import com.dust.exmall.async.AsyncCenter
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.interfaces.maininterfaces.OnGetProductSearchResult

class SearchCenter() {
    private lateinit var asyncCenter:AsyncCenter
    init {
        asyncCenter = AsyncCenter()
    }
    fun searchProduct(onGetProductSearchResult: OnGetProductSearchResult , data:List<ProductsDataClass>,  word:String , type:Int){
        asyncCenter.startProductSearchByName(onGetProductSearchResult , word , data)
    }
}