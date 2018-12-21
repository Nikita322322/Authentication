package com.example.khamritelev.auth01.injection

import com.example.khamritelev.auth01.dataLayer.RepositoryImpl
import com.example.khamritelev.auth01.dataLayer.localStore.LocalStore
import com.example.khamritelev.auth01.domainLayer.*
import com.example.khamritelev.auth01.presentationLayer.loginActivity.LoginPresenterImpl
import com.example.khamritelev.auth01.presentationLayer.mainActivity.MainActivityPresenterImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit


val presentationModule = applicationContext {

    bean("signInInteractor") { SignInInteractor(get("repository")) }
    bean("checkTokenInteractor") { CheckTokenInteractor(get("repository")) }
    bean("signOutInteractor") { SignOutInteractor(get("repository")) }
    bean("getInfoAboutAccountInteractor") { GetInfoAboutAccountInteractor(get("repository")) }

    bean { LoginPresenterImpl(get("checkTokenInteractor"), get("signInInteractor")) }
    bean { MainActivityPresenterImpl(get("signOutInteractor"), get("getInfoAboutAccountInteractor")) }
}

val appModule = applicationContext {
    bean("context") { androidApplication().applicationContext }
    bean { RefreshTokenInteractor(get("repository")) }
    bean("repository") { RepositoryImpl(get("localStore")) }
    bean("localStore") { LocalStore(get("context"), get("retrofit")) }
    bean("retrofit") {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com/oauth2/v1/")
                .build()
    }

}