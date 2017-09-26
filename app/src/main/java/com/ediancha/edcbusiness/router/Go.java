package com.ediancha.edcbusiness.router;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by dengmingzhi on 2017/9/21.
 */

public class Go {

    /**
     * 个人中心
     */
    public static void goPersonCenter() {
        ARouter.getInstance().build(RouterUrl.PERSON_CENTER_ACTIVITY).navigation();
    }

    /**
     * 空间详情
     */
    public static void goSpaceDesc() {
        ARouter.getInstance().build(RouterUrl.SPACE_DESC_ACTIVITY).navigation();
    }

    /**
     * 活动消息
     */
    public static void goActivityMessage() {
        ARouter.getInstance().build(RouterUrl.ACTIVITY_MESSAGE_ACTIVITY).navigation();
    }


    /**
     * 二维码扫描
     */
    public static void goQw(Activity ctx, int request) {
        ARouter.getInstance().build(RouterUrl.QW_ACTIVITY).navigation(ctx, request);
    }


    /**
     * 活动精选
     */
    public static void goActivitySplendid() {
        ARouter.getInstance().build(RouterUrl.ACTIVITY_SPLENDID_ACTIVITY).navigation();
    }

    /**
     * 活动精选
     */
    public static void goExpenseTips() {
        ARouter.getInstance().build(RouterUrl.EXPENSE_TIPS_ACTIVITY).navigation();
    }

    /**
     * 活动精选
     */
    public static void goLogin() {
        ARouter.getInstance().build(RouterUrl.LOGIN_ACTIVITY).navigation();
    }

    /**
     * 空间订单
     */
    public static void goSpaceOrder() {
        ARouter.getInstance().build(RouterUrl.SPACE_ORDER_ACTIVITY).navigation();
    }

    /**
     * 空间订单
     */
    public static void goGoodOrder() {
        ARouter.getInstance().build(RouterUrl.GOOD_ORDER_ACTIVITY).navigation();
    }

    /**
     * 空间订单详情
     */
    public static void goGoodOrderDesc(String orderId) {
        ARouter.getInstance().build(RouterUrl.GOOD_ORDER_DESC_ACTIVITY).withString("orderId", orderId).navigation();
    }

    /**
     * 我的钱包
     */
    public static void goMyPackage() {
        ARouter.getInstance().build(RouterUrl.WALLET_ACTIVITY).navigation();
    }

    /**
     * 余额
     */
    public static void goMoney() {
        ARouter.getInstance().build(RouterUrl.MONEY_ACTIVITY).navigation();
    }
    /**
     * 优惠券
     */
    public static void goCoupon() {
        ARouter.getInstance().build(RouterUrl.Coupon_ACTIVITY).navigation();
    }


    /**
     * 优惠券
     */
    public static void goDeposit() {
        ARouter.getInstance().build(RouterUrl.DEPOSIT_ACTIVITY).navigation();
    }

    /**
     * 充值
     */
    public static void goCharge() {
        ARouter.getInstance().build(RouterUrl.CHARGE_ACTIVITY).navigation();
    }

    /**
     * 我的资料
     */
    public static void goMyInfo() {
        ARouter.getInstance().build(RouterUrl.MY_INFO_ACTIVITY).navigation();
    }
}
