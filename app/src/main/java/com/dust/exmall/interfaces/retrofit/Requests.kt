package com.dust.exmall.interfaces.retrofit

import com.dust.exmall.dataclasses.ProductsDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Requests {
    @GET("products?limit=4")
    fun getAmazingProducts():Call<List<ProductsDataClass>>

    @GET
    fun getProductByCategory(@Url url:String): Call<List<ProductsDataClass>>

    @GET("products/category/jewelery")
    fun getSliderContent(): Call<List<ProductsDataClass>>

    @GET("products/category/men's%20clothing")
    fun getMagicContent(): Call<List<ProductsDataClass>>
}