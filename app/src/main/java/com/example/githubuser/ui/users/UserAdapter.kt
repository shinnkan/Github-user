package com.example.githubuser.ui.users

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R
import com.example.githubuser.logic.model.Items
import com.example.githubuser.ui.userPage.UserPageActivity
import com.squareup.picasso.Picasso

class UserAdapter(private val activity: MainActivity, private val items: List<Items>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImage: ImageView = view.findViewById(R.id.userImage)
        val userName: TextView = view.findViewById(R.id.userName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val intent = Intent(parent.context, UserPageActivity::class.java).apply {
                putExtra("userName", items[position].login)
            }
            activity.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Picasso.get().load(item.imageUrl).resize(300, 300).into(holder.userImage)
        holder.userName.text = item.login
    }

    override fun getItemCount(): Int {
        return items.size
    }


}