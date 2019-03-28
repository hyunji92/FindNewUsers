package com.hyundeee.app.findnewusers.presenter

import com.hyundeee.app.findnewusers.api.GithubUserApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
//여기에서 @inject가 constructor 앞에 있는 것은, 생성자가 클래스 생성 중에 모든 매개 변수 의존성을 모으도록 Dagger에 지시합니다.
//생성자가 @inject 되었기 때문에 Dagger를 통해 MainPresenterImpl의 의존성을 가져옵니다.
class MainPresenterImpl @Inject constructor(val view: MainPresenter.View, val client: GithubUserApiClient) : MainPresenter {
    override fun getGithubUserList(q: String) {
        client.userDataService.getUserData(q, "joined", "desc")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({},{},{})
    }

}
