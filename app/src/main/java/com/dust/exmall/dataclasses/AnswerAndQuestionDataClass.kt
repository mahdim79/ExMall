package com.dust.exmall.dataclasses

data class AnswerAndQuestionDataClass(var id:Int , var text:String , var reply:Replies)
data class Replies(var id:Int , var text:String , var submitter:String , var submitterType:String , var likes:Int , var disLikes:Int)
