package com.hyundeee.app.findnewusers

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.hyundeee.app.findnewusers.common.UserData
import com.hyundeee.app.findnewusers.model.SearchResponse
import com.hyundeee.app.findnewusers.presenter.MainPresenter
import com.hyundeee.app.findnewusers.presenter.UserDataList
import com.hyundeee.app.findnewusers.view.FollowersFragment
import com.hyundeee.app.findnewusers.view.RepoFragment
import com.hyundeee.app.findnewusers.view.UserFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), UserDataList {

    private val presenter: MainPresenter<UserData> by inject()

    override fun searchGithubUser(searchWord: String) {
        if (searchWord.isNullOrBlank()) {
            userFragment.userAdapter.apply {
                items.clear()
                notifyDataSetChanged()
            }
        } else {
            presenter.getGithubUserList(searchWord)
        }
    }

    private val userFragment: UserFragment = UserFragment()
    private val repoFragment: RepoFragment = RepoFragment()
    private val followersFragment: FollowersFragment = FollowersFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager
    var active: Fragment = userFragment


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fragmentManager.beginTransaction().hide(active).show(userFragment).commit()
                active = userFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fragmentManager.beginTransaction().hide(active).show(repoFragment).commit()
                active = repoFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fragmentManager.beginTransaction().hide(active).show(followersFragment).commit()
                active = followersFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchView = menu?.findItem(R.id.search_bar)?.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let { searchGithubUser(s) }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter.userData = this

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fragmentManager.beginTransaction().add(R.id.main_container, followersFragment, "3").hide(followersFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, repoFragment, "2").hide(repoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, userFragment, "1").commit()
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {
        userFragment.userAdapter.apply {
            items.clear()
            items.addAll(storeResponse.items)
            notifyDataSetChanged()
        }
    }

    override fun onDataFailed() {
        Log.d("test", "onDataFailed ------")
        userFragment.userAdapter.apply {
            items.clear()
            notifyDataSetChanged()
        }
    }
}
