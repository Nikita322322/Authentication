package com.example.khamritelev.auth01

import android.support.test.InstrumentationRegistry
import com.example.khamritelev.auth01.dataLayer.localStore.LocalStore
import com.example.khamritelev.auth01.dataLayer.models.UserInfoModel
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GetAccountInfoTest {
    lateinit var localStore: LocalStore
    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com/oauth2/v1/")
                .build()
        localStore = LocalStore(InstrumentationRegistry.getTargetContext(), retrofit)
    }

    @Test
    fun test() {
        val testSubscriber = TestObserver<UserInfoModel>()
        localStore.getAccount().subscribe(testSubscriber)
        testSubscriber.assertEmpty()
        testSubscriber.assertComplete()
    }
}