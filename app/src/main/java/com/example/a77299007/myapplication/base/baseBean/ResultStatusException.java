package com.example.a77299007.myapplication.base.baseBean;

/**
 * Created by wsl on 2017/3/29.
 */

public class ResultStatusException extends Exception {
    public static final int SERVER_FAIL = 0;
    public static final int SERVER_SUCCESS = 1;
    public static final int DATA_EMPTY = 2;
    public static final int DATA_NO_ENCRYPT = 3;
    public static final int TOKEN_INVALID = 4;
    public static final int CREATE_TEMP_FILE_FAIL = 5;


    private int errCode = 0;    //0,1 服务器的状态 2为Data为空,提示刷新.  3为数据数据未加密异常 4,Token失效,导致未登录
    private String mMsg;


    public ResultStatusException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        mMsg = msg;
    }

    public ResultStatusException(Throwable throwable, int errCode) {
        super(throwable);
        this.errCode = errCode;
    }

    public String getMsg() {
        return mMsg;
    }

    public int getErrCode() {
        return errCode;
    }
}
