package com.dust.exmall.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesCenter(var context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MAIN_SHARED_PREFERENCES" , Context.MODE_PRIVATE)

    fun addToSearchHistory(word:String){
        val historyList = arrayListOf<String>()
        historyList.addAll(sharedPreferences.getString("SEARCH_HISTORY" , "")!!.split('|'))

        val resultList = arrayListOf<String>()
        try {
            resultList.addAll(historyList.subList( (historyList.indices.last - 10) , historyList.indices.last))
        }catch (e:Exception){
            resultList.clear()
            resultList.addAll(historyList)
        }

        if (!resultList.contains(word)){
            resultList.add(word)
            val result = resultList.joinToString("|")
            sharedPreferences.edit().putString("SEARCH_HISTORY" , result).apply()
        }
        historyList.clear()
        resultList.clear()
    }

    fun getSearchHistory():List<String>{
        val resultList = arrayListOf<String>()
        resultList.addAll(sharedPreferences.getString("SEARCH_HISTORY" , "")!!.split("|"))
        for (i in resultList.indices)
            if (resultList[i] == ""){
                resultList.removeAt(i)
                break
            }
        resultList.reverse()
        return resultList
    }

    fun removeSearchList(){
        sharedPreferences.edit().remove("SEARCH_HISTORY").apply()
    }
}