package com.example.khamritelev.auth01.domainLayer

import com.example.khamritelev.auth01.dataLayer.RepositoryImpl
import io.reactivex.Observable

class RefreshTokenInteractor(private var repository: RepositoryImpl) : UseCase<Unit?, Boolean>() {
    override fun buildUseCase(arg: Unit?): Observable<Boolean> {
        return repository.refreshToken()
    }
}