package com.ediancha.edcbusiness.v1.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dmz.library.dmzapi.api.contract.SingleDataBuilder;
import com.dmz.library.dmzapi.api.presenter.IBasePresenter;
import com.dmz.library.dmzapi.utils.MyToast;
import com.dmz.library.dmzapi.view.activity.SingleDataBaseActivity;
import com.dmz.library.dmzapi.view.custom.TipView;
import com.ediancha.edcbusiness.R;
import com.ediancha.edcbusiness.bean.user.UserInfoUtil;
import com.ediancha.edcbusiness.constant.ApiContant;
import com.ediancha.edcbusiness.v1.bean.CashPledgeBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/v1/activity/my/cashPledge")
public class CashPledgeActivity extends SingleDataBaseActivity<CashPledgeBean, CashPledgeBean.Data> {

    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvTip)
    TextView tvTip;
    @BindView(R.id.btSubmit)
    Button btSubmit;

    private int status;

    @Override
    public void onSuccess(IBasePresenter presenter, CashPledgeBean.Data bean) {
        status = bean.getStatus();
        tvStatus.setText(bean.getStatusInfo());
        btSubmit.setText(bean.getShowStatusInfo());
        tvTip.setVisibility(status == 1 ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initDataBuilder() {
        mBuilder.setSuccessRid(R.layout.success_cash_pledge)
                .setCurrentViewEnum(SingleDataBuilder.ShowViewEnum.SUCCESSVIEW);
    }

    @Override
    protected void initDmzBuilder() {
        dBuilder.setaClass(CashPledgeBean.class)
                .setParms(UserInfoUtil.getUserToken())
                .setUrl(ApiContant.CASHPLEDGE);
    }

    @Override
    protected void initBarView() {
        super.initBarView();
        dmzBar.setText("押金");
    }


    @OnClick(R.id.btSubmit)
    void submit() {
        switch (status) {
            case 0:
                //TODO 去押金缴纳页面
                break;
            case 2:
                //TODO 退押金操作
                TipView.getInstance()
                        .setTitle("退押金")
                        .setContent("退押金后将无法使用共享空间！")
                        .setBottom(new TipView.BottomInfo("确定要退", new TipView.OnClickListener() {
                            @Override
                            public void OnClick() {
                                MyToast.normal("押金退还成功");
                            }
                        }))
                        .setShowCancel(false)
                        .setBottom("不退了").show(this);
                break;
            case 1:
                //TODO 取消退押金操作
                TipView.getInstance()
                        .setTitle("取消退押金")
                        .setContent("取消退还押金后您可以正常使用空间！")
                        .setShowCancel(false)
                        .setBottom("不取消")
                        .setBottom(new TipView.BottomInfo("取消退押金", new TipView.OnClickListener() {
                            @Override
                            public void OnClick() {
                                MyToast.normal("取消押金退还成功");
                            }
                        })).show(this);
                break;
        }
    }
}
