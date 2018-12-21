package com.example.khamritelev.auth01.presentationLayer.mainActivity

import com.example.khamritelev.auth01.presentationLayer.mvp.Presenter

interface MainActivityPresenter : Presenter<MainActivityView> {
    fun logOut();
    fun getInfo()
}