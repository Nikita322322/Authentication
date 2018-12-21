package com.example.khamritelev.auth01.domainLayer

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class UseCase<Input, Output> {

    protected abstract fun buildUseCase(arg: Input): Observable<Output>

    fun execute(arg: Input): Observable<Output> {
        return buildUseCase(arg)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }
}