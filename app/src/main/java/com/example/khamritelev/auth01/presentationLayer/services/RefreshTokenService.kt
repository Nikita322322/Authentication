package com.example.khamritelev.auth01.presentationLayer.services

import android.app.job.JobParameters
import android.app.job.JobService
import com.example.khamritelev.auth01.domainLayer.RefreshTokenInteractor
import io.reactivex.functions.Consumer
import org.koin.android.ext.android.inject

class RefreshTokenService : JobService() {
    val refreshTokenInteractor: RefreshTokenInteractor by inject()
    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        refreshTokenInteractor.execute(null).subscribe(Consumer { t ->
            run {
                jobFinished(params, !t)
            }
        })
        return true
    }
}