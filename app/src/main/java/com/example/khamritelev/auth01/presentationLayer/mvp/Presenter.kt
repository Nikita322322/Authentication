package com.example.khamritelev.auth01.presentationLayer.mvp

interface Presenter<V : MvpView> {
    fun onCreateView(view: V)
    fun detachView()
}