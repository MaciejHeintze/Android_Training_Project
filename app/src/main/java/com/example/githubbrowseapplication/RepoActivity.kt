package com.example.githubbrowseapplication

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity : AppCompatActivity() {

    private var REPO_URL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        setupActionBar()
        setupWebView()
    }

    private fun setupWebView(){
        REPO_URL = intent.getStringExtra(REPOSITORY_URL)
        web_view_id.webViewClient = MyWebViewClient(this)
        web_view_id.loadUrl(REPO_URL)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupActionBar(){
        val actionBar = supportActionBar
        actionBar!!.title="User repository"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        var webView = web_view_id
        if(webView.canGoBack()){
            webView.goBack()
        }else {
            super.onBackPressed()
        }
    }
    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString()
            view?.loadUrl(url)
            return true
        }
        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }
        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(activity, "Error! $error", Toast.LENGTH_SHORT).show()
        }
    }
}
