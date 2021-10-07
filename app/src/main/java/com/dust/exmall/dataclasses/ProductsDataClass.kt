package com.dust.exmall.dataclasses

data class ProductsDataClass(var id:Int, var title:String, var price:String, var category:String, var description:String, var image:String) {}
data class SellerDataClass(var id:Int , var title:String)
data class ColorDataClass(var id:Int , var name:String , var hexColor:String , var selected:Boolean)