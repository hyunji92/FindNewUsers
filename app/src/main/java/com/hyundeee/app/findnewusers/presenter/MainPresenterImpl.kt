package com.hyundeee.app.findnewusers.presenter

import com.hyundeee.app.findnewusers.di.GithubUserApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl(val apiService: GithubUserApiService) : MainPresenter<UserDataList>() {

    override fun getGithubUserList(q: String) {
        compositeDisposable?.add(apiService.getUserData(q, "repositories", "desc")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userData?.onDataLoaded(it) }, { userData?.onDataFailed() }))
    }
}
