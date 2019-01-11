package com.example.a77299007.myapplication.testLoginTopNumber

import com.example.a77299007.myapplication.base.presenter.RxPresenter

class TestToNumberPresenter(testToNumber:ItestToNumberView.TestToNumber):RxPresenter<ItestToNumberView.TestToNumber>(testToNumber),ItestToNumberView.TestToNumberPresenter {
    override fun TestToNumberQuest(
        CompanyId: String,
        StartTime: String,
        EndTime: String,
        PageNumber: String,
        PageSize: String
    ) {
       super.execute(commonApi.testToNumber(CompanyId,StartTime,EndTime,PageNumber,PageSize),{
           result ->
              mView.ToNumberSuccess(result)
       },{
           mView.ToNumberFailure(it.toString())
       },"加载中···")
    }
}