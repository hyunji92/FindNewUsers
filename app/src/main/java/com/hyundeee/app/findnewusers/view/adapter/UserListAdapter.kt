package com.hyundeee.app.findnewusers.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hyundeee.app.findnewusers.R
import com.hyundeee.app.findnewusers.model.User
import kotlinx.android.synthetic.main.list_item.view.*

class UserListAdapter(var context: Context?, val items: ArrayList<User>) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserListViewHolder {
        return UserListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.run {
            items[position].let {
                bindView(it)
            }
        }
    }

    inner class UserListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(userListItem: User) {
            with(view) {
                user_name.text = userListItem.login
                user_name.text = userListItem.login
                user_repo_url.text = userListItem.repos_url

                Glide.with(context)
                        .load(userListItem.avatar_url)
                        .fitCenter()
                        .into(user_image)
                user_info_layout.setOnClickListener {

                }

            }
        }
    }
}
