package com.dust.exmall.apicore

import com.dust.exmall.dataclasses.*
import com.dust.exmall.interfaces.maininterfaces.*
import com.dust.exmall.interfaces.retrofit.Requests
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiServiceManager() {
    private lateinit var retrofit: Retrofit
    
    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).build()
            )
            .build()
    }

    fun getAllProducts(onGetProducts: OnGetProducts) {
        retrofit.create(Requests::class.java).getAllProducts()
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getAmazingOffersProducts(onGetProducts: OnGetProducts) {
        retrofit.create(Requests::class.java).getAmazingProducts()
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getProductsByCategory(onGetProducts: OnGetProducts, category: String) {
        val url = "products/category/${category}"
        retrofit.create(Requests::class.java).getProductsByCategory(url)
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getSliderContent(onGetSliderContent: OnGetSliderContent) {
        retrofit.create(Requests::class.java).getSliderContent()
            .enqueue(object : Callback<List<ProductsDataClass>> {
                override fun onResponse(
                    call: Call<List<ProductsDataClass>>,
                    response: Response<List<ProductsDataClass>>
                ) {
                    val list = arrayListOf<Pair<String, String>>()
                    response.body()!!.forEach {
                        list.add(Pair(it.title, it.image))
                    }
                    onGetSliderContent.onGetSliderContent(list)
                }

                override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                    onGetSliderContent.onGetFailure(t.message!!)
                }

            })
    }

    fun getMagicCartContents(onGetMagicCartContent: OnGetMagicCartContent) {
        retrofit.create(Requests::class.java).getMagicContent()
            .enqueue(object : Callback<List<ProductsDataClass>> {
                override fun onResponse(
                    call: Call<List<ProductsDataClass>>,
                    response: Response<List<ProductsDataClass>>
                ) {
                    val list = arrayListOf<ProductsDataClass>()
                    if (response.body()!!.size < 9)
                        list.addAll(response.body()!!)
                    else
                        list.addAll(response.body()!!.subList(0, 9))
                    onGetMagicCartContent.onGetMagicCartContents(list)
                }

                override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                    onGetMagicCartContent.onFailureMagicCartContents(t.message!!)
                }

            })
    }

    fun getPopularCategories(onGetCategories: OnGetCategories) {
        retrofit.create(Requests::class.java).getPopularCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    val list = arrayListOf<String>()
                    if (response.body()!!.size > 4) {
                        list.addAll(response.body()!!.subList(0, 4))
                    } else if (response.body()!!.size < 4) {
                        list.addAll(response.body()!!)
                        for (i in 0 until 4) {
                            list.add(response.body()!![response.body()!!.size - 1])
                            if (list.size == 4)
                                break
                        }
                    } else {
                        list.addAll(response.body()!!)
                    }
                    onGetCategories.onGetCategories(list)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    onGetCategories.onFailureGetCategories(t.message!!)
                }

            })
    }

    fun getPopularProductsByCategory(
        onGetPopularProducts: OnGetPopularProducts,
        category: String,
        tag: Int
    ) {
        val url = "products/category/${category}"
        retrofit.create(Requests::class.java).getProductsByCategory(url)
            .enqueue(object : Callback<List<ProductsDataClass>> {
                override fun onResponse(
                    call: Call<List<ProductsDataClass>>,
                    response: Response<List<ProductsDataClass>>
                ) {
                    onGetPopularProducts.onGetPopularProducts(response.body()!!, tag)
                }

                override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                    onGetPopularProducts.onFailureGetPopularProducts(t.message!!)
                }

            })
    }

    fun getPlusProducts(onGetProducts: OnGetProducts) {
        retrofit.create(Requests::class.java).getPlusProducts()
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getBestSellersProducts(onGetProducts: OnGetProducts) {
        retrofit.create(Requests::class.java).getBestSellersProducts()
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getTopBrands(onGetTopBrands: OnGetTopBrands) {
        retrofit.create(Requests::class.java).getTopBrands()
            .enqueue(object : Callback<List<TopBrandDataClass>> {
                override fun onResponse(
                    call: Call<List<TopBrandDataClass>>,
                    response: Response<List<TopBrandDataClass>>
                ) {
                    onGetTopBrands.onGetTopBrands(response.body()!!)
                }

                override fun onFailure(call: Call<List<TopBrandDataClass>>, t: Throwable) {
                    onGetTopBrands.onFailureGetTopBrands(t.message!!)
                }

            })
    }

    fun getHighReviewedProducts(onGetProducts: OnGetProducts) {
        retrofit.create(Requests::class.java).getHighReviewedProducts()
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getForSaleProducts(onGetProducts: OnGetProducts) {
        retrofit.create(Requests::class.java).getForSaleProducts()
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getRecentlySeenProducts(onGetProducts: OnGetProducts) {
        retrofit.create(Requests::class.java).getRecentlySeenProducts()
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getSingleProduct(onGetProducts: OnGetProducts, id: Int) {
        retrofit.create(Requests::class.java).getSingleProduct("products/$id")
            .enqueue(object : Callback<ProductsDataClass> {
                override fun onResponse(
                    call: Call<ProductsDataClass>,
                    response: Response<ProductsDataClass>
                ) {
                    onGetProducts.onGetProducts(arrayListOf(response.body()!!))
                }

                override fun onFailure(call: Call<ProductsDataClass>, t: Throwable) {
                    onGetProducts.onFailureGetProducts(t.message!!)
                }

            })
    }

    fun getUserBuySimilarProducts(onGetProducts: OnGetProducts, ProductId: String) {
        val url = "products/category/${ProductId}"
        retrofit.create(Requests::class.java).getProductsByCategory(url)
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getRelatedCategories(onGetCategories: OnGetCategories, category: String) {
        // put category here
        val url = "products/categories"
        retrofit.create(Requests::class.java).getRelatedCategories(url)
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    onGetCategories.onGetCategories(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    onGetCategories.onFailureGetCategories(t.message!!)
                }

            })
    }

    fun getSellerProducts(onGetProducts: OnGetProducts, sellerId: Int) {
        retrofit.create(Requests::class.java).getSellerProducts("products?limit=16")
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getTagsProducts(onGetProducts: OnGetProducts, tagName: String) {
        retrofit.create(Requests::class.java).getTagProducts("products?limit=16")
            .enqueue(object : Callback<List<ProductsDataClass>> {
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

    fun getLocalData(onGetLocalData: OnGetLocalData){
        retrofit.create(Requests::class.java).getLocalProductsData().enqueue(object :Callback<List<ProductsDataClass>>{
            override fun onResponse(
                call: Call<List<ProductsDataClass>>,
                response: Response<List<ProductsDataClass>>
            ) {
                // we don't have an stable api so we set request return type (List<ProductsDataClass>) . so we have to create fake LocalProductsDataClass! /:
                onGetLocalData.onGetData(generateFakeLocalDataClass(response.body()))
            }

            override fun onFailure(call: Call<List<ProductsDataClass>>, t: Throwable) {
                onGetLocalData.onFailureGetData(t.message!!)
            }

        })
    }

    private fun generateFakeLocalDataClass(body: List<ProductsDataClass>?): LocalProductsDataClass {
        val headerList = arrayListOf<CardsDataClass>()
        headerList.add(CardsDataClass("LINK" , "www.google.com" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        headerList.add(CardsDataClass("LINK" , "www.google.com" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        headerList.add(CardsDataClass("LINK" , "www.google.com" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        headerList.add(CardsDataClass("LINK" , "www.google.com" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        val categoryData = arrayListOf<CategoryDataClass>()
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        categoryData.add(CategoryDataClass("خشک بار نمونه" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png" , 700))
        val topBrandList = arrayListOf<TopBrandDataClass>()
        topBrandList.add(TopBrandDataClass(0 , "برند نمونه" , "" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        topBrandList.add(TopBrandDataClass(0 , "برند نمونه" , "" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        topBrandList.add(TopBrandDataClass(0 , "برند نمونه" , "" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        topBrandList.add(TopBrandDataClass(0 , "برند نمونه" , "" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))
        topBrandList.add(TopBrandDataClass(0 , "برند نمونه" , "" , "" , "" , "https://www.creatopy.com/blog/wp-content/uploads/2016/06/images-for-banner-ads-1024x527.png"))

        return LocalProductsDataClass(headerList , headerList , body!! , body , body ,categoryData , categoryData , categoryData , categoryData , topBrandList, headerList )
    }
}