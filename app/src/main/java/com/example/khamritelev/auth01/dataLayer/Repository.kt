package com.example.khamritelev.auth01.dataLayer

import com.example.khamritelev.auth01.dataLayer.models.UserInfoModel
import io.reactivex.Observable

interface Repository {
    fun getToken(account: String): Observable<String>
    fun checkToken(): Observable<String>
    fun logOut(): Observable<Boolean>
    fun getInfo(): Observable<UserInfoModel>
    fun refreshToken(): Observable<Boolean>
}