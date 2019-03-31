package com.hyundeee.app.findnewusers.di

import com.hyundeee.app.findnewusers.model.SearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUserApiService {

    @GET("/search/users")
    fun getUserData(
            @Query("q") searchword: String,
            @Query("sort") sort: String,
            @Query("order") order: String
    ): Single<SearchResponse>
}
