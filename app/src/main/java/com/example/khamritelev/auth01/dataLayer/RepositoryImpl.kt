package com.example.khamritelev.auth01.dataLayer

import com.example.khamritelev.auth01.dataLayer.localStore.LocalStore
import com.example.khamritelev.auth01.dataLayer.models.UserInfoModel
import io.reactivex.Observable

class RepositoryImpl(private var localStore: LocalStore) : Repository {
    override fun refreshToken(): Observable<Boolean> {
        return localStore.refreshToken()
    }

    override fun getInfo(): Observable<UserInfoModel> {
        return localStore.getAccount()
    }

    override fun logOut(): Observable<Boolean> {
        return localStore.logOut()
    }

    override fun checkToken(): Observable<String> {
        return localStore.checkToken()
    }

    override fun getToken(account: String): Observable<String> {
        return localStore.getToken(account)
    }


}