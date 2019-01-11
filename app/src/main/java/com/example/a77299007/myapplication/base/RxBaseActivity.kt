package com.example.a77299007.myapplication.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*

import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions




import butterknife.Unbinder
import com.example.a77299007.myapplication.BuildConfig
import com.example.a77299007.myapplication.R
import com.example.a77299007.myapplication.base.baseView.IBaseInitialization
import com.example.a77299007.myapplication.base.baseView.IBaseView
import com.example.a77299007.myapplication.base.presenter.RxPresenter
import com.example.a77299007.myapplication.progress.LoadingDialog
import com.example.a77299007.myapplication.utils.Constants
import com.example.a77299007.myapplication.progress.ProgressDialog
import com.example.a77299007.myapplication.utils.ToastUtils


import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


/**
 * Created by Administrator on 2017/3/14.
 */

abstract class RxBaseActivity : AppCompatActivity(), IBaseInitialization {
    private var tvTitle: TextView? = null
    private var rl_layoutTitle :RelativeLayout ?=null
    private var backBtn: ImageView? = null
    private var ivSearch: ImageView? = null
    private var tvEdit: TextView? = null
    private var ivAdd: ImageView? = null
    protected var baseActivity: RxBaseActivity? = null
    private var mBind: Unbinder? = null
    protected var mPresenter: RxPresenter<*>? = RxPresenter<IBaseView>()
    private var mRxPermissions: RxPermissions? = null

    // private var mProgressDialog: ProgressDialog? = null
    private var mLoadingDialog:LoadingDialog? = null


    /**
     * 下面这4个变量是吐司需要用到的
     * */
    private var oldMsg: String? = null
    private var toast: Toast? = null
    private var oneTime: Long = 0
    private var twoTime: Long = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)
        val baseViewContainer = findViewById<LinearLayout>(R.id.ll_base)
        if ( isHasTitleBar()&&layoutResId!=0){
            layoutInflater.inflate(R.layout.base_titlebar, baseViewContainer)


            backBtn = findViewById(R.id.iv_back)
            tvTitle = findViewById(R.id.tv_title)
            rl_layoutTitle = findViewById(R.id.rl_layoutTitle)

            backBtn?.setOnClickListener {
                onBackClick()
            }
            tvEdit = findViewById(R.id.tv_edit)
            tvEdit?.setOnClickListener {
                tvClick()
            }
            ivSearch = findViewById(R.id.iv_search)
            ivSearch?.setOnClickListener {
                rightBtnClick()
            }
            ivAdd = findViewById(R.id.iv_add)
            ivAdd?.setOnClickListener {
                addIntent()
            }
        }
        layoutInflater.inflate(layoutResId, baseViewContainer)


        val simpleName = AppCompatActivity().javaClass.simpleName
        if (!simpleName.endsWith(Constants.KOTLIN_END))
            baseActivity = this

        mPresenter = bindRxPresenter()

        configViews(savedInstanceState)
        initData()
        initRxBus()
    }

    open fun tvClick() {}

    //设置右边字体颜色
    open fun setRightTextColor(color: Int) {
        tvEdit?.setTextColor(color)
    }

    open fun setRightTextContent(text: String,showState: Boolean) {
        tvEdit?.text = text
        if (showState) {
            tvEdit?.visibility = View.VISIBLE
        } else {
            tvEdit?.visibility = View.GONE
        }

    }


    //头部的添加按钮的跳转事件
    open fun addIntent() {}


    open fun isHasTitleBar(): Boolean {
        return true
    }

    open fun onBackClick() {
        finish()
    }

    //头部的搜索按钮的跳转事件
    open fun rightBtnClick() {}



    fun setTitle(title: String) {
        tvTitle?.text = title
    }

    fun setTitle(title: String, state: Boolean) {
        if (state) {
            rl_layoutTitle!!.visibility = View.VISIBLE
            tvTitle?.text = title
        } else {
            rl_layoutTitle!!.visibility = View.GONE
        }

    }




    @SuppressLint("WrongConstant")
    fun showToast(s: String) {
        if (TextUtils.isEmpty(s)) {
            return
        }

        if (toast == null) {
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT)
            toast!!.setGravity(Gravity.TOP, 0, 0)

            toast!!.show()
            oneTime = System.currentTimeMillis()
        } else {
            twoTime = System.currentTimeMillis()
            if (!TextUtils.isEmpty(s) && s == oldMsg) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast!!.show()
                }
            } else {
                oldMsg = s
                toast!!.setText(s)
                toast!!.show()
            }
        }
        oneTime = twoTime
    }






    // 基类里面 统一将 根布局设置 setFitsSystemWindows 属性， 否则会出现问题， 标题显示不完整
    private fun setRootView() {
        val v = findViewById<View>(android.R.id.content)

        if (v is ViewGroup) {
            var i = 0
            val count = v.childCount
            while (i < count) {
                val childView = v.getChildAt(i)
                childView.fitsSystemWindows = true
                if (childView is ViewGroup) {
                    childView.clipToPadding = true
                }
                i++
            }
        }
    }

    protected fun initRxBus() {

    }

    override fun bindRxPresenter(): RxPresenter<*>? {
        return null
    }

    /**
     * 管理订阅者
     *
     * @param disposable 订阅的disposable
     */
    fun addDisposable(disposable: Disposable) {
//         if (mPresenter == null)
//             mPresenter = RxPresenter<>()
        mPresenter!!.addDisposable(disposable)
    }

    protected fun startActivity(cls: Class<*>) {
        val intent = Intent(baseActivity, cls)
        baseActivity?.startActivity(intent)
    }



    public override fun onResume() {
        super.onResume()

    }

    public override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBind != null) {
            mBind!!.unbind()
        }

        if (mPresenter != null) {
            mPresenter!!.detachView()
            mPresenter = null
        }
        mRxPermissions = null
        if (mLoadingDialog != null) {
            mLoadingDialog!!.dismiss()
            mLoadingDialog = null
        }



        //销毁吐司
        if (toast != null) {
            toast!!.cancel()
            toast = null
        }



        //监控内存泄漏
        //    BaseApplication.getsInstance()!!.getRefWatcher().watch(this)
    }

    /**
     * 检查权限 Permission
     *
     * @param onNext      结果处理
     * @param permissions 需要申请的权限  Manifest.permission.ACCESS_CHECKIN_PROPERTIES...
     */
    fun checkPermissions(onNext: Consumer<Permission>, vararg permissions: String) {
        addDisposable(permissionsAnalyze(onNext, *permissions))
    }

    /**
     * 检查权限
     *
     * @param onNext
     * @param permissions
     * @return
     */
    fun permissionsAnalyze(onNext: Consumer<Permission>, vararg permissions: String): Disposable {
        if (mRxPermissions == null) {
            mRxPermissions = RxPermissions(baseActivity!!)
            mRxPermissions!!.setLogging(BuildConfig.DEBUG)
        }
        return mRxPermissions!!.requestEach(*permissions)
            .subscribe(onNext, Consumer { it.printStackTrace() })
    }

    fun showProgress(progressMessage: String) {
        if (TextUtils.isEmpty(progressMessage)) {
            return
        }

        if (Looper.getMainLooper() != Looper.myLooper()) {
            return
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.Builder(baseActivity)
                .setmCancelableOnTouchOutside(false)
                .setOnCancelListener{
                    hideProgress()
                 //   ToastUtils.showSingleToast("您取消了网络请求···")
                    //这里是取消loading 同时也取消网络请求
                    if (mPresenter != null) {
                        mPresenter!!.detachView()
                        mPresenter = null
                    }
                }
                .build()
        }
        mLoadingDialog!!.show(progressMessage)
    }


    fun hideProgress() {
        if (mLoadingDialog != null) {
            mLoadingDialog!!.dismiss()
            mLoadingDialog!!.cancel()
        }
    }

    /**
     * 隐藏键盘
     *
     * @param t
     */
    @JvmOverloads
    fun hideSoftInputFromWindow(t: View? = super.getWindow().decorView) {
        if (t == null)
            return
        val imm = baseActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(t.windowToken, 0)
    }



    fun complete(){

    }

    /**
     * 超时,需要重新登录
     */
    fun shouldLogin() {
        showToast("账号被顶了,这里可以跳转到登陆界面，前提是干掉其他Activity")
    }

    override fun onBackPressed() {
        hideSoftInputFromWindow()

        super.onBackPressed()
    }





    override fun toString(): String {
        return javaClass.simpleName
    }

}

