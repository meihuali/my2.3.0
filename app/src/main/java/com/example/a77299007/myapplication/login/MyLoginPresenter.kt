package com.toogps.distributionsystem.ui.activity.homeLogin;

import com.example.a77299007.myapplication.base.presenter.RxPresenter

class MyLoginPresenter(loginView: ILoginView.View) : RxPresenter<ILoginView.View>(loginView),ILoginView.Presenter{

    override fun Login(name: String, psw: String,type: String) {

        super.execute(commonApi.login(name,psw,type),{
            result ->
            mView.LoginSoccss(result)
        },{
            message ->
            mView.OnError(message)
        },"登陆中···")


    }



}
