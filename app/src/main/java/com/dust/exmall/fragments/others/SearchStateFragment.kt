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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.StateAdapter
import com.dust.exmall.interfaces.local.OnStateSelected

class SearchStateFragment(var onStateSelected: OnStateSelected) : Fragment() {
    private lateinit var stateRecyclerView:RecyclerView
    private lateinit var backImage:ImageView
    private lateinit var searchEditText:EditText

    private lateinit var stateAdapter: StateAdapter

    private val mainStateSource = arrayListOf<String>()
    private val currentStateSource = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_state , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpDataSources()
        setUpMainRecyclerViewAdapter()
        setUpSearchEditText()
    }

    private fun setUpDataSources() {
        mainStateSource.add("تهران")
        mainStateSource.add("خراسان رضوی")
        mainStateSource.add("خراسان جنوبی")
        mainStateSource.add("سمنان")
        mainStateSource.add("کرمان")
        mainStateSource.add("آذربایجان غربی")
        mainStateSource.add("آذربایجان شرقی")
        mainStateSource.add("اردبیل")
        mainStateSource.add("اصفهان")
        mainStateSource.add("البرز")
        mainStateSource.add("ایلام")
        mainStateSource.add("بوشهر")
        mainStateSource.add("چهارمحال و بختیاری")
        mainStateSource.add("خراسان شمالی")
        mainStateSource.add("خوزستان")
        mainStateSource.add("زنجان")
        mainStateSource.add("سیستان و بلوچستان")
        mainStateSource.add("فارس")
        mainStateSource.add("قزوین")
        mainStateSource.add("قم")
        mainStateSource.add("کردستان")
        mainStateSource.add("کرمانشاه")
        mainStateSource.add("کهکیلویه و بویراحمد")
        mainStateSource.add("گلستان")
        mainStateSource.add("گیلان")
        mainStateSource.add("لرستان")
        mainStateSource.add("مازندران")
        mainStateSource.add("مرکزی")
        mainStateSource.add("هرمزگان")
        mainStateSource.add("همدان")
        mainStateSource.add("یزد")

        currentStateSource.addAll(mainStateSource)
    }

    private fun setUpSearchEditText() {
        searchEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                currentStateSource.clear()
                if (searchEditText.text.toString() == ""){
                    currentStateSource.addAll(mainStateSource)
                }else{
                    for (i in 0 until mainStateSource.size)
                        if (mainStateSource[i].contains(searchEditText.text.toString() , true))
                            currentStateSource.add(mainStateSource[i])
                }
                stateAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(backImage.windowToken , InputMethodManager.HIDE_NOT_ALWAYS)
            requireActivity().supportFragmentManager.popBackStack( "SearchStateFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpMainRecyclerViewAdapter() {
        stateAdapter = StateAdapter(currentStateSource , onStateSelected , requireActivity().supportFragmentManager)
        stateRecyclerView.adapter = stateAdapter
    }

    private fun setUpViews(view: View) {
        stateRecyclerView = view.findViewById(R.id.stateRecyclerView)
        backImage = view.findViewById(R.id.backImage)
        searchEditText = view.findViewById(R.id.searchEditText)

        stateRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
    }
}