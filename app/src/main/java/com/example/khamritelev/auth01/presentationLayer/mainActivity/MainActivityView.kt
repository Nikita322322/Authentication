package com.example.khamritelev.auth01.presentationLayer.mainActivity

import com.example.khamritelev.auth01.presentationLayer.mvp.MvpView

interface MainActivityView : MvpView {
    fun setTextView(text: String)
    fun startLoginActivity()
}