package com.dust.exmall.fragments.others.specialfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.fragments.others.SearchFragment

class LocalProductsCategoryFragment() : Fragment() {

    private lateinit var localCategoriesRecyclerView: RecyclerView
    private lateinit var favoritesLayout: LinearLayout
    private lateinit var bestSellingLayout: LinearLayout
    private lateinit var search_text_linear: LinearLayout
    private lateinit var search_image: ImageView

    private lateinit var favoriteTitle: TextView
    private lateinit var bestSellingTitle: TextView

    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var bestSellingRecyclerView: RecyclerView
    private lateinit var forSaleRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local_product_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setUpViews(view)
        setUpSearchBox()
        setUpTitleTexts()
        setUpLocalCategoriesRecyclerView()
    }

    private fun setUpTitleTexts() {
        favoriteTitle.text = "محبوب ترین کالاها"
        bestSellingTitle.text = "پرفروش ترین کالاها"
    }

    private fun setUpSearchBox() {
        search_image.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        search_text_linear.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , SearchFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("SearchFragment")
                .commit()
        }

        search_image.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack("LocalProductsCategoryFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpLocalCategoriesRecyclerView() {
       // localCategoriesRecyclerView.adapter = LocalProductsSubCategoryAdapter()
    }

    private fun setUpViews(view: View) {
        localCategoriesRecyclerView = view.findViewById(R.id.localCategoriesRecyclerView)
        favoritesLayout = view.findViewById(R.id.favoritesLayout)
        bestSellingLayout = view.findViewById(R.id.bestSellingLayout)
        search_text_linear = view.findViewById(R.id.search_text_linear)
        search_image = view.findViewById(R.id.search_image)

        favoriteTitle = favoritesLayout.findViewById(R.id.title)
        bestSellingTitle = bestSellingLayout.findViewById(R.id.title)

        favoriteRecyclerView = favoritesLayout.findViewById(R.id.localProductsRecyclerView)
        bestSellingRecyclerView = bestSellingLayout.findViewById(R.id.localProductsRecyclerView)
        forSaleRecyclerView = view.findViewById(R.id.forSaleRecyclerView)

        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        bestSellingRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
        forSaleRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        localCategoriesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun onDestroy() {
        checkFlags()
        super.onDestroy()
    }

    private fun checkFlags() {
        try {
            if (requireActivity().supportFragmentManager.getBackStackEntryAt(requireActivity().supportFragmentManager.backStackEntryCount - 1).name == "LocalProductsFragment")
                requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }catch (e:Exception){}
    }
}