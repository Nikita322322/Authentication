package com.example.khamritelev.auth01.domainLayer

import android.accounts.Account
import android.content.Context
import com.example.khamritelev.auth01.dataLayer.Repository
import com.example.khamritelev.auth01.dataLayer.RepositoryImpl
import com.google.android.gms.auth.GoogleAuthUtil
import io.reactivex.Observable


class SignInInteractor(private var repository: RepositoryImpl) : UseCase<String, String>() {
    override fun buildUseCase(arg: String): Observable<String> {
        return repository.getToken(arg);
    }


}