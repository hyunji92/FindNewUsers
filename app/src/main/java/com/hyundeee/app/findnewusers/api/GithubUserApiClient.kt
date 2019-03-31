package com.hyundeee.app.findnewusers.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserApiClient @Inject constructor(provideOkHttpClient: OkHttpClient) {
    var baseUrl = "https://api.github.com"
    val userDataService: GithubUserApiService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        userDataService = retrofit.create(GithubUserApiService::class.java)
    }
}
