package com.ediancha.edcbusiness.activity.walletbag;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dmz.library.dmzapi.api.contract.SingleDataBuilder;
import com.dmz.library.dmzapi.api.presenter.IBasePresenter;
import com.dmz.library.dmzapi.view.activity.SingleDataBaseActivity;
import com.ediancha.edcbusiness.R;
import com.ediancha.edcbusiness.bean.user.UserInfoUtil;
import com.ediancha.edcbusiness.bean.walletbean.MyPackageBean;
import com.ediancha.edcbusiness.constant.ApiContant;
import com.ediancha.edcbusiness.router.Go;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


/**
 * Created by Admin on 2017/9/21.
 * 我的钱包
 */
@Route(path = "/activity/walletbag/mypackage")
public class MyPackageActivity extends SingleDataBaseActivity<MyPackageBean, MyPackageBean.Data> {


    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.ln_money)
    LinearLayout mLnMoney;
    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;
    @BindView(R.id.ln_coupon)
    LinearLayout mLnCoupon;
    @BindView(R.id.tv_deposit)
    TextView mTvDeposit;
    @BindView(R.id.ln_deposit)
    LinearLayout mLnDeposit;
    @BindView(R.id.tv_pass)
    TextView mTvPass;
    @BindView(R.id.ln_pass)
    LinearLayout mLnPass;

    MyPackageBean.Data data;
    @BindView(R.id.cb_check)
    CheckBox mCbCheck;

    @Override
    protected void initDataBuilder() {
        mBuilder.setCurrentViewEnum(SingleDataBuilder.ShowViewEnum.SUCCESSVIEW)
                .setSuccessRid(R.layout.activity_mypackage);
    }

    @Override
    protected void initDmzBuilder() {

        dBuilder.setaClass(MyPackageBean.class)
                .setUrl(ApiContant.MYPACKAGE)
                .setParms("token", UserInfoUtil.getToken(), "userId", UserInfoUtil.getUserId());
    }

    @Override
    protected void initBarView() {
        super.initBarView();
        dmzBar.setText("我的钱包");
    }

    @Override
    public void onSuccess(IBasePresenter presenter, MyPackageBean.Data bean) {
        data = bean;
        mTvMoney.setText(data.getMoney().getMoney() + "");
        mTvCoupon.setText(bean.getYouhui() + "");
        mTvDeposit.setText(bean.getDepositMoney() + "");
        mTvPass.setText((bean.getPayPwd() == 0) ? "未设置" : "已设置");

    }

    @OnClick({R.id.ln_money, R.id.ln_coupon, R.id.ln_deposit, R.id.ln_pass})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ln_money: //余额
                Go.goMoney();
                break;
            case R.id.ln_coupon://优惠券
                Go.goCoupon();
                break;
            case R.id.ln_deposit://押金
                Go.goDeposit(data.getDepositMoney() + "", data.getDepositType());
                break;
            case R.id.ln_pass:
                Go.goPayPassWord();//修改支付密码
                break;
        }
    }
    @OnCheckedChanged(R.id.cb_check)
    void checkChecked(){

    }

}
