package com.example.a77299007.myapplication.base.presenter;

/**
 * Created by mac on 2017/12/8.
 * <p>
 * 网络请求数据处理的参数
 */

public class PresenterBean<C> {


    public PresenterBean() {
    }

    /**
     * 网络请求成功处理数据
     */
    public RxPresenter.OnExecutedSuccess<C> callBack;

    /**
     * 返回所有的数据
     */
    public RxPresenter.BaseBeanCallBack<C> allBack;

    /**
     * 网络请求成功没有结果的数据
     */
    public RxPresenter.ExecutedSuccess success;

    /**
     * 请求出现错误
     */
    public RxPresenter.OnErrorCallBack error;

    /**
     * 其他的状态码处理
     */
    public RxPresenter.OnOtherStateCallBack otherState;

    /**
     * 是否显示加载框
     */
    public boolean showProgress;

    /**
     * 是否隐藏加载框
     */
    public boolean hideProgress = true;

    /**
     * 加载框显示的文字
     */
    public String progressMessage;

    /**
     * 其他状态  是否弹出 提示信息   默认为 true , 表示要弹出
     */
    public boolean showTips = true;

    public PresenterBean(RxPresenter.OnExecutedSuccess<C> callBack, boolean showProgress) {
        this(callBack, showProgress, null);
    }

    public PresenterBean(RxPresenter.ExecutedSuccess success, boolean showProgress) {
        this(success, showProgress, null);
    }

    public PresenterBean(RxPresenter.OnExecutedSuccess<C> callBack, boolean showProgress
            , String progressMessage) {
        this(callBack, null, showProgress, progressMessage);
    }


    public PresenterBean(RxPresenter.ExecutedSuccess success, boolean showProgress
            , String progressMessage) {
        this(success, null, showProgress, progressMessage);
    }

    public PresenterBean(RxPresenter.OnExecutedSuccess<C> callBack, RxPresenter.OnErrorCallBack error
            , boolean showProgress, String progressMessage) {
        this.callBack = callBack;
        this.error = error;
        this.showProgress = showProgress;
        this.progressMessage = progressMessage;
    }

    public PresenterBean(RxPresenter.ExecutedSuccess success, RxPresenter.OnErrorCallBack error
            , boolean showProgress, String progressMessage) {
        this.success = success;
        this.error = error;
        this.showProgress = showProgress;
        this.progressMessage = progressMessage;
    }

}
