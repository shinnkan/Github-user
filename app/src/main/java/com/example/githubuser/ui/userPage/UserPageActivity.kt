package com.example.githubuser.ui.userPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_info.*

class UserPageActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(UserPageViewModel::class.java) }
    lateinit var adapter: UserPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = UserPageAdapter(viewModel.unforkedRepos)
        recyclerView.adapter = adapter

        val userName = intent.getStringExtra("userName")
        if (userName != null) {
            viewModel.getUserRepo(userName)
        }

        viewModel.userPageLiveData.observe(this, Observer { result ->
            val repo = result.getOrNull()
            if (repo != null) {
                Picasso.get().load(repo.imageUrl).resize(400, 400).into(userImage)
                userPageName.text = repo.login
                fullName.text = repo.name
                followers.text = repo.followers.toString()
                followings.text = repo.following.toString()
                if (userName != null) {
                    viewModel.getUserRepos(userName)
                }
            }
        })

        viewModel.userReposLiveData.observe(this, Observer { result ->
            val repos = result.getOrNull()
            if (repos != null && repos.isNotEmpty()) {
                viewModel.unforkedRepos.clear()
                for (unforkedRepo in repos) {
                    if (!unforkedRepo.fork) viewModel.unforkedRepos.add(unforkedRepo)
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

}