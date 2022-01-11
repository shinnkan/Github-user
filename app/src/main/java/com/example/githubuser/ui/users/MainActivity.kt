package com.example.githubuser.ui.users

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) }
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        userAdapter = UserAdapter(this, viewModel.items)
        recyclerView.adapter = userAdapter

        userSearch.setOnClickListener {
            val userName = searchUserEdit.text.toString()
            if (userName.isNotEmpty()) {
                viewModel.getUsers(userName)
            } else {
                recyclerView.visibility = View.GONE
                noData.visibility = View.GONE
                viewModel.items.clear()
                userAdapter.notifyDataSetChanged()
            }
        }

        viewModel.usersLiveData.observe(this, Observer { result ->
            val items = result.getOrNull()
            if (items != null && items.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                noData.visibility = View.GONE
                viewModel.items.clear()
                viewModel.items.addAll(items)
                userAdapter.notifyDataSetChanged()
            } else {
                recyclerView.visibility = View.GONE
                noData.visibility = View.VISIBLE
                viewModel.items.clear()
                userAdapter.notifyDataSetChanged()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

}