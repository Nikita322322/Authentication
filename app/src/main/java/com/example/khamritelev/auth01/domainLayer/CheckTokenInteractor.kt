package com.example.khamritelev.auth01.domainLayer

import com.example.khamritelev.auth01.dataLayer.RepositoryImpl
import io.reactivex.Observable

class CheckTokenInteractor(private var repository: RepositoryImpl) : UseCase<Unit?, String>() {
    override fun buildUseCase(arg: Unit?): Observable<String> {
        return repository.checkToken()
    }
}