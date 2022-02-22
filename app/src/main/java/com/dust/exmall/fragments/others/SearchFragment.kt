package com.dust.exmall.fragments.others

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.ProductSearchAdapter
import com.dust.exmall.adapters.recyclerview.SearchAdapter
import com.dust.exmall.adapters.recyclerview.SearchHistoryAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.apicore.ApiServiceManager
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.dataclasses.ProductsDataClass
import com.dust.exmall.interfaces.local.OnHistorySearchSelected
import com.dust.exmall.interfaces.maininterfaces.OnGetProductSearchResult
import com.dust.exmall.interfaces.maininterfaces.OnGetProducts
import com.dust.exmall.search.SearchCenter
import com.dust.exmall.sharedpreferences.SharedPreferencesCenter
import java.util.*

class SearchFragment() : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var imageDelete: ImageView
    private lateinit var imageBack: ImageView
    private lateinit var deleteHistory: ImageView
    private lateinit var productSearchRecyclerView: RecyclerView
    private lateinit var categoriesSearchRecyclerView: RecyclerView
    private lateinit var relativeSearchRecyclerView: RecyclerView
    private lateinit var searchHistoryRecyclerView: RecyclerView
    private lateinit var fullCategoryProductText: CTextView
    private lateinit var seeAllCategoryProductContainer: LinearLayout
    private lateinit var searchHistoryRelative: RelativeLayout
    private lateinit var mainDivider: View

    private val CATEGORY_VIEW_TYPE = 0
    private val RELATIVE_VIEW_TYPE = 1

    private val dataList = arrayListOf<ProductsDataClass>()

    private lateinit var searchCenter: SearchCenter
    private lateinit var apiServiceManager: ApiServiceManager

    private var searchHandler: Handler = Handler()
    private lateinit var runnable: Runnable

    private val animations = Animations()

    private var searchWord = ""

    private lateinit var sharedPreferencesCenter: SharedPreferencesCenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setUpViews(view)
        setUpAnimations()
        setUpSharedPreferences()
        setUpSearchHistoryRelative()
        setUpSearchCenter()
        setUpApiServiceManager()
        setUpBackImage()
        setUpSearchEditText()
        setUpDeleteImage()
    }

    private fun setUpSharedPreferences() {
        sharedPreferencesCenter = SharedPreferencesCenter(requireContext())
    }

    private fun setUpSearchHistoryRelative() {
        val history = sharedPreferencesCenter.getSearchHistory()
        if (history.isNotEmpty()) {
            searchHistoryRelative.visibility = View.VISIBLE
            searchHistoryRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            searchHistoryRecyclerView.adapter =
                SearchHistoryAdapter(history, object : OnHistorySearchSelected {
                    override fun onSelect(word: String) {
                        searchEditText.setText(word)
                    }
                })

            deleteHistory.setOnClickListener {
                searchHistoryRelative.visibility = View.GONE
                sharedPreferencesCenter.removeSearchList()
            }

        }
    }

    private fun setUpAnimations() {
        seeAllCategoryProductContainer.setOnTouchListener { v, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_CANCEL) {
                v.startAnimation(animations.getFadeInAnimation())
                return@setOnTouchListener false
            }
            if (motionEvent.action == MotionEvent.ACTION_UP)
                v.startAnimation(animations.getFadeInAnimation())
            else
                v.startAnimation(animations.getFadeOutAnimation())
            false
        }
    }

    private fun setUpApiServiceManager() {
        apiServiceManager = ApiServiceManager()
    }

    private fun setUpSearchCenter() {
        searchCenter = SearchCenter()
    }

    private fun setUpDeleteImage() {
        imageDelete.setOnClickListener {
            searchEditText.setText("")
            try {
                searchHandler.removeCallbacks(runnable)
            } catch (e: Exception) {
            }
        }
    }


    private fun setUpBackImage() {
        imageBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "SearchFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpSearchEditText() {
        searchEditText.requestFocus()
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                try {
                    searchHandler.removeCallbacks(runnable)
                } catch (e: Exception) {
                }
                if (searchEditText.text.toString() != "") {
                    imageDelete.visibility = View.VISIBLE
                    searchHandler.postDelayed({
                        if (dataList.size != 0) {
                            startSearchProcess(searchEditText.text.toString())
                        } else {
                            apiServiceManager.getAllProducts(object : OnGetProducts {
                                override fun onGetProducts(data: List<ProductsDataClass>) {
                                    dataList.addAll(data)
                                    startSearchProcess(searchEditText.text.toString())
                                }

                                override fun onFailureGetProducts(message: String) {

                                }

                            })
                        }

                    }, 1000)

                } else {
                    imageDelete.visibility = View.GONE

                }

            }
        })
    }

    private fun startSearchProcess(word: String) {
        searchCenter.searchProduct(object : OnGetProductSearchResult {
            override fun onGet(list: List<ProductsDataClass>) {
                val resultList = arrayListOf<ProductsDataClass>()
                try {
                    resultList.addAll(list.subList(0, 8))
                } catch (e: IndexOutOfBoundsException) {
                    resultList.clear()
                    resultList.addAll(list)
                }

                val categoryList = arrayListOf<String>()
                resultList.forEach { p ->
                    if (!categoryList.contains(p.category) && categoryList.size < 3)
                        categoryList.add(p.category)
                }

                setUpNewData(resultList, categoryList)

                searchWord = word
            }

            override fun onNothingFound() {

            }
        }, dataList, word, 0)
    }

    private fun setUpNewData(resultList: ArrayList<ProductsDataClass>, categoryList: List<String>) {
        if (resultList.isNotEmpty())
            mainDivider.visibility = View.VISIBLE
        productSearchRecyclerView.adapter =
            ProductSearchAdapter(resultList, requireActivity().supportFragmentManager)
        categoriesSearchRecyclerView.adapter = SearchAdapter(
            CATEGORY_VIEW_TYPE,
            categoryList,
            requireActivity().supportFragmentManager
        )
        seeAllCategoryProductContainer.visibility = View.VISIBLE
        seeAllCategoryProductContainer.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, CategoryProductsFragment().newInstance(categoryList[0]))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("CategoryProductsFragment")
                .commit()
        }

        fullCategoryProductText.text = requireActivity().resources.getString(
            R.string.allSpecifiedCategoryProducts,
            categoryList[0]
        )

    }

    override fun onDestroy() {
        checkFlags()
        if (searchWord != "")
            sharedPreferencesCenter.addToSearchHistory(searchWord)
        super.onDestroy()
    }

    private fun setUpViews(view: View) {
        searchEditText = view.findViewById(R.id.searchEditText)
        imageDelete = view.findViewById(R.id.imageDelete)
        imageBack = view.findViewById(R.id.imageBack)
        searchHistoryRelative = view.findViewById(R.id.searchHistoryRelative)
        fullCategoryProductText = view.findViewById(R.id.fullCategoryProductText)
        productSearchRecyclerView = view.findViewById(R.id.productSearchRecyclerView)
        categoriesSearchRecyclerView = view.findViewById(R.id.categoriesSearchRecyclerView)
        seeAllCategoryProductContainer = view.findViewById(R.id.seeAllCategoryProductContainer)
        relativeSearchRecyclerView = view.findViewById(R.id.relativeSearchRecyclerView)
        searchHistoryRecyclerView = view.findViewById(R.id.searchHistoryRecyclerView)
        deleteHistory = view.findViewById(R.id.deleteHistory)
        mainDivider = view.findViewById(R.id.mainDivider)
        productSearchRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoriesSearchRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        relativeSearchRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun checkFlags() {
        try {
            if (requireActivity().supportFragmentManager.getBackStackEntryAt(requireActivity().supportFragmentManager.backStackEntryCount - 1).name == "LocalProductsFragment")
                requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }catch (e:Exception){}
    }

}