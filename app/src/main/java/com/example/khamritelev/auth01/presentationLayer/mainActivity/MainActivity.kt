package com.example.khamritelev.auth01.presentationLayer.mainActivity

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.databinding.DataBindingUtil
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.khamritelev.auth01.presentationLayer.loginActivity.LoginActivity
import com.example.khamritelev.auth01.R
import com.example.khamritelev.auth01.databinding.ActivityMainBinding
import com.example.khamritelev.auth01.presentationLayer.services.RefreshTokenService
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), MainActivityView {
    val periodOfStartingService = 1000L//interval for which RefreshTokenService will start
    lateinit var binding: ActivityMainBinding
    val presenter: MainActivityPresenterImpl by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.logOutButton.setOnClickListener(View.OnClickListener { v ->
            run {
                presenter.logOut()
            }
        })
        binding.getInfoButton.setOnClickListener(View.OnClickListener { v ->
            run {
                presenter.getInfo()
            }
        })
        if (intent != null) {
            binding.textView.setText(intent.getStringExtra(resources.getString(R.string.accountName)))
            presenter.getInfo()
        }
        presenter.onCreateView(this)
        val jobScheduler = applicationContext.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(applicationContext, RefreshTokenService::class.java)
        val jobInfo = JobInfo.Builder(1, componentName).setPeriodic(periodOfStartingService).setRequiresCharging(true).build()
        jobScheduler.schedule(jobInfo)
    }

    override fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
        finish()
    }

    override fun setTextView(text: String) {
        binding.textView.text = text
    }

    fun String.last(): String = this.get(this.length - 1).toString()


}
