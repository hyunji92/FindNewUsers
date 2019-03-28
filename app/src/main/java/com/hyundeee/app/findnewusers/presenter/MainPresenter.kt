package com.hyundeee.app.findnewusers.presenter

import com.hyundeee.app.findnewusers.data.SearchResponse

interface MainPresenter {
    fun getGithubUserList(q: String)

    interface View{
        fun onDataLoaded(storeResponse: SearchResponse)
        fun onDataFailed()
        fun onDataComplete()
        fun searchGithubUser(searchWord: String)
    }
}
