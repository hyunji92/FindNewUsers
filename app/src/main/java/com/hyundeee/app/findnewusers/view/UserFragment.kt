package com.hyundeee.app.findnewusers.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyundeee.app.findnewusers.R
import com.hyundeee.app.findnewusers.model.User
import com.hyundeee.app.findnewusers.view.adapter.UserListAdapter
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    val items by lazy { ArrayList<User>() }
    val userAdapter by lazy { UserListAdapter(context, items) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false) as RecyclerView
        view.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = userAdapter
        }
        return view
    }
}
