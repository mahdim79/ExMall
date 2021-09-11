package com.dust.exmall.dataclasses

data class FeatureDataClass(var title:String , var dataList:List<SingleFeatureDataClass>)
data class SingleFeatureDataClass(var title: String , var featureList:List<String>)
