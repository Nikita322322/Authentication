package com.example.khamritelev.auth01.presentationLayer.loginActivity

import com.example.khamritelev.auth01.presentationLayer.mvp.MvpView

interface LoginView : MvpView {
    fun startMainActivity(accountName: String)
}