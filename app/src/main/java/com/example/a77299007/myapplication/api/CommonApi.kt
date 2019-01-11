package com.example.a77299007.myapplication.api

import com.example.a77299007.myapplication.base.baseBean.BaseBean
import com.example.a77299007.myapplication.base.baseBean.BaseBeanTwo
import com.example.a77299007.myapplication.login.LoginBen
import com.example.a77299007.myapplication.testLoginTopNumber.TestToNumberBean

import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface CommonApi {


    /**
     * 登录
     *
     * @param account  账号
     * @param pwd      密码
     * @param platForm 平台(Android)
     * @return
     */
    @POST("NonAuth/LoginOld")
    @FormUrlEncoded
    fun login(
        @Field("Account") account: String,
        @Field("Password") pwd: String,
        @Field("PlatForm") platForm: String
    ):Flowable<BaseBean<LoginBen>>


    /**
     * 出车看板
     * */
    @POST("Order/GetCarDispatchBoardOld")
    @FormUrlEncoded
    fun testToNumber(
        @Field("CompanyId") CompanyId:String ,
        @Field("StartTime") StartTime:String ,
        @Field("EndTime") EndTime:String ,
        @Field("PageNumber") PageNumber:String ,
        @Field("PageSize") PageSize:String
    ):Flowable<BaseBean<List<List<String>>>>




}