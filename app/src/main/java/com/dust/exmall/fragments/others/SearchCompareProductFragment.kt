package com.dust.exmall.fragments.others

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.CompareProductAdapter
import com.dust.exmall.apicore.ApiServiceManager
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.interfaces.maininterfaces.OnGetProductSearchResult
import com.dust.exmall.interfaces.maininterfaces.OnGetProducts
import com.dust.exmall.search.SearchCenter

class SearchCompareProductFragment() : Fragment() {
    private lateinit var backImage: ImageView
    private lateinit var cancelImage: ImageView
    private lateinit var searchEditText: EditText
    private lateinit var productCount: CTextView
    private lateinit var productCountTitle: CTextView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var nested: NestedScrollView
    private lateinit var searchProgressBar: ProgressBar

    private lateinit var apiServiceManager: ApiServiceManager
    private var dataList = arrayListOf<ProductsDataClass>()
    private var paginateData = arrayListOf<ProductsDataClass>()
    private var pagination = 0
    private lateinit var adapter: CompareProductAdapter

    private var SEARCH_MODE = false
    private var paginationSearch = 0
    private lateinit var adapterSearch: CompareProductAdapter
    private var dataListSearch = arrayListOf<ProductsDataClass>()
    private var paginateDataSearch = arrayListOf<ProductsDataClass>()
    private val SEARCH_BY_NAME = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compare_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpApiServiceManager()
        getMainData()
        setUpBackImage()
        setUpSearchEditText()
        setUpPagination()
    }

    private fun getMainData() {

        apiServiceManager.getProductsByCategory(object : OnGetProducts {
            override fun onGetProducts(data: List<ProductsDataClass>) {
                dataList.addAll(data)
                deleteCurrentProduct()
                productCount.text = requireActivity().resources.getString(R.string.productCount , dataList.size)
                productCountTitle.text = "برترین محصصولات برای مقایسه"
                searchProgressBar.visibility = View.GONE
                setUpProductsRecyclerView()

            }

            override fun onFailureGetProducts(message: String) {

            }

        }, requireArguments().getString("CATEGORY")!!)
    }

    private fun deleteCurrentProduct(){
        for(i in 0 until dataList.size){
            if (dataList[i].id == requireArguments().getInt("ID")){
                dataList.removeAt(i)
                break
            }
        }

        for(i in 0 until dataListSearch.size){
            if (dataListSearch[i].id == requireArguments().getInt("ID")){
                dataListSearch.removeAt(i)
                break
            }
        }
    }

    private fun setUpPagination() {
        nested.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == nested.getChildAt(0).measuredHeight - nested.measuredHeight) {
                if (!SEARCH_MODE) {
                    pagination++
                    val dataCount = paginateData.size
                    optimizeData()
                    if (paginateData.size != dataCount)
                        adapter.notifyItemRangeInserted(
                            paginateData.size,
                            paginateData.size - dataCount
                        )
                } else {
                    paginationSearch++
                    val dataCount = paginateDataSearch.size
                    optimizeDataSearch()
                    if (paginateDataSearch.size != dataCount)
                        adapterSearch.notifyItemRangeInserted(
                            paginateDataSearch.size,
                            paginateDataSearch.size - dataCount
                        )
                }

            }
        }
    }

    private fun setUpApiServiceManager() {
        apiServiceManager = ApiServiceManager()
    }

    private fun setUpProductsRecyclerView() {
        productsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        optimizeData()
        adapter = CompareProductAdapter(paginateData , requireActivity().supportFragmentManager , requireArguments().getInt("ID"))
        productsRecyclerView.adapter = adapter
        searchProgressBar.isIndeterminate = false
    }

    private fun optimizeData() {
        val start = pagination * 5
        val stop = pagination * 5 + 5
        try {
            for (i in start until stop) {
                paginateData.add(dataList[i])
            }
        } catch (e: Exception) {

        }
    }

    private fun optimizeDataSearch() {
        val start = paginationSearch * 5
        val stop = paginationSearch * 5 + 5
        try {
            for (i in start until stop) {
                paginateDataSearch.add(dataListSearch[i])
            }
        } catch (e: Exception) {

        }
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                productsRecyclerView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            if (SEARCH_MODE) {
                turnOffSearchMode()
            } else {
                requireActivity().supportFragmentManager.popBackStack(
                    "SearchCompareProductFragment",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }
        }
    }

    private fun turnOnSearchMode() {
        SEARCH_MODE = true
        searchProgressBar.visibility = View.VISIBLE
        optimizeDataSearch()
        adapterSearch = CompareProductAdapter(paginateDataSearch , requireActivity().supportFragmentManager , requireArguments().getInt("ID"))
        productsRecyclerView.adapter = adapterSearch
    }

    private fun turnOffSearchMode() {
        searchEditText.setText("")
        SEARCH_MODE = false
        cancelImage.visibility = View.GONE
        searchProgressBar.visibility = View.GONE
        productsRecyclerView.adapter = adapter
    }

    private fun setUpSearchEditText() {
        val searchCenter = SearchCenter()
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (searchEditText.text.toString() != "") {
                    cancelImage.visibility = View.VISIBLE
                } else {
                    cancelImage.visibility = View.GONE
                }
                if (!SEARCH_MODE)
                    turnOnSearchMode()
                // start search
                searchProgressBar.isIndeterminate = true
                searchCenter.searchProduct(object : OnGetProductSearchResult {
                    override fun onGet(list: List<ProductsDataClass>) {
                        paginationSearch = 0
                        paginateDataSearch.clear()
                        dataListSearch.clear()
                        dataListSearch.addAll(list)
                        deleteCurrentProduct()
                        optimizeDataSearch()
                        adapterSearch.notifyDataSetChanged()
                        searchProgressBar.visibility = View.GONE
                    }

                    override fun onNothingFound() {
                        paginationSearch = 0
                        paginateDataSearch.clear()
                        dataListSearch.clear()
                        optimizeDataSearch()
                        adapterSearch.notifyDataSetChanged()
                        searchProgressBar.visibility = View.GONE
                    }

                }, dataList, searchEditText.text.toString(), SEARCH_BY_NAME)

            }

        })
    }

    private fun setUpViews(view: View) {
        cancelImage = view.findViewById(R.id.cancelImage)
        backImage = view.findViewById(R.id.backImage)
        searchEditText = view.findViewById(R.id.searchEditText)
        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)
        nested = view.findViewById(R.id.nested)
        searchProgressBar = view.findViewById(R.id.searchProgressBar)
        productCount = view.findViewById(R.id.productCount)
        productCountTitle = view.findViewById(R.id.productCountTitle)

        cancelImage.setOnClickListener {
            searchEditText.setText("")
            cancelImage.visibility = View.GONE
        }
    }

    fun newInstance(category: String, id: Int): SearchCompareProductFragment {
        val args = Bundle()
        args.putString("CATEGORY", category)
        args.putInt("ID", id)
        val fragment = SearchCompareProductFragment()
        fragment.arguments = args
        return fragment
    }
}