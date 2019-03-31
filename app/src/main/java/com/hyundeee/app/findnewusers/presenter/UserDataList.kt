package com.hyundeee.app.findnewusers.presenter

import com.hyundeee.app.findnewusers.common.UserData
import com.hyundeee.app.findnewusers.model.SearchResponse

interface UserDataList : UserData{
    fun onDataLoaded(storeResponse: SearchResponse)
    fun onDataFailed()

    fun searchGithubUser(searchWord: String)
}
