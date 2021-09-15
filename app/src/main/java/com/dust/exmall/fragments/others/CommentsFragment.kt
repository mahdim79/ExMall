package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.CommentAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.dataclasses.CommentDataClass
import com.dust.exmall.fragments.bottomsheets.SortBottomSheet

class CommentsFragment() : Fragment() {
    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var backImage: ImageView
    private lateinit var sortImage: ImageView

    private val animations = Animations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpViewsAnimations()
        setUpBackImage()
        setUpSortButton()
        setUpCommentRecyclerView()
    }

    private fun setUpCommentRecyclerView() {
        commentsRecyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        commentsRecyclerView.adapter = CommentAdapter(generateFakeComments())
    }

    private fun setUpViewsAnimations() {
        backImage.setOnTouchListener { v, motionEvent ->
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

        sortImage.setOnTouchListener { v, motionEvent ->
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


    private fun setUpSortButton() {
        sortImage.setOnClickListener {
            val dialog = SortBottomSheet()
            dialog.show(requireActivity().supportFragmentManager , "SortBottomSheet")
        }
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "CommentsFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpViews(view: View) {
        commentsRecyclerView = view.findViewById(R.id.commentsRecyclerView)
        backImage = view.findViewById(R.id.backImage)
        sortImage = view.findViewById(R.id.sortImage)
    }

    private fun generateFakeComments():List<CommentDataClass>{
        val list = arrayListOf<CommentDataClass>()
        for(i in 0..10){
            list.add(CommentDataClass("کامنت شماره$i" , i , "مشکل خاصی نداشت" , "15 روز پیش" , "محمد اسدی"))
        }
        return list
    }
}