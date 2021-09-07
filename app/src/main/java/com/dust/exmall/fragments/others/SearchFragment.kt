package com.dust.exmall.fragments.others

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.ProductSearchAdapter
import com.dust.exmall.adapters.recyclerview.SearchAdapter

class SearchFragment():Fragment() {
    private lateinit var searchEditText:EditText
    private lateinit var imageDelete:ImageView
    private lateinit var imageBack:ImageView
    private lateinit var productSearchRecyclerView:RecyclerView
    private lateinit var categoriesSearchRecyclerView:RecyclerView
    private lateinit var relativeSearchRecyclerView:RecyclerView

    private val CATEGORY_VIEW_TYPE = 0
    private val RELATIVE_VIEW_TYPE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpSearchEditText()
        setUpProductSearchRecyclerView()
        setUpCategoryRecyclerView()
        setUpRelativeRecyclerView()
    }

    private fun setUpRelativeRecyclerView() {
        categoriesSearchRecyclerView.adapter = SearchAdapter(RELATIVE_VIEW_TYPE)
    }

    private fun setUpCategoryRecyclerView() {
        relativeSearchRecyclerView.adapter = SearchAdapter(CATEGORY_VIEW_TYPE)
    }

    private fun setUpProductSearchRecyclerView() {
        productSearchRecyclerView.adapter = ProductSearchAdapter()
    }

    private fun setUpBackImage() {
        imageBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack("SearchFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpSearchEditText() {
        searchEditText.requestFocus()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(searchEditText , InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setUpViews(view: View) {
        searchEditText = view.findViewById(R.id.searchEditText)
        imageDelete = view.findViewById(R.id.imageDelete)
        imageBack = view.findViewById(R.id.imageBack)
        productSearchRecyclerView = view.findViewById(R.id.productSearchRecyclerView)
        categoriesSearchRecyclerView = view.findViewById(R.id.categoriesSearchRecyclerView)
        relativeSearchRecyclerView = view.findViewById(R.id.relativeSearchRecyclerView)
        productSearchRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        categoriesSearchRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        relativeSearchRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
    }
}