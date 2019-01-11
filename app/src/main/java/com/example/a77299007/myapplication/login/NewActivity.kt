package com.example.a77299007.myapplication.login

import android.content.Intent
import com.example.a77299007.myapplication.R
import com.example.a77299007.myapplication.base.RxBaseActivity
import com.example.a77299007.myapplication.base.presenter.RxPresenter
import com.example.a77299007.myapplication.testLoginTopNumber.TestLoginTopNumber
import com.example.a77299007.myapplication.utils.AppCache
import com.toogps.distributionsystem.ui.activity.homeLogin.ILoginView
import com.toogps.distributionsystem.ui.activity.homeLogin.MyLoginPresenter
import kotlinx.android.synthetic.main.activit_new.*

class NewActivity :RxBaseActivity(), ILoginView.View  {



    override fun getLayoutResId(): Int {
        return R.layout.activit_new
    }

    override fun configViews() {
        btn_testLogin.setOnClickListener {
            startActivity(Intent(this,TestLoginTopNumber::class.java))
        }
    }

      private val loginPresenter by lazy {
          MyLoginPresenter(this)
      }

    override fun bindRxPresenter(): RxPresenter<*>? {
        loginPresenter.attachView(this)
        return loginPresenter
    }


    override fun initData() {
        //这里调用网络请求
        loginPresenter.Login("17620193389","123456","Android")
    }

    override fun OnError(msg: String ) {

    }

    override fun LoginSoccss(data: LoginBen) {
        tv_body.text = data.toString()
        AppCache.setLoginResult(data)

    }


}