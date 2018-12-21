package com.example.khamritelev.auth01.domainLayer

import com.example.khamritelev.auth01.dataLayer.RepositoryImpl
import com.example.khamritelev.auth01.dataLayer.models.UserInfoModel
import io.reactivex.Observable

class GetInfoAboutAccountInteractor(private var repository: RepositoryImpl) : UseCase<Unit?, UserInfoModel>() {
    override fun buildUseCase(arg: Unit?): Observable<UserInfoModel> {
        return repository.getInfo()
    }
}