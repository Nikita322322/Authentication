package com.example.khamritelev.auth01.presentationLayer.mainActivity

import android.content.Context
import com.example.khamritelev.auth01.R
import com.example.khamritelev.auth01.domainLayer.GetInfoAboutAccountInteractor
import com.example.khamritelev.auth01.domainLayer.SignOutInteractor
import com.example.khamritelev.auth01.presentationLayer.mvp.BasePresenter
import io.reactivex.functions.Consumer

class MainActivityPresenterImpl(private var signOutInteractor: SignOutInteractor,
                                private var getInfoAboutAccountInteractor: GetInfoAboutAccountInteractor) :
        BasePresenter<MainActivityView>(), MainActivityPresenter {

    override fun logOut() {
        addDisposable(signOutInteractor.execute(null).subscribe(Consumer { t ->
            run {
                mvpView!!.startLoginActivity()
            }
        }))
    }

    override fun getInfo() {
        addDisposable(getInfoAboutAccountInteractor.execute(null).subscribe({ t ->
            run {
                if (mvpView != null && t!=null && t.email!=null) {
                    mvpView!!.setTextView(t.email!!)
                }
            }
        }, { t -> run {} }))
    }
}