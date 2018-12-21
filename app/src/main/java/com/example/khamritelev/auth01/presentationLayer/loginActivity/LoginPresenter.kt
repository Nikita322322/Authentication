package com.example.khamritelev.auth01.presentationLayer.loginActivity

import com.example.khamritelev.auth01.presentationLayer.mvp.Presenter

interface LoginPresenter : Presenter<LoginView> {
    fun getToken(account: String)
    fun checkToken()
}