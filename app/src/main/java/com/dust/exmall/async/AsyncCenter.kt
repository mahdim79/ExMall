package com.dust.exmall.async

import android.os.AsyncTask
import android.text.TextUtils
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.interfaces.maininterfaces.OnGetProductSearchResult
import java.util.regex.Pattern

class AsyncCenter {
    private lateinit var onGetProductSearchResultByName: OnGetProductSearchResult

    fun startProductSearchByName(onGetProductSearchResult: OnGetProductSearchResult, name: String , data:List<ProductsDataClass>) {
        this.onGetProductSearchResultByName = onGetProductSearchResult
        val searchProductByName = SearchProductByName()
        searchProductByName.execute(Pair(name , data))
    }

    inner class SearchProductByName() : AsyncTask<Pair<String , List<ProductsDataClass>>, Void, List<ProductsDataClass>>() {
        override fun doInBackground(vararg p0: Pair<String , List<ProductsDataClass>>?): List<ProductsDataClass> {
            val resultList = arrayListOf<ProductsDataClass>()
            p0[0]!!.second.forEach {
                if (it.title.contains(p0[0]!!.first , true))
                    resultList.add(it)
            }
            return resultList
        }

        override fun onPostExecute(result: List<ProductsDataClass>?) {
            super.onPostExecute(result)
            if (result!!.isEmpty())
                onGetProductSearchResultByName.onNothingFound()
            else
                onGetProductSearchResultByName.onGet(result)
        }

    }
}