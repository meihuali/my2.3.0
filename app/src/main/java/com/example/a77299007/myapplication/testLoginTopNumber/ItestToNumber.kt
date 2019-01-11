package com.example.a77299007.myapplication.testLoginTopNumber

import com.example.a77299007.myapplication.base.baseView.IBaseView

interface ItestToNumberView {

    interface TestToNumber:IBaseView{
        fun ToNumberSuccess(testToNumber: List<List<String>>)

        fun ToNumberFailure(message:String)
    }

    interface TestToNumberPresenter{
        fun TestToNumberQuest(CompanyId:String,StartTime:String,EndTime:String,PageNumber:String,PageSize:String)
    }


}