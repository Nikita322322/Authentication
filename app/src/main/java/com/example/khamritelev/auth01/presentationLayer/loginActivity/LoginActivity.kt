package com.example.khamritelev.auth01.presentationLayer.loginActivity

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.khamritelev.auth01.R
import com.example.khamritelev.auth01.databinding.ActivityLoginBinding
import com.example.khamritelev.auth01.injection.presentationModule
import com.example.khamritelev.auth01.presentationLayer.mainActivity.MainActivity
import com.google.android.gms.common.AccountPicker
import org.koin.android.ext.android.inject
import org.koin.standalone.StandAloneContext.loadKoinModules


class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginBinding
    private val requestCode: Int = 123
    val presenter: LoginPresenterImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(listOf(presentationModule))

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.signInButton.setOnClickListener(View.OnClickListener { v ->
            run {
                val intent = AccountPicker.newChooseAccountIntent(null, null, arrayOf("com.google"),
                        false, null, null, null, null)
                startActivityForResult(
                        intent,
                        requestCode
                )
            }
        })
        presenter.onCreateView(this)
        presenter.checkToken()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == this.requestCode && resultCode == Activity.RESULT_OK) {
            val accountName = data!!.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
            presenter.getToken(accountName)
        }
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun startMainActivity(accountName: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.accountName), accountName)
        startActivity(intent)
        finish()
    }
}
