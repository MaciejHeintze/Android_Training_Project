package com.example.githubbrowseapplication

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubbrowseapplication.model.Owner


class GithubHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private fun AdapterView.OnItemClickListener.onItemClick(owner: Owner) {
    onItemClick(owner)
}
