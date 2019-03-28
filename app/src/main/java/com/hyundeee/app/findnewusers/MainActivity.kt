package com.hyundeee.app.findnewusers

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.hyundeee.app.findnewusers.data.SearchResponse
import com.hyundeee.app.findnewusers.di.DaggerGithubUserListComponent
import com.hyundeee.app.findnewusers.di.GithubUserListModule
import com.hyundeee.app.findnewusers.presenter.MainPresenter
import com.hyundeee.app.findnewusers.view.FollowersFragment
import com.hyundeee.app.findnewusers.view.RepoFragment
import com.hyundeee.app.findnewusers.view.UserFragment
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.View {

    @Inject
    lateinit var presenter: MainPresenter
    val searchSubject: PublishSubject<String> = PublishSubject.create()

    override fun onDataLoaded(storeResponse: SearchResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchGithubUser(searchWord: String) {
        if (searchWord.isNullOrBlank()){

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
                active = userFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        searchSubject.debounce(2000, TimeUnit.MILLISECONDS).subscribe { searchGithubUser(it) }

        var component = DaggerGithubUserListComponent.builder()
                .githubUserListModule(GithubUserListModule(this))
                .build()
        component.inject(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fragmentManager.beginTransaction().add(R.id.main_container, followersFragment, "3").hide(followersFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, repoFragment, "2").hide(repoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, userFragment, "1").commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchView = menu?.findItem(R.id.search_bar)?.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let { searchSubject.onNext(it) }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let { searchSubject.onNext(it) }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}
