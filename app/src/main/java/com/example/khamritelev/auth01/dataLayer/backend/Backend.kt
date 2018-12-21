package com.example.khamritelev.auth01.dataLayer.backend

import com.example.khamritelev.auth01.dataLayer.models.UserInfoModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Backend {
    @GET("userinfo?alt=json")
    fun getInfo(@Query("access_token") token: String): Observable<UserInfoModel>
}