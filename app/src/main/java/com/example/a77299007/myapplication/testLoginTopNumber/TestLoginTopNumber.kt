package com.example.a77299007.myapplication.testLoginTopNumber

import com.example.a77299007.myapplication.R
import com.example.a77299007.myapplication.base.RxBaseActivity
import com.example.a77299007.myapplication.base.presenter.RxPresenter
import com.example.a77299007.myapplication.utils.AppCache

class TestLoginTopNumber :RxBaseActivity(),ItestToNumberView.TestToNumber{
    override fun ToNumberSuccess(testToNumber: List<List<String>>) {
        showToast(testToNumber.toString())
    }

    override fun ToNumberFailure(message: String) {
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_test_login_to_number
    }

    override fun configViews() {

    }

    private val itestToNumberPresenter by lazy {
        TestToNumberPresenter(this)
    }

    override fun bindRxPresenter(): RxPresenter<*>? {
        itestToNumberPresenter.attachView(this)
        return itestToNumberPresenter
    }

    override fun initData() {
        //这里请求的地方
        itestToNumberPresenter.TestToNumberQuest(AppCache.getCompanyId().toString(),"2018-11-8","2018-12-8", "1","15")
    }




}