package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView

class FeedbackFragment(): Fragment() {
    private lateinit var backImage:ImageView
    private lateinit var productTitle:CTextView
    private lateinit var similarCodeCard:CardView
    private lateinit var duplicateCheckbox:CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feedback , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setData()
        setUpCheckBoxes()
    }

    private fun setUpCheckBoxes() {
        duplicateCheckbox.setOnCheckedChangeListener { _ , b ->
            if (b)
                similarCodeCard.visibility = View.VISIBLE
            else
                similarCodeCard.visibility = View.GONE
        }
    }

    private fun setData() {
        productTitle.text = requireArguments().getString("NAME")
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack( "FeedbackFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        productTitle = view.findViewById(R.id.productTitle)
        similarCodeCard = view.findViewById(R.id.similarCodeCard)

        duplicateCheckbox = view.findViewById(R.id.duplicateCheckbox)
    }

    fun newInstance(productName: String, productId: Int): FeedbackFragment {
        val args = Bundle()
        args.putString("NAME", productName)
        args.putInt("ID", productId)
        val fragment = FeedbackFragment()
        fragment.arguments = args
        return fragment
    }
}