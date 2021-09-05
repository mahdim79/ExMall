package com.dust.exmall.apicore

import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.interfaces.maininterfaces.OnGetMagicCartContent
import com.dust.exmall.interfaces.maininterfaces.OnGetProducts
import com.dust.exmall.interfaces.maininterfaces.OnGetSliderContent
import com.dust.exmall.interfaces.retrofit.Requests
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

class ApiServiceManager() {
    private lateinit var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().callTimeout(30 , TimeUnit.SECONDS)
                .connectTimeout(30 , TimeUnit.SECONDS)
                .readTimeout(30 , TimeUnit.SECONDS)
                .writeTimeout(30 , TimeUnit.SECONDS).build())
            .build()
    }
    fun getAmazingOffersProducts(onGetProducts: OnGetProducts){
        retrofit.create(Requests::class.java).getAmazingProducts().enqueue(object :Callback<List<ProductsDataClass>>{
            override fun onResponse(
                call: Call<List<ProductsDataClass>>,
                response: Response<List<ProductsDataClass>>
            ) {
                val data = response.body()
                onGetProducts.onGetProducts(data!!)
            }

            override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                onGetProducts.onFailureGetProducts(t.message!!)
            }
        })
    }

    fun getProductsByCategory(onGetProducts: OnGetProducts , category:String){
        val url = "products/category/${category}"
        retrofit.create(Requests::class.java).getProductByCategory(url).enqueue(object :Callback<List<ProductsDataClass>>{
            override fun onResponse(
                call: Call<List<ProductsDataClass>>,
                response: Response<List<ProductsDataClass>>
            ) {
                onGetProducts.onGetProducts(response.body()!!)
            }

            override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                onGetProducts.onFailureGetProducts(t.message!!)
            }

        })
    }

    fun getSliderContent(onGetSliderContent: OnGetSliderContent){
        retrofit.create(Requests::class.java).getSliderContent().enqueue(object :Callback<List<ProductsDataClass>>{
            override fun onResponse(
                call: Call<List<ProductsDataClass>>,
                response: Response<List<ProductsDataClass>>
            ) {
                val list = arrayListOf<Pair<String , String>>()
                response.body()!!.forEach {
                    list.add(Pair(it.title , it.image))
                }
                onGetSliderContent.onGetSliderContent(list)
            }

            override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                onGetSliderContent.onGetFailure(t.message!!)
            }

        })
    }

    fun getMagicCartContents(onGetMagicCartContent: OnGetMagicCartContent){
        retrofit.create(Requests::class.java).getMagicContent().enqueue(object :Callback<List<ProductsDataClass>>{
            override fun onResponse(
                call: Call<List<ProductsDataClass>>,
                response: Response<List<ProductsDataClass>>
            ) {
                val list = arrayListOf<ProductsDataClass>()
                if (response.body()!!.size < 4)
                    list.addAll(response.body()!!)
                else
                    list.addAll(response.body()!!.subList(0 , 4))
                onGetMagicCartContent.onGetMagicCartContents(list)
            }

            override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                onGetMagicCartContent.onFailureMagicCartContents(t.message!!)
            }

        })
    }
}