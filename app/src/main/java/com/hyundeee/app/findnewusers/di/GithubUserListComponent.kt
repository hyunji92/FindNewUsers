package com.hyundeee.app.findnewusers.di

import com.hyundeee.app.findnewusers.MainActivity
import dagger.Component

/*
* @Component: 컴포넌트는 @Inject 와 @Module 사이 다리이며 의존성을 주입하는 역할을 합니다.
* 컴포넌트는 미리 정의한 모든 타입의 인스턴스를 줍니다.
*
* @Component 어노테이션은 인터페이스에다만 달아야합니다 그리고 컴포넌트를 구성하는 모든 @Module 이 달린 클래스 목록을 적어야합니다.
* 컴포넌트에서 사용하는 모듈들중 하나라도 없다면 컴파일 타임에 에러를 만듭니다.
* 모든 컴포넌트들은 컴포넌트에 포함된 모듈들을 통해 의존성의 범위를 알 수 있습니다.
* */
@Component(modules = [GithubUserListModule::class])
interface GithubUserListComponent {
    fun inject(Activity: MainActivity)
}
