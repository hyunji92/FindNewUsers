package com.hyundeee.app.findnewusers.di

import com.hyundeee.app.findnewusers.presenter.MainPresenter
import com.hyundeee.app.findnewusers.presenter.MainPresenterImpl
import org.koin.core.module.Module
import org.koin.dsl.module


val userlistModule: Module = module {
    factory {
        MainPresenterImpl(get()) as MainPresenter<*>
    }

}
