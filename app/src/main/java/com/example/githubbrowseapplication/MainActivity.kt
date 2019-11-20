package com.example.githubbrowseapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.githubbrowseapplication.model.Github
import com.example.githubbrowseapplication.model.Owner
import kotlinx.android.synthetic.main.activity_main.*

const val GITHUB_URL = "https://api.github.com/search/users?q="

class MainActivity : AppCompatActivity() {

    private lateinit var githubAdapter: GithubAdapter
    private val dataList: MutableList<Owner> = mutableListOf()
    private var user: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        onSearchButtonClicked()
    }

    private fun onSearchButtonClicked(){
        search_user_button.setOnClickListener {
            clearData()
            hideKeyboard()
            initData()
        }
    }

    private fun setupRecyclerView(){
        githubAdapter=GithubAdapter(dataList)
        recycler_view_id.layoutManager= LinearLayoutManager(this)
        recycler_view_id.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view_id.adapter=githubAdapter
    }

    private fun initData(){
        user = search_user_edit_text.text.toString()
        AndroidNetworking.initialize(this)
        AndroidNetworking.get("${GITHUB_URL}${user}")
            .build()
            .getAsObject(Github::class.java, object : ParsedRequestListener<Github>{
                override fun onResponse(response: Github) {
                    dataList.addAll(response.items)
                    githubAdapter.notifyDataSetChanged()
                }
                override fun onError(anError: ANError?) {
                    Toast.makeText(applicationContext, "Something went wrong:${anError}",Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun clearData(){
        if (dataList.size > 0){
            dataList.clear()
        }
    }

    private fun AppCompatActivity.hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}
