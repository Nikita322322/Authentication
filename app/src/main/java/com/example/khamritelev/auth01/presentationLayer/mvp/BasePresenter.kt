package com.example.khamritelev.auth01.presentationLayer.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : MvpView> : Presenter<V> {
    private val compositeDisposable = CompositeDisposable()

    protected var mvpView: V? = null
    override fun onCreateView(view: V) {
        mvpView = view
    }

    protected fun onViewAttached() {

    }

    override fun detachView() {
        mvpView = null
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}