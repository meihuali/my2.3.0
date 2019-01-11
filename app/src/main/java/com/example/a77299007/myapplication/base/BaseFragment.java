package com.example.a77299007.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.example.a77299007.myapplication.App;
import com.example.a77299007.myapplication.base.baseView.IBaseInitialization;
import com.example.a77299007.myapplication.base.presenter.RxPresenter;
import com.example.a77299007.myapplication.utils.Constants;
import com.tbruyelle.rxpermissions2.Permission;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class BaseFragment extends Fragment implements IBaseInitialization {

    protected View mParentView;
    private Unbinder mBinder;
    protected BaseFragment mContext;
    protected RxPresenter mPresenter;
    public RxBaseActivity mBaseActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(getLayoutResId(), container, false);
        return mParentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String simpleName = super.getClass().getSimpleName();
        if (!simpleName.endsWith(Constants.KOTLIN_END))   //过滤点  kotlin 写的类
            mBinder = ButterKnife.bind(this, view);
        FragmentActivity activity = super.getActivity();
        if (activity instanceof RxBaseActivity) {
            mBaseActivity = (RxBaseActivity) activity;
        }
        mContext = this;
        mPresenter = bindRxPresenter();
        configViews();
        initData();
        initRxBus();
    }

    protected void initRxBus() {
    }

    @Override
    public RxPresenter bindRxPresenter() {
        return null;
    }

    /**
     * 管理订阅者
     *
     * @param disposable 订阅的disposable
     */
    public void addDisposable(Disposable disposable) {
        mPresenter.addDisposable(disposable);
    }









    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinder != null)
            mBinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    public void checkPermissions(Consumer<Permission> onNext, String... permissions) {
        if (mPresenter == null)
            mPresenter = new RxPresenter();
        mPresenter.addDisposable(mBaseActivity.permissionsAnalyze(onNext, permissions));
    }

    public void finish() {
        super.getActivity().finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //监控内存泄漏
      //  App.getRefWatcher().watch(this);
    }

    public void showProgress(String progressMessage) {

        mBaseActivity.showProgress(progressMessage);
    }

    public void hideProgress() {
        mBaseActivity.hideProgress();
    }



    /**
     * 给下拉刷新用的,哪个使用,复写该方法
     */
    public void complete() {

    }

    /**
     * 超时,需要重新登录
     */
    public void shouldLogin() {
        mBaseActivity.shouldLogin();
    }

    /**
     * 隐藏键盘
     *
     * @param t
     */
    public void hideSoftInputFromWindow(TextView t) {
        InputMethodManager imm = (InputMethodManager) mBaseActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(t.getWindowToken(), 0);
    }






    public RxBaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public void onResume() {
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }

}
