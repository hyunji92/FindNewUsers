package com.hyundeee.app.findnewusers.api

import com.hyundeee.app.findnewusers.data.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUserApiService {

    @GET("/search/users")
    fun getUserData(
            @Query("q") searchword: String,
            @Query("sort") sort: String,
            @Query("order") order: String
    ): Observable<SearchResponse>
}
