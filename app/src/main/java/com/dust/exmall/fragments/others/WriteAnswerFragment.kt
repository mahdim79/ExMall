package com.dust.exmall.fragments.others

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dust.exmall.R
import com.dust.exmall.customviews.CButton
import com.dust.exmall.customviews.CTextView

class WriteAnswerFragment() : Fragment() {
    private lateinit var backImage:ImageView
    private lateinit var counter:CTextView
    private lateinit var rulesText:CTextView
    private lateinit var QuestionTextEditText:EditText
    private lateinit var sendQuestionButton:CButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_answer , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpEditText()
        setUpRules()
    }

    private fun setUpEditText() {
        // editText Counter
        QuestionTextEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                counter.text = "${QuestionTextEditText.text.toString().length}/100"
            }
        })
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack( "WriteAnswerFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpRules() {
        val spannableString =
            SpannableString(requireContext().resources.getString(R.string.commentRules))
        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),
            30,
            51,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(requireContext(), "this is rules", Toast.LENGTH_SHORT).show()
            }

        }, 30, 51, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
        rulesText.text = spannableString
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        counter = view.findViewById(R.id.counter)
        rulesText = view.findViewById(R.id.rulesText)
        QuestionTextEditText = view.findViewById(R.id.QuestionTextEditText)
        sendQuestionButton = view.findViewById(R.id.sendQuestionButton)
    }
}