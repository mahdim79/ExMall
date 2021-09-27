package com.dust.exmall.fragments.others

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.adapters.recyclerview.GoodAndWeakPointsAdapter
import com.dust.exmall.customviews.CButton
import com.dust.exmall.customviews.CTextView
import com.squareup.picasso.Picasso

class WriteReviewFragment() : Fragment() {
    private lateinit var returnImage: ImageView
    private lateinit var productImage: ImageView
    private lateinit var goodPointAddImage: ImageView
    private lateinit var weakPointAddImage: ImageView
    private lateinit var productTitle: CTextView
    private lateinit var rulesText: CTextView
    private lateinit var rateState: TextView
    private lateinit var rateSeekBar: SeekBar
    private lateinit var commentTitleEditText: EditText
    private lateinit var goodPointsEditText: EditText
    private lateinit var weakPointsEditText: EditText
    private lateinit var commentTextEditText: EditText
    private lateinit var anonymousCheckbox: CheckBox
    private lateinit var sendCommentButton: CButton
    private lateinit var weakRecyclerView: RecyclerView
    private lateinit var goodRecyclerView: RecyclerView

    private val goodPointsList = arrayListOf<String>()
    private val weakPointsList = arrayListOf<String>()

    private var rateLevel = 0

    private lateinit var goodPointsAdapter: GoodAndWeakPointsAdapter
    private lateinit var weakPointsAdapter: GoodAndWeakPointsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpReturnButton()
        setUpProductData()
        setUpRatingSystem()
        setUpRecyclerViews()
        setUpEditTexts()
        setUpSubmitButton()
        setUpCommentRules()
    }

    private fun setUpSubmitButton() {
        sendCommentButton.setOnClickListener {
            val checkResult = checkDataInputValidity()
            if (checkResult.first) {
                Log.i("submitComment", "done")
            } else {
                Toast.makeText(requireContext(), checkResult.second, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSubmitButtonEnabled(enabled: Boolean) {
        sendCommentButton.background = if (enabled)
            ResourcesCompat.getDrawable(
                requireActivity().resources,
                R.drawable.continue_button_shape,
                null
            )
        else
            ResourcesCompat.getDrawable(
                requireActivity().resources,
                R.drawable.continue_button_shape_disabled,
                null
            )
    }

    private fun checkDataInputValidity(): Pair<Boolean, String> {
        return if (rateLevel != 0) {
            if (commentTextEditText.text.toString() != "") {
                if (commentTextEditText.text.toString().length > 10) {
                    Pair(true, "RESULT_OK")
                } else {
                    Pair(false, "لطفا نظر خود را کامل تر بیان کنید")
                }
            } else {
                Pair(false, "لطفا نظر خود را بیان کنید")
            }
        } else {
            Pair(false, "لطفا امتیاز ثبت کنید")
        }
    }

    private fun setUpRecyclerViews() {
        goodPointsAdapter = GoodAndWeakPointsAdapter(goodPointsList, false)
        weakPointsAdapter = GoodAndWeakPointsAdapter(weakPointsList, true)
        goodRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        weakRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        weakRecyclerView.adapter = weakPointsAdapter
        goodRecyclerView.adapter = goodPointsAdapter
    }

    private fun setUpEditTexts() {
        goodPointAddImage.setOnClickListener {
            if (goodPointsEditText.text.toString() != "" && !checkFullSpaceContent(
                    goodPointsEditText.text.toString()
                )
            ) {
                goodPointsList.add(goodPointsEditText.text.toString())
                goodPointsAdapter.notifyDataSetChanged()
                goodPointsEditText.setText("")
            }
        }

        weakPointAddImage.setOnClickListener {
            if (weakPointsEditText.text.toString() != "" && !checkFullSpaceContent(
                    weakPointsEditText.text.toString()
                )
            ) {
                weakPointsList.add(weakPointsEditText.text.toString())
                weakPointsAdapter.notifyDataSetChanged()
                weakPointsEditText.setText("")
            }
        }

        commentTextEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                submitButtonHandler()
            }

        })
    }

    private fun submitButtonHandler() {
        setSubmitButtonEnabled(checkDataInputValidity().first)
    }

    private fun checkFullSpaceContent(str: String): Boolean {
        for (i in 0 until str.length)
            if (str[i] != ' ')
                return false
        return true
    }

    private fun setUpRatingSystem() {
        rateSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                rateLevel = p1
                when (p1) {
                    0 -> {
                        rateState.text = ""
                    }
                    1 -> {
                        rateState.text = "خیلی بد"
                    }
                    2 -> {
                        rateState.text = "بد"
                    }
                    3 -> {
                        rateState.text = "خوب"
                    }
                    4 -> {
                        rateState.text = "خیلی خوب"
                    }
                    5 -> {
                        rateState.text = "عالی"
                    }
                }
                submitButtonHandler()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun setUpCommentRules() {
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
                // TODO: 9/19/2021 Implement Rules Fragment
            }

        }, 30, 51, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
        rulesText.text = spannableString
    }

    private fun setUpReturnButton() {
        returnImage.setOnClickListener {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                returnImage.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            requireActivity().supportFragmentManager.popBackStack(
                "WriteReviewFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpProductData() {
        Picasso.get().load(requireArguments().getString("IMAGE")).into(productImage)
        productTitle.text = requireArguments().getString("TITLE")
    }

    private fun setUpViews(view: View) {
        returnImage = view.findViewById(R.id.backImage)
        productImage = view.findViewById(R.id.productImage)
        productTitle = view.findViewById(R.id.productTitle)
        rateState = view.findViewById(R.id.rateState)
        rateSeekBar = view.findViewById(R.id.rateSeekBar)
        commentTitleEditText = view.findViewById(R.id.commentTitleEditText)
        goodPointsEditText = view.findViewById(R.id.goodPointsEditText)
        weakPointsEditText = view.findViewById(R.id.weakPointsEditText)
        commentTextEditText = view.findViewById(R.id.commentTextEditText)
        anonymousCheckbox = view.findViewById(R.id.anonymousCheckbox)
        sendCommentButton = view.findViewById(R.id.sendCommentButton)
        rulesText = view.findViewById(R.id.rulesText)
        weakPointAddImage = view.findViewById(R.id.weakPointAddImage)
        goodPointAddImage = view.findViewById(R.id.goodPointAddImage)
        weakRecyclerView = view.findViewById(R.id.weakRecyclerView)
        goodRecyclerView = view.findViewById(R.id.goodRecyclerView)
    }

    fun newInstance(
        productTitle: String,
        productImage: String,
        productId: Int
    ): WriteReviewFragment {
        val args = Bundle()
        args.putString("TITLE", productTitle)
        args.putString("IMAGE", productImage)
        args.putInt("ID", productId)
        val fragment = WriteReviewFragment()
        fragment.arguments = args
        return fragment
    }
}