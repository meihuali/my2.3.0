package com.example.a77299007.myapplication.base.presenter;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface IBasePresenter<T> {

    void attachView(T view);

    void detachView();

}
