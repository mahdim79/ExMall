package com.dust.exmall.fragments.others

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView

class WebPageFragment() : Fragment() {
    private lateinit var backImage:ImageView
    private lateinit var shareImage:ImageView
    private lateinit var titleText:CTextView
    private lateinit var mainWebView:WebView
    private lateinit var loadingProgressBar:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_webpage , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
        setUpBackImage()
        setUpShareImage()
        setUpTitleText()
        setUpWebView()
    }

    private fun setUpWebView() {
        mainWebView.settings.javaScriptEnabled = true
        mainWebView.webViewClient = MainWebViewClient()
        mainWebView.loadUrl(requireArguments().getString("URL")!!)
    }

    private fun setUpTitleText() {
        titleText.text = requireArguments().getString("TITLE")
    }

    private fun setUpShareImage() {
        shareImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT , requireArguments().getString("URL"))
            startActivity(Intent.createChooser(intent , "به اشتراک گذاری بوسیله ..."))
        }
    }

    private fun setUpBackImage() {
        backImage.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack( "WebPageFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun setUpViews(view: View) {
        backImage = view.findViewById(R.id.backImage)
        shareImage = view.findViewById(R.id.shareImage)
        titleText = view.findViewById(R.id.titleText)
        mainWebView = view.findViewById(R.id.mainWebView)
        loadingProgressBar = view.findViewById(R.id.loadingProgressBar)
    }

    inner class MainWebViewClient() : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            loadingProgressBar.visibility = View.GONE
            super.onPageFinished(view, url)
        }
    }

    fun newInstance(title: String , url:String): WebPageFragment {
        val args = Bundle()
        args.putString("TITLE", title)
        args.putString("URL", url)
        val fragment = WebPageFragment()
        fragment.arguments = args
        return fragment
    }
}