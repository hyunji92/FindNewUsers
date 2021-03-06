package com.hyundeee.app.findnewusers.presenter

import android.annotation.SuppressLint
import com.hyundeee.app.findnewusers.api.GithubUserApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
* 여기에서 @inject가 constructor 앞에 있는 것은, 생성자가 클래스 생성 중에 모든 매개 변수 의존성을 모으도록 Dagger에 지시합니다.
* 생성자가 @inject 되었기 때문에 Dagger를 통해 MainPresenterImpl의 의존성을 가져옵니다.
*/
class MainPresenterImpl @Inject constructor(val view: MainPresenter.View, val client: GithubUserApiClient) : MainPresenter {

    @SuppressLint("CheckResult")
    override fun getGithubUserList(q: String) {
        client.userDataService.getUserData(q, "repositories", "desc")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view.onDataLoaded(it)},{view.onDataFailed()},{})
    }
    // 추후 Disposables로 관리 필요
}
