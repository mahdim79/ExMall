package com.dust.exmall.fragments.others

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView

class FullSellerInfoFragment() : Fragment() {
    private lateinit var backImage: ImageView
    private lateinit var roundedCornerProgressBar: IconRoundCornerProgressBar
    private lateinit var totalPercentageText: CTextView
    private lateinit var totalSellerStatusText: CTextView
    private lateinit var moreProducts: CTextView

    private val GOOD_SATISFACTION = 0
    private val BAD_SATISFACTION = 1
    private val ORDINARY_SATISFACTION = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_full_seller_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        getSellerData()
    }

    private fun getSellerData() {
        val satisfaction = (Math.random() * 100).toFloat()
        setUpTotalPercentageText(satisfaction)
        setUpTotalStatusText(satisfaction)
        setUpIconRoundCornerProgressBar(satisfaction)
        setUpMoreProductsText()
    }

    private fun setUpMoreProductsText() {
        moreProducts.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer , SellerProductsFragment().newInstance(requireArguments().getInt("SELLER_ID")))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("SellerProductsFragment")
                .commit()
        }
    }

    private fun setUpTotalStatusText(totalPercentage: Float) {
        when(identifyStatus(totalPercentage)){
            BAD_SATISFACTION -> {
                totalSellerStatusText.text = "بد"
                totalSellerStatusText.setTextColor(Color.RED)
            }
            ORDINARY_SATISFACTION -> {
                totalSellerStatusText.text = "متوسط"
                totalSellerStatusText.setTextColor(ContextCompat.getColor(requireContext() , R.color.light_orange))
            }
            GOOD_SATISFACTION -> {
                totalSellerStatusText.text = "عالی"
                totalSellerStatusText.setTextColor(Color.GREEN)
            }
        }
    }

    private fun setUpTotalPercentageText(totalPercentage: Float) {
        totalPercentageText.text = "${String.format("%.1f" , totalPercentage)}%"
    }

    private fun setUpIconRoundCornerProgressBar(totalPercentage: Float) {
        roundedCornerProgressBar.progress = totalPercentage

        when(identifyStatus(totalPercentage)){
            BAD_SATISFACTION -> {
                roundedCornerProgressBar.progressColor = Color.RED
                roundedCornerProgressBar.iconImageResource = R.drawable.ic_baseline_sentiment_very_dissatisfied_24
                roundedCornerProgressBar.setIconBackgroundColor(Color.RED)
            }
            ORDINARY_SATISFACTION -> {
                roundedCornerProgressBar.progressColor = Color.YELLOW
                roundedCornerProgressBar.iconImageResource = R.drawable.ic_baseline_sentiment_satisfied_24
                roundedCornerProgressBar.setIconBackgroundColor(Color.YELLOW)
            }
            GOOD_SATISFACTION -> {
                roundedCornerProgressBar.progressColor = Color.GREEN
                roundedCornerProgressBar.iconImageResource = R.drawable.ic_baseline_sentiment_satisfied_alt_24
                roundedCornerProgressBar.setIconBackgroundColor(Color.GREEN)
            }
        }

    }

    private fun identifyStatus(satisfactionRate:Float):Int{
        return if (satisfactionRate < 30) {
            BAD_SATISFACTION
        } else if (satisfactionRate > 30 && satisfactionRate < 90) {
            ORDINARY_SATISFACTION
        } else {
            GOOD_SATISFACTION
        }
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "FullSellerInfoFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        roundedCornerProgressBar = view.findViewById(R.id.roundedCornerProgressBar)
        totalPercentageText = view.findViewById(R.id.totalPercentageText)
        totalSellerStatusText = view.findViewById(R.id.totalSellerStatusText)
        moreProducts = view.findViewById(R.id.moreProducts)
    }

    fun newInstance(sellerId: Int): FullSellerInfoFragment {
        val args = Bundle()
        args.putInt("SELLER_ID", sellerId)
        val fragment = FullSellerInfoFragment()
        fragment.arguments = args
        return fragment
    }
}