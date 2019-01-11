package com.example.a77299007.myapplication.base.baseBean;

import java.util.List;

public class BaseBeanTwo<T> {
    private List<T> Data;
    public String Msg;
    public int Stat;
    public Object data;
    public Object rows;
    public int OpCode;
    public String ModelState;
    public int Time;
    public int total;


    public BaseBeanTwo() {
    }

    public List<T> getData() {
        return Data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public int getOpCode() {
        return OpCode;
    }

    public void setOpCode(int opCode) {
        OpCode = opCode;
    }

    public String getModelState() {
        return ModelState;
    }

    public void setModelState(String modelState) {
        ModelState = modelState;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setData(List<T> data) {
        Data = data;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getStat() {
        return Stat;
    }

    public void setStat(int stat) {
        Stat = stat;
    }

    @Override
    public String toString() {
        return "BaseBeanTwo{" +
                "Data=" + Data +
                ", Msg='" + Msg + '\'' +
                ", Stat=" + Stat +
                ", data=" + data +
                ", rows=" + rows +
                ", OpCode=" + OpCode +
                ", ModelState='" + ModelState + '\'' +
                ", Time=" + Time +
                ", total=" + total +
                '}';
    }
}
