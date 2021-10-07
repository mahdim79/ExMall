package com.dust.exmall.fragments.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.dust.exmall.R
import com.dust.exmall.adapters.viewpager.ProductImagesAdapter
import com.dust.exmall.animation.Animations
import com.dust.exmall.customviews.CTextView
import com.squareup.picasso.Picasso

class ProductFullImagesFragment(var listImages: List<String>, var position: Int) : Fragment() {

    private lateinit var imageClose: ImageView
    private lateinit var imageViewPager: ViewPager
    private lateinit var counterText: CTextView
    private lateinit var selectionItemContainer: LinearLayout
    private lateinit var imageScrollView: HorizontalScrollView
    private val animations = Animations()

    private val selectionItems = arrayListOf<View>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_full_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpCloseImage()
        setUpImageSelector()
        setUpImageViewPager()
        imageClose.performClick()
    }

    private fun setUpCloseImage() {
        imageClose.setOnClickListener {
            imageClose.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack(
                    "ProductFullImagesFragment",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }
        }
    }

    private fun setUpImageSelector() {
        for (i in listImages.indices) {
            val layout = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_image_selector, null, false)

            // setImageOnEachLayout
            val image = layout.findViewById<ImageView>(R.id.image)
            Picasso.get().load(listImages[i]).into(image)

            selectionItems.add(layout)
            layout.setOnClickListener {
                if (imageViewPager.currentItem != i) {
                    removeOtherIndicators()
                    layout.findViewById<View>(R.id.indexView).visibility = View.VISIBLE
                    imageViewPager.currentItem = i
                }
            }
            selectionItemContainer.addView(layout)
        }

        counterText.text = "${(position + 1)}/${listImages.size}"
        selectionItems[0].findViewById<View>(R.id.indexView).visibility = View.VISIBLE
        selectionItems.forEach {
            it.setOnTouchListener { v, motionEvent ->
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
    }

    private fun removeOtherIndicators() {
        selectionItems.forEach {
            it.findViewById<View>(R.id.indexView).visibility = View.INVISIBLE
        }
    }

    private fun setUpImageViewPager() {
        imageViewPager.adapter =
            ProductImagesAdapter(requireActivity().supportFragmentManager, listImages, true)

        imageViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                counterText.text = "${(position + 1)}/${listImages.size}"
                selectItemIndicator()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        imageViewPager.currentItem = position

    }

    private fun selectItemIndicator() {
        removeOtherIndicators()
        selectionItems[imageViewPager.currentItem].findViewById<View>(R.id.indexView).visibility =
            View.VISIBLE
        imageScrollView.scrollTo(selectionItems[0].measuredWidth * imageViewPager.currentItem, 0)
    }

    private fun setUpViews(view: View) {
        imageClose = view.findViewById(R.id.imageClose)
        imageViewPager = view.findViewById(R.id.imageViewPager)
        counterText = view.findViewById(R.id.counterText)
        selectionItemContainer = view.findViewById(R.id.selectionItemContainer)
        imageScrollView = view.findViewById(R.id.imageScrollView)
    }
}