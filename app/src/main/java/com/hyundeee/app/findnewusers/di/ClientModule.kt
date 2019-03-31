package com.hyundeee.app.findnewusers.di

import com.hyundeee.app.findnewusers.api.GithubUserApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/*
* @Provide : 모듈 안에서 해당 어노테이션이 달린 메서드를 정의합니다.
* 해당 어노테이션이 달린 메서드가 Dagger 가 어떻게 의존성에 맞게 객체를 만들고 제공하는지 알려줍니다.
*
* @Module : 모듈들은 의존성을 제공하는 메서드들을 가진 클래스입니다. 의존성을 제공하는 클래스를 정의하고 @Module 어노테이션을 답니다.
* 그러면 Dagger 는 클래스 인스턴스를 만들 때 의존성을 만족시키기 위한 정보를 찾을 수 있습니다.
*/
@Module
class ClientModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }


    @Provides
    fun provideGithubUserApiClient(): GithubUserApiClient {
        return GithubUserApiClient(provideOkHttpClient())
    }

}
