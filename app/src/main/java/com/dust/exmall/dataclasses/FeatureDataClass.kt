package com.dust.exmall.dataclasses

data class FeatureDataClass(var title:String , var dataList:List<SingleFeatureDataClass>)
data class SingleFeatureDataClass(var title: String , var featureList:List<String>)
data class ImportantFeatureDataClass(var title: String , var feature:String)
