package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.QuestionAndAnswerAdapter
import com.dust.exmall.dataclasses.AnswerAndQuestionDataClass
import com.dust.exmall.dataclasses.Replies
import com.dust.exmall.fragments.bottomsheets.SortBottomSheet
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AnswerAndQuestionFragment() : Fragment() {
    private lateinit var sortImage:ImageView
    private lateinit var backImage:ImageView
    private lateinit var commentsRecyclerView:RecyclerView
    private lateinit var floatingButton:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_answer_and_question , container ,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpSortImage()
        getPrimaryData()
        setUpFloatingActionButton()
    }

    private fun setUpFloatingActionButton() {
        floatingButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , WriteAnswerFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("WriteAnswerFragment")
                .commit()
        }
    }

    private fun getPrimaryData() {
        commentsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        commentsRecyclerView.adapter = QuestionAndAnswerAdapter(generateRandomFakeAnswerAndQuestion() , requireContext() , requireActivity().supportFragmentManager)
    }

    private fun setUpSortImage() {
        sortImage.setOnClickListener {
            val dialog = SortBottomSheet()
            dialog.show(requireActivity().supportFragmentManager , "SortBottomSheet")
        }
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack("AnswerAndQuestionFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        sortImage = view.findViewById(R.id.sortImage)
        commentsRecyclerView = view.findViewById(R.id.commentsRecyclerView)
        floatingButton = view.findViewById(R.id.floating_button)
    }

    private fun generateRandomFakeAnswerAndQuestion():List<AnswerAndQuestionDataClass>{
        val list = arrayListOf<AnswerAndQuestionDataClass>()
        var reply = Replies(-1 , "" , "" , "" , 0 , 0)
        for (i in 0..20){
            if ((Math.random() * 20) > 10){
                reply = Replies(i , "نخیر این امکان پذیر نیست!" , "اس ام" , "فروشنده" , 1 , 2)
            }
            list.add(AnswerAndQuestionDataClass(i , "این یک متن نمایشی است" , reply))
        }
        return list
    }
}