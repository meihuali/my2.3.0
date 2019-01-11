package com.example.a77299007.myapplication.utils

import com.example.a77299007.myapplication.App
import com.example.a77299007.myapplication.login.LoginBen


/**
 * Author: zlh
 * Date: 2018/8/2
 * Description:
 */

//统一管理缓存
object AppCache {

    //是否是第一次安装，第一次则显示引导页
    fun setIsSkipGuide() {
        SPUtil.saveData(App.getsInstance(), "isSkipGuide", true)
    }

/*    fun getIsSkipGuide(): Boolean {
        return SPUtil.getData(App.getsInstance(), "isSkipGuide", Boolean::class.java)!!
    }*/

    //登录数据缓存
    fun setLoginResult(result: LoginBen) {
        SPUtil.saveData(App.getsInstance(), "loginResult", result)
    }

    fun getLoginResult(): LoginBen? {
        return SPUtil.getData(App.getsInstance(), "loginResult", LoginBen::class.java)
    }

    //功能模块缓存
/*    fun setFunctionResult(result: FunctionListResult) {
        SPUtil.saveData(BaseApplication.getContext()!!, "functionListResult", result)
    }*/
    //功能模块缓存2
/*    fun setFunctionResult(result: FunctionBean) {
        SPUtil.saveData(BaseApplication.getContext()!!, "functionBen", result)
    }*/

/*    fun getFunctionResult(): FunctionListResult? {
        return SPUtil.getData(BaseApplication.getContext()!!, "functionListResult", FunctionListResult::class.java)
    }*/

    fun getCompanyId(): Int {
        if (getLoginResult() != null) {
            return getLoginResult()!!.companyId
        }
        return 0
    }

    fun getUserId(): Int {
        if (getLoginResult() != null) {
            return getLoginResult()!!.id
        }
        return 0
    }

    //清楚所有数据
    fun clearCache() {
        SPUtil.deleteData(App.getsInstance())
    }

    //清楚本账号的数据
    fun clearAccountCache() {
        SPUtil.dellDataString(App.getsInstance(),"loginResult")
        SPUtil.dellDataString(App.getsInstance(),"functionListResult")
    }
}