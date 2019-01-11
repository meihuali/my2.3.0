package com.example.a77299007.myapplication.event;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/3/16.
 * <p>
 * 过滤器,给rxBus做过滤使用
 */

public class Events<T> {

    //所有事件的CODE
    public static final int PAY_SUCCESS = 1; //支付界面  支付成功
    public static final int EXIT_APP = 2; //退出app
    public static final int UPDATE_RECHARGE = 3; //充值会员费用
    public static final int GO_HOME = 4;   //跳转HomeActivity的HomeFragment
    public static final int WECHAT_PAY = 5; //微信支付回调通知
    public static final int PARTNER_PAY_SUCCESS = 6; //合伙人支付成功
    public static final int GOODS_DELETE = 10;
    public static final int UPDATE_SERVICE_FEE = 11; // 选择会员级别成功之后,  返回赊货成功界面,  通知赊货成功界面更新服务费数据
    public static final int GET_UPDATE_APPLY_STATES = 13;       // 更新易货信用界面的数据
    public static final int RE_LOAD_ENTITY_LIST_DATA = 14; //  当订单状态发生改变的时候,  重新 获取订单列表的数据
    public static final int LOGIN_SUCCESS = 15; //  登录成功
    public static final int UPDATE_BARTER_CATEGORY_LIST = 16; //   易货团  选择分类的时候,  Activity 的弹出框删除
    // item 的时候 发送的通知(里面 Fragment 的条目要更新)
    public static final int UPDATE_TOGETHER_REVIEW_STATUS = 18; //一起焕报名和审核回到详情页面更新审核状态

    public static final int UPDATE_AUCTION_DETAIL_PAY = 17; //   拍卖详情支付


    public static final int INSTALL_APK = 19; //   安装apk

    //刷新我的需求

    public static final int REFRESH_WANT = 20;

    public static final int RECRUI_MEMBER_DELETE = 21; //   协会会员页面删除了该招募信息后返回到协会管理页面重新请求数据
    public static final int REFRESH_SHOPPING_CART = 23;

    public static final int CHAT_TRANSFER_BACK = 22;//  聊天转账支付后返回聊天窗口


    public static final int FRIEND_CIRCLE_ISSUE = 24;//  焕友圈发表动态更新
    public static final int DELETE_FRIEND = 25;//  焕友圈发表动态更新
    public static final int CHAT_TRANSFER_RECEIVE = 26;//  聊天转账接收
    public static final int CHAT_TRANSFER_RETURN = 27;//  聊天转账退回
    public static final int DYNAMIC_DETAILS_UPDATE = 28; //焕友圈详情回到列表更新




    //  云通信, 聊天, 用户 被强制 剔除下线
    public static final int IM_SDK_FORCE_OFFLINE = 29;

    //  云通信, 聊天,  用户签名过期了，需要刷新 userSig 重新登录 SDK
    public static final int IM_SDK_USER_SIG_EXPIRED = 30;

    //  云通信, 聊天,   登录成功
    public static final int IM_SDK_LOGIN_SCS = 31;

    //  云通信, 聊天,  登录失败
    public static final int IM_SDK_LOGIN_FAIL = 32;

    //唤唤商机底部消息数量
    public static final int HOME_MESSAGE_NUM =33;
    //采购匹配标签变化回来更新
    public static final int PURCHASE_UPDATE=34;
    //客户资料页面变化回来刷新
    public static final int CLIENT_UPDATE=35;

    //焕焕商机页面焕友圈,通知消息数量更新
    public static final int HUAN_MESSAGE_UPDATE=36;

    //收到通知消息,焕焕商机页面更新
    public static final int NOTICE_UPDATE=37;


    //枚举

    @IntDef({PAY_SUCCESS, EXIT_APP, WECHAT_PAY, UPDATE_RECHARGE, GOODS_DELETE,
            UPDATE_SERVICE_FEE, PARTNER_PAY_SUCCESS,
            GO_HOME, GET_UPDATE_APPLY_STATES, RE_LOAD_ENTITY_LIST_DATA, LOGIN_SUCCESS,
            REFRESH_WANT, REFRESH_SHOPPING_CART, CHAT_TRANSFER_RETURN,
            UPDATE_BARTER_CATEGORY_LIST, UPDATE_TOGETHER_REVIEW_STATUS,
            UPDATE_AUCTION_DETAIL_PAY, INSTALL_APK, FRIEND_CIRCLE_ISSUE,
            DELETE_FRIEND, CHAT_TRANSFER_RECEIVE, CHAT_TRANSFER_BACK,
            DYNAMIC_DETAILS_UPDATE,
            IM_SDK_FORCE_OFFLINE, IM_SDK_USER_SIG_EXPIRED,
            IM_SDK_LOGIN_SCS, IM_SDK_LOGIN_FAIL,HOME_MESSAGE_NUM,PURCHASE_UPDATE,
            CLIENT_UPDATE,HUAN_MESSAGE_UPDATE,NOTICE_UPDATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventCode {
    }


    @Events.EventCode
    int code;

    //需要发送的数据
    T content;

    public Events(int code, T content) {
        this.code = code;
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
