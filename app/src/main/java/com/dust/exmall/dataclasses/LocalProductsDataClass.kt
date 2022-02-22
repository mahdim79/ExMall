package com.dust.exmall.dataclasses

data class LocalProductsDataClass(var headerList:List<CardsDataClass> ,
                                  var topList: List<CardsDataClass> ,
                                  var favoritesProductsList:List<ProductsDataClass> ,
                                  var newestProductsList:List<ProductsDataClass> ,
                                  var cheapestProductsList:List<ProductsDataClass> ,
                                  var mainCategories:List<CategoryDataClass> ,
                                  var wearingCategories:List<CategoryDataClass> ,
                                  var playingCategories:List<CategoryDataClass> ,
                                  var eatingCategories:List<CategoryDataClass> ,
                                  var topBrands:List<TopBrandDataClass> ,
                                  var fourCards:List<CardsDataClass>
                                  )
data class CategoryDataClass(var name:String , var image:String , var productCount:Int)