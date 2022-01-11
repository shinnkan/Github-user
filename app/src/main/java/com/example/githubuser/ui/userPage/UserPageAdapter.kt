package com.example.githubuser.ui.userPage

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R
import com.example.githubuser.logic.model.UnforkedRepoResponse

class UserPageAdapter(private val unforkedRepos: List<UnforkedRepoResponse>) :
    RecyclerView.Adapter<UserPageAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repoName: TextView = view.findViewById(R.id.repoName)
        val description: TextView = view.findViewById(R.id.description)
        val stars: TextView = view.findViewById(R.id.stars)
        val language: TextView = view.findViewById(R.id.language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_repos, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            CustomTabsIntent.Builder().build()
                .launchUrl(parent.context, Uri.parse(unforkedRepos[position].url))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unforkedRepo = unforkedRepos[position]
        holder.repoName.text = unforkedRepo.name
        holder.description.text = unforkedRepo.description
        holder.stars.text = unforkedRepo.stars.toString()
        holder.language.text = unforkedRepo.language
    }

    override fun getItemCount(): Int {
        return unforkedRepos.size
    }
}