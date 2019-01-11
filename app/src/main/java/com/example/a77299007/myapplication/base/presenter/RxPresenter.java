package com.example.a77299007.myapplication.base.presenter;


import android.text.TextUtils;


import android.util.Log;
import com.example.a77299007.myapplication.api.CommonApi;
import com.example.a77299007.myapplication.base.baseBean.BaseBean;
import com.example.a77299007.myapplication.base.baseBean.BaseBeanTwo;
import com.example.a77299007.myapplication.base.baseView.IBaseView;
import com.example.a77299007.myapplication.net.NetApi;
import com.example.a77299007.myapplication.utils.DebugUtils;
import com.example.a77299007.myapplication.utils.NetworkUtils;
import com.example.a77299007.myapplication.utils.ToastUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.Objects;


/**
 * Created by Administrator on 2017/3/15.
 * <p>
 * 给需要Rxjava的请求的presenter使用的,防止内存泄漏
 */

public class RxPresenter<T extends IBaseView> implements IBasePresenter<T> {
    private static final String TAG = "RxPresenter";
    protected T mView;

    public RxPresenter(T view) {
        this.attachView(view);
    }

    public RxPresenter() {
    }


    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        dispose();
    }

    private void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
      mCompositeDisposable = null;
    }




    /**
     * 添加RxJava产生的订阅
     *
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }



    public CommonApi getCommonApi (){
        return NetApi.getInstance().getApiService();
    }



    /**
     * rx线程切换, io线程转换成ui线程
     *
     * @param flowable
     * @param onNext
     * @param onError
     * @param <D>
     */
    public <D> void rxSchedulers(Flowable<D> flowable,
                                 Consumer<D> onNext, Consumer<? super Throwable> onError) {

        addDisposable(flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError != null ? onError : Throwable::printStackTrace));
    }

    protected <D> void rxSchedulers(Flowable<D> flowable,Consumer<D> onNext){
        rxSchedulers(flowable, onNext, null);
    }


    /**
     * 需要获取其他状态 state 不需要结果的时候调用这个...
     */
    protected void executeForStateNoResult(Flowable<BaseBean> observable, ExecutedSuccess success,
                                           OnErrorCallBack error, OnOtherStateCallBack otherState, String progress) {

        PresenterBean bean = new PresenterBean(success, error, true, progress);

        bean.otherState = otherState;
        dataDealWith(observable, bean);
    }

    /**
     * 自己控制显示和隐藏进度条,  还有 进度条信息...
     */
    protected <TYPE> void excuteForState(Flowable<BaseBean<TYPE>> observable, OnExecutedSuccess<TYPE> success
            , OnErrorCallBack error, OnOtherStateCallBack otherState
            , boolean showProgress, boolean hideProgress, String progress) {
        PresenterBean<TYPE> bean = new PresenterBean<>(success, error, showProgress, progress);
        bean.otherState = otherState;
        bean.hideProgress = hideProgress;

        dataDealWith(observable, bean);
    }


    /**
     * 需要获取其他状态 state 的时候调用这个...
     * see
     */
    protected <TYPE> void excuteForState(Flowable<BaseBean<TYPE>> observable, OnExecutedSuccess<TYPE> success
            , OnErrorCallBack error, OnOtherStateCallBack otherState, String progress) {
        PresenterBean<TYPE> bean = new PresenterBean<>(success, error, true, progress);
        bean.otherState = otherState;

        dataDealWith(observable, bean);
    }






    /**
     * 能够控制是否要显示和隐藏进度条的接口
     */
    public <TYPE> void execute(
            Flowable<BaseBean<TYPE>> observable,
            OnExecutedSuccess<TYPE> success, OnErrorCallBack error,
            boolean showProgress, boolean hideProgress, String progressMessage) {

        PresenterBean<TYPE> bean = new PresenterBean<>(success, error, showProgress, progressMessage);
        bean.hideProgress = hideProgress;

        dataDealWith(observable, bean);
    }


    /**
     * 网络请求
     *
     * @param observable 请求数据
     * @param success    请求结果的处理
     * @param <TYPE>
     */
    protected <TYPE> void execute(Flowable<BaseBean<TYPE>> observable, OnExecutedSuccess<TYPE> success
            , OnErrorCallBack error) {

        PresenterBean<TYPE> bean = new PresenterBean<>(success, error, false, null);

        dataDealWith(observable, bean);
    }


    /**
     * 网络请求
     *
     * @param observable   请求数据
     * @param success      请求结果的处理
     * @param showProgress 是否需要显示网络请求的dialog
     * @param <TYPE>
     */
    protected <TYPE> void execute(Flowable<BaseBean<TYPE>> observable, OnExecutedSuccess<TYPE> success
            , boolean showProgress) {

        PresenterBean<TYPE> bean = new PresenterBean<>(success, showProgress);

        dataDealWith(observable, bean);

    }

    protected <TYPE> void execute(Flowable<BaseBean<TYPE>> observable, OnExecutedSuccess<TYPE> success
            , OnErrorCallBack error, boolean showProgress) {

        PresenterBean<TYPE> bean = new PresenterBean<>(success, error, showProgress, null);

        dataDealWith(observable, bean);

    }



    protected <TYPE> void execute(Flowable<BaseBean<TYPE>> observable, OnExecutedSuccess<TYPE> success
            , OnErrorCallBack error, String progressMessage) {

        PresenterBean<TYPE> bean = new PresenterBean<>(success, error, true, progressMessage);
        dataDealWith(observable, bean);
    }


    /**
     * @param observable   请求数据
     * @param success      请求结果的处理
     * @param showProgress 是否需要显示网络请求的dialog
     */
    protected void executeNoResult(Flowable<BaseBean> observable, ExecutedSuccess success, boolean showProgress) {
        PresenterBean bean = new PresenterBean(success, showProgress);
        dataDealWith(observable, bean);
    }

    /**
     * 显示dialog
     *
     * @param observable      请求数据
     * @param success         请求结果的处理
     * @param progressMessage 显示dialog的描述  ,默认为正在加载中
     */
    protected void executeNoResult(Flowable<BaseBean> observable, ExecutedSuccess success
            , String progressMessage) {
        PresenterBean bean = new PresenterBean(success, true, progressMessage);

        dataDealWith(observable, bean);
    }


    protected void executeNoResult(Flowable<BaseBean> observable, ExecutedSuccess success, OnErrorCallBack error
            , boolean showProgress) {

        PresenterBean bean = new PresenterBean(success, error, showProgress, null);

        dataDealWith(observable, bean);
    }


    public <TYPE, B extends BaseBean<TYPE>> void onNext(B tBaseBean, PresenterBean<TYPE> bean) {

        ToastUtils.showSingleToast(bean.toString());

        if (bean.allBack != null) {
            bean.allBack.allBaseBean(tBaseBean);
        }
        if (bean.hideProgress) {
            mView.hideProgress();
        }
        if (tBaseBean != null) {
            String state = tBaseBean.ModelState;
            int stat = tBaseBean.Stat;

            if (tBaseBean.OpCode==401){ //身份过期,重新登录
                ToastUtils.showSingleToast("身份过期,请重新登录");
                mView.shouldLogin();
        }
            if (bean.success != null){
                bean.success.onSuccess();
            }

            if (bean.callBack != null) {
                if (tBaseBean.Data != null) {
                    bean.callBack.onSuccess(tBaseBean.Data);
                }
            }
            if (TextUtils.equals("failed", state)) {
                ToastUtils.showSingleToast(tBaseBean.Msg);
                if (bean.error != null) {
                    bean.error.onError(tBaseBean.Msg);
                }
            } else if (TextUtils.equals("authFailed", state)) {   //登录超时
                mView.shouldLogin();
            }else if (stat == 1){
                //  ToastUtils.showSingleToast("获取成功");
                ToastUtils.showSingleToast(tBaseBean.Msg);

            }else {
                // 其他状态  是否弹出 提示信息   默认为 true , 表示要弹出
                if (bean.showTips) {
                    ToastUtils.showSingleToast(tBaseBean.Msg);
                }
                if (bean.error != null) {
                    bean.error.onError(tBaseBean.Msg);
                }
                if (null != bean.otherState) {
                    bean.otherState.onOtherState(state, tBaseBean.Msg);
                }
                if (null != bean.otherState) {
                    bean.otherState.onOtherState(state,String.valueOf(tBaseBean.Stat));
                }
            }
        } else {
            if (bean.error != null)
                bean.error.onError(Objects.requireNonNull("网络错误,请稍后再试"));
        }
    }


    protected void onError(PresenterBean bean, Throwable throwable) {
        if (bean != null && bean.hideProgress) {
            mView.hideProgress();
        }
        if (DebugUtils.INSTANCE.isDebug())
            if (throwable != null)
                throwable.printStackTrace();

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showSingleToast("联网失败,请检查网络");
        } else {
            ToastUtils.showSingleToast("链接失败,请稍后重试");
        }

        if (bean != null && bean.error != null) {
            bean.error.onError("");
        }
    }


    /**
     * 为了合并上面两种不同处理结果所抽取的方法,不对外开发
     *
     * @param observable
     * @param <C>
     * @param <D>
     */
    protected <C, D extends BaseBean<C>> void dataDealWith(Flowable<D> observable, PresenterBean<C> bean) {

        if (mView == null)
            return;
        if (bean.showProgress){
          mView.showProgress(TextUtils.isEmpty(bean.progressMessage)? "正在加载中" : bean.progressMessage);
        }

        rxSchedulers(observable, tBaseBean -> onNext(tBaseBean, bean), throwable -> onError(bean, throwable));
    }

    public interface OnExecutedSuccess<TYPE> {
        void onSuccess(TYPE result);
    }



    public interface ExecutedSuccess {
        void onSuccess();
    }


    public interface OnErrorCallBack {
        void onError(String message);
    }

    public interface OnOtherStateCallBack {
        void onOtherState(String state, String message);
    }


    public interface BaseBeanCallBack<TYPE> {
        void allBaseBean(BaseBean<TYPE> bean);
    }




    /////////////////////////////////////////////////////////














}
