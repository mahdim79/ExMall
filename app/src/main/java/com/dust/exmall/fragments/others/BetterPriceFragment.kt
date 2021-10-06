package com.dust.exmall.fragments.others

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dust.exmall.R
import com.dust.exmall.customviews.CButton
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.interfaces.local.OnStateSelected

class BetterPriceFragment() : Fragment() {

    private lateinit var backImage: ImageView
    private lateinit var productTitle: CTextView
    private lateinit var shopPlaceText: TextView
    private lateinit var onlineShopContainer: LinearLayout
    private lateinit var placeShopContainer: LinearLayout
    private lateinit var onlineShopCheckbox: CheckBox
    private lateinit var choosePlaceContainer: RelativeLayout
    private lateinit var priceEditText: EditText
    private lateinit var shopAddressEditText: EditText
    private lateinit var shopTitleEditText: EditText
    private lateinit var submitButton: CButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_better_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpTitle()
        setUpCheckBox()
        setUpChoosePlace()
        setUpSubmitButton()
        setUpEditTexts()
    }

    private fun setUpEditTexts() {
        val offlineShopTextWatcher = object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                submitButton.isEnabled = priceEditText.text.toString() != "" && shopPlaceText.text.toString() != "" && shopTitleEditText.text.toString() != ""
            }
        }

        shopPlaceText.addTextChangedListener(offlineShopTextWatcher)
        shopTitleEditText.addTextChangedListener(offlineShopTextWatcher)

        shopAddressEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                submitButton.isEnabled = priceEditText.text.toString() != "" && shopAddressEditText.text.toString() != ""
            }
        })
    }

    private fun setUpSubmitButton() {
        submitButton.setOnClickListener {
            if (onlineShopCheckbox.isChecked){
                // TODO: 9/28/2021 start sending request
            }else{
                // TODO: 9/28/2021 start sending request
            }
        }
    }

    private fun setUpChoosePlace() {
        choosePlaceContainer.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, SearchStateFragment(object : OnStateSelected {
                    override fun onSelect(stateName: String) {
                        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                            backImage.windowToken,
                            InputMethodManager.HIDE_NOT_ALWAYS
                        )
                        shopPlaceText.text = stateName
                    }
                }))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("SearchStateFragment")
                .commit()
        }
    }

    private fun setUpCheckBox() {
        onlineShopCheckbox.setOnCheckedChangeListener { _, b ->
            if (b) {
                placeShopContainer.visibility = View.GONE
                onlineShopContainer.visibility = View.VISIBLE

                submitButton.isEnabled = shopAddressEditText.text.toString() != "" && priceEditText.text.toString() != ""
            } else {
                onlineShopContainer.visibility = View.GONE
                placeShopContainer.visibility = View.VISIBLE

                submitButton.isEnabled = priceEditText.text.toString() != "" && shopPlaceText.text.toString() != "" && shopTitleEditText.text.toString() != ""
            }
        }
    }

    private fun setUpTitle() {
        productTitle.text = requireArguments().getString("NAME")
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "BetterPriceFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        productTitle = view.findViewById(R.id.productTitle)
        placeShopContainer = view.findViewById(R.id.placeShopContainer)
        onlineShopContainer = view.findViewById(R.id.onlineShopContainer)
        onlineShopCheckbox = view.findViewById(R.id.onlineShopCheckbox)
        choosePlaceContainer = view.findViewById(R.id.choosePlaceContainer)
        shopPlaceText = view.findViewById(R.id.shopPlaceText)
        priceEditText = view.findViewById(R.id.priceEditText)
        submitButton = view.findViewById(R.id.submitButton)
        shopAddressEditText = view.findViewById(R.id.shopAddressEditText)
        shopTitleEditText = view.findViewById(R.id.shopTitleEditText)
    }

    fun newInstance(productName: String, productId: Int): BetterPriceFragment {
        val args = Bundle()
        args.putString("NAME", productName)
        args.putInt("ID", productId)
        val fragment = BetterPriceFragment()
        fragment.arguments = args
        return fragment
    }
}