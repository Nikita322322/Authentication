package com.example.khamritelev.auth01.presentationLayer.loginActivity

import com.example.khamritelev.auth01.domainLayer.CheckTokenInteractor
import com.example.khamritelev.auth01.domainLayer.SignInInteractor
import com.example.khamritelev.auth01.presentationLayer.mvp.BasePresenter
import io.reactivex.functions.Consumer

class LoginPresenterImpl(private var checkTokenInteractor: CheckTokenInteractor, private var signInInteractor: SignInInteractor) : BasePresenter<LoginView>(), LoginPresenter {
    override fun getToken(account: String) {
        addDisposable(signInInteractor.execute(account).subscribe(Consumer { t -> if (!t.equals("")) mvpView!!.startMainActivity(t) }))
    }

    override fun checkToken() {
        addDisposable(checkTokenInteractor.execute(null).subscribe({ t ->
            run {
                if (!t.equals("")) {
                    mvpView!!.startMainActivity(t)
                } else {
                }
            }
        }))
    }
}