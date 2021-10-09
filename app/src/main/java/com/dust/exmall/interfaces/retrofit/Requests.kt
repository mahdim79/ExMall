package com.dust.exmall.interfaces.retrofit

import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.dataclasses.TopBrandDataClass
import retrofit2.Call
import retrofit2.http.*

interface Requests {

    @GET("products?limit=4")
    fun getAmazingProducts():Call<List<ProductsDataClass>>

    @GET
    fun getProductsByCategory(@Url url:String): Call<List<ProductsDataClass>>

    @GET("products/category/jewelery")
    fun getSliderContent(): Call<List<ProductsDataClass>>

    @GET("products?limit=9")
    fun getMagicContent(): Call<List<ProductsDataClass>>

    @GET("products/categories")
    fun getPopularCategories():Call<List<String>>

    @GET("products?limit=6")
    fun getPlusProducts():Call<List<ProductsDataClass>>

    @GET("products?limit=15")
    fun getBestSellersProducts():Call<List<ProductsDataClass>>

    @GET("products?limit=14")
    fun getTopBrands():Call<List<TopBrandDataClass>>

    @GET("products?limit=15")
    fun getHighReviewedProducts():Call<List<ProductsDataClass>>

    @GET("products?limit=16")
    fun getForSaleProducts():Call<List<ProductsDataClass>>

    @GET("products?limit=8")
    fun getRecentlySeenProducts():Call<List<ProductsDataClass>>

    @GET()
    fun getSingleProduct(@Url url:String):Call<ProductsDataClass>

    @GET()
    fun getUserBuySimilarProducts(@Url url:String):Call<List<ProductsDataClass>>

    @GET
    fun getRelatedCategories(@Url url: String):Call<List<String>>

    @GET
    fun getSellerProducts(@Url url:String):Call<List<ProductsDataClass>>

    @GET
    fun getTagProducts(@Url url:String):Call<List<ProductsDataClass>>
}