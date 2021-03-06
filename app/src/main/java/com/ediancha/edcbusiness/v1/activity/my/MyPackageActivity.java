package com.ediancha.edcbusiness.v1.activity.my;


import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import butterknife.OnClick;


/**
 * Created by Admin on 2017/9/21.
 * 我的钱包
 */
@Route(path = "/v1/activity/my/mypackage")
public class MyPackageActivity extends SingleDataBaseActivity<MyPackageBean, MyPackageBean.Data> {


    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvYhq)
    TextView tvYhq;
    @BindView(R.id.tvYj)
    TextView tvYj;
    @BindView(R.id.tvSetPass)
    TextView tvSetPass;
    @BindView(R.id.fgSetPass)
    FrameLayout fgSetPass;
    @BindView(R.id.ivOpen)
    ImageView ivOpen;
    private MyPackageBean.Data data;

    @Override
    protected void initDataBuilder() {
        mBuilder.setCurrentViewEnum(SingleDataBuilder.ShowViewEnum.SUCCESSVIEW)
                .setSuccessRid(R.layout.activity_mypackage);
    }

    @Override
    protected void initDmzBuilder() {

        dBuilder.setaClass(MyPackageBean.class)
                .setUrl(ApiContant.MYPACKAGE)
                .setParms(UserInfoUtil.getUserToken());
    }

    @Override
    protected void initBarView() {
        super.initBarView();
        dmzBar.setText("我的钱包");
    }

    @Override
    public void onSuccess(IBasePresenter presenter, MyPackageBean.Data bean) {
        data = bean;
        tvMoney.setText(data.getMoney().getMoney());
        tvYhq.setText(bean.getYouhui());
        tvSetPass.setText((bean.getPayPwd() == 0) ? "未设置" : "已设置");

        setPassword(bean.getPayPwd() != 0);

    }

    @OnClick({R.id.llMoeny, R.id.llYhq, R.id.llYj, R.id.fgSetPass, R.id.fgOpen})
    void click(View view) {
        switch (view.getId()) {
            case R.id.llMoeny: //余额
                Go.goMoney();
                break;
            case R.id.llYhq://优惠券
                Go.goCoupon();
                break;
            case R.id.llYj://押金
                Go.goCashPledgeActivity();
                break;
            case R.id.fgSetPass:
                Go.goPayPassWord();//修改支付密码
                break;
            case R.id.fgOpen:
                setPassword(fgSetPass.getVisibility() == View.GONE);
                break;
        }
    }


    private void setPassword(boolean isShow) {
        fgSetPass.setVisibility(isShow ? View.VISIBLE : View.GONE);
        ivOpen.setImageLevel(isShow ? 1 : 0);
    }

}
