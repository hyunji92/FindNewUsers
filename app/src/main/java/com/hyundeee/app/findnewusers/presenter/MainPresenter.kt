package com.hyundeee.app.findnewusers.presenter

import com.hyundeee.app.findnewusers.model.SearchResponse

interface MainPresenter {
    fun getGithubUserList(q: String)

    interface View{
        fun onDataLoaded(storeResponse: SearchResponse)
        fun onDataFailed()
        fun searchGithubUser(searchWord: String)
    }
}
