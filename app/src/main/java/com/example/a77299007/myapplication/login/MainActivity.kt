package com.example.a77299007.myapplication.login


import android.content.Intent
import com.example.a77299007.myapplication.R
import com.example.a77299007.myapplication.base.RxBaseActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : RxBaseActivity(){


    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }



    override fun initData() {
    }

    override fun configViews() {
        btn_request.setOnClickListener {

            startActivity(Intent(this,NewActivity::class.java))
        }
    }







}



