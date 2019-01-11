package com.toogps.distributionsystem.ui.activity.homeLogin

import com.example.a77299007.myapplication.base.baseView.IBaseView
import com.example.a77299007.myapplication.login.LoginBen


interface ILoginView {
    interface View : IBaseView {
        fun LoginSoccss(data: LoginBen)

        fun OnError(msg:String)
    }
    interface Presenter{
        fun Login(name: String, psw: String,type:String)
    }
}