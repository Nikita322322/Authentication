package com.example.khamritelev.auth01

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.v7.app.AppCompatActivity
import com.example.khamritelev.auth01.dataLayer.localStore.LocalStore
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class CheckTokenTest() {
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
        val s: String = localStore.getToken();
        Assert.assertNotEquals("", s)
    }
}