package com.example.khamritelev.auth01.dataLayer.localStore

import android.accounts.Account
import android.annotation.SuppressLint
import android.content.Context
import com.example.khamritelev.auth01.dataLayer.backend.Backend
import com.example.khamritelev.auth01.dataLayer.models.UserInfoModel
import com.google.android.gms.auth.GoogleAuthUtil
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import retrofit2.Retrofit
import java.util.*


class LocalStore(context: Context, private var retrofit: Retrofit) {
    private val G_PLUS_SCOPE = "oauth2:https://www.googleapis.com/auth/plus.me"
    private val USERINFO_SCOPE = "https://www.googleapis.com/auth/userinfo.profile"
    private val EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email"
    private val SCOPES = "$G_PLUS_SCOPE $USERINFO_SCOPE $EMAIL_SCOPE"
    private val PREFS_FILENAME = "sPref"
    private val TOKEN = "token"
    private val DATE = "date"
    private val ACCOUNT_NAME = "account_name"
    private var contex: Context = context

    //access token
    fun getToken(accountName: String): Observable<String> {
        return Observable.create { emitter ->
            run {
                try {
                    val accountDetails = Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE)
                    val token: String = GoogleAuthUtil.getToken(contex, accountDetails, SCOPES);
                    saveProperty(token, accountName)
                    emitter.onNext(accountName)
                } catch (e: Exception) {
                    emitter.onError(e)
                } finally {
                    emitter.onComplete()
                }
            }
        }
    }

    private fun saveProperty(token: String, accountName: String) {
        val sharedPreferences = contex.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN, token)
        val date = Date()
        editor.putLong(DATE, date.time)
        editor.putString(ACCOUNT_NAME, accountName)
        editor.apply()
    }

    fun logOut(): Observable<Boolean> {
        return Observable.create { emitter ->
            run {
                try {
                    saveProperty("", "")
                    emitter.onNext(true)
                } catch (e: Exception) {
                    emitter.onError(e)
                } finally {
                    emitter.onComplete()
                }
            }
        }
    }

    fun test(): Int {
        return 1
    }


     fun getToken(): String {
        val sharedPreferences = contex.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(TOKEN, "")
    }


    private fun getAccountName(): String {
        val sharedPreferences = contex.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(ACCOUNT_NAME, "")
    }

    fun checkToken(): Observable<String> {
        return Observable.create { emitter ->
            run {
                try {
                    if (getToken().equals("")) {
                        emitter.onNext(getAccountName())
                    } else {
                        emitter.onNext(getAccountName())
                    }
                } catch (e: Exception) {
                    emitter.onError(e)
                } finally {
                    emitter.onComplete()
                }
            }
        }
    }

    fun refreshToken(): Observable<Boolean> {
        return Observable.create { emitter ->
            run {
                try {
                    val accountName: String = getAccountName();
                    if (!accountName.equals("")) {
                        getToken(getAccountName()).subscribe(Consumer { t -> emitter.onNext(true) })
                    } else {
                        emitter.onNext(false)
                    }
                } catch (e: Exception) {
                    emitter.onError(e)
                } finally {
                    emitter.onComplete()
                }
            }
        }
    }

    fun getAccount(): Observable<UserInfoModel> {
        return retrofit.create(Backend::class.java).getInfo(getToken()).doOnError { t ->
            run {
                refreshToken().subscribe()
            }
        }
    }

}