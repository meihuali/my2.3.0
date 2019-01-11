package com.example.a77299007.myapplication.base.baseBean;


public class BaseBean<T>{

    /**
     * Msg : 获取信息成功！
     * Stat : 1
     * Data : {"Id":12,"Title":"内测通告 ","Contents":"各位同事，现在派工应用进入内测阶段，请各位暴力使用，发现问题，统一集中上报到唐云飞处，感谢您的参与。","CreationTime":"2017-05-23 09:47"}
     * data : null
     * rows : null
     * OpCode : 0
     * ModelState : null
     * Time : 1534743818
     * total : 0
     */

    public String Msg;
    public int Stat;
    public T Data;
    public Object data;
    public Object rows;
    public int OpCode;
    public String ModelState;
    public int Time;
    public int total;



}
