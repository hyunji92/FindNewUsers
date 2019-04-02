package com.hyundeee.app.findnewusers.presenter

import com.hyundeee.app.findnewusers.common.UserData
import io.reactivex.disposables.CompositeDisposable

abstract class MainPresenter<U : UserData>   {

    protected var compositeDisposable: CompositeDisposable? = null

    var userData: U? = null
        set(value) {
            if (this.userData !== value) {
                field = value
                compositeDisposable = CompositeDisposable()
            }
        }
    abstract fun getGithubUserList(q: String)
}
