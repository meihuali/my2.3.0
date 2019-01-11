package com.example.a77299007.myapplication.base.baseView;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import com.example.a77299007.myapplication.base.presenter.RxPresenter;

/**
 * Created by Administrator on 2017/3/16.
 * <p>
 * 基类需要初始化的数据
 */

public interface IBaseInitialization {

    @LayoutRes
    int getLayoutResId();

    /**
     * 获取RxPresenter,在界面销毁时,把rx订阅解绑
     *
     * @return
     */
    RxPresenter bindRxPresenter();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    void configViews();

    default void configViews(Bundle savedInstanceState){
        configViews();
    }

    void initData();
}
