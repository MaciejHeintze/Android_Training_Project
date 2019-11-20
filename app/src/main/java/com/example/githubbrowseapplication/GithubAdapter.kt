package com.example.githubbrowseapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubbrowseapplication.model.Owner
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

const val REPOSITORY_URL = "repo_url"

class GithubAdapter
    (
    private val dataList: MutableList<Owner>
    ) : RecyclerView.Adapter<GithubHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubHolder {
        context=parent.context
        return GithubHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.item_view,parent,false))
    }
    override fun getItemCount()= dataList.size

    override fun onBindViewHolder(holder: GithubHolder, position: Int) {
        val data = dataList[position]
        val userAvatarImageView = holder.itemView.user_avatar_id
        val userNameTextView = holder.itemView.user_name_id
        val userScoreTextView = holder.itemView.user_score_id
        val userUrlAddressTextView = holder.itemView.html_url_id
        val userTypeTextView = holder.itemView.user_type_id

        Picasso.get().load(data.avatarUrl).into(userAvatarImageView)

        val userName = "Login: ${data.login}"
        userNameTextView.text=userName

        val score = "${data.score}"
        userScoreTextView.text="Score: ${score}"

        val userUrlAdress = "${data.html_url}"
        userUrlAddressTextView.text=userUrlAdress

        val userType = "Type: ${data.type}"
        userTypeTextView.text=userType

        holder.itemView.setOnClickListener{
            val intent = Intent(context, RepoActivity::class.java)
            intent.putExtra(REPOSITORY_URL, userUrlAdress )
            startActivity(context,intent,null)
        }
    }
}