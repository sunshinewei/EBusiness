package com.ediancha.edcbusiness.v1.activity.help;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.dmz.library.dmzapi.api.bean.IType;
import com.dmz.library.dmzapi.api.contract.SingleDataBuilder;
import com.dmz.library.dmzapi.api.list.AdapterHelper;
import com.dmz.library.dmzapi.api.presenter.IBasePresenter;
import com.dmz.library.dmzapi.view.activity.SingleDataBaseActivity;
import com.dmz.library.dmzapi.view.custom.DmzBar;
import com.ediancha.edcbusiness.R;
import com.ediancha.edcbusiness.bean.walletbean.HelpCenterBean;
import com.ediancha.edcbusiness.constant.ApiContant;
import com.ediancha.edcbusiness.router.Go;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2017/9/26.
 */
@Route(path =  "/v1/activity/help/helpCenter")
public class HelpCenterActivity extends SingleDataBaseActivity<HelpCenterBean, HelpCenterBean.Data> implements  AdapterHelper.OnClickListener, AdapterHelper.OnConvertInterface {
    @BindView(R.id.recy_one)
    RecyclerView mRecyOne;
    @BindView(R.id.recy_two)
    RecyclerView mRecyTwo;
    @BindView(R.id.tv_fankuai)
    TextView mTvFankuai;

    @Override
    public void onSuccess(IBasePresenter presenter, HelpCenterBean.Data bean) {
        AdapterHelper._instance(this, mRecyOne)
                ._initData(bean.getHots()).setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        })
                .setType(new AdapterHelper.ViewTypeInfo().setType(0).setRid(R.layout.item_helpcenter_go).setConvertInterface(this).setOnClickListener(this));

        AdapterHelper._instance(this, mRecyTwo)
                ._initData(bean.getCategorys()).setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        })
                .setType(new AdapterHelper.ViewTypeInfo().setType(1).setRid(R.layout.item_helpcenter_go).setConvertInterface(this).setOnClickListener(this));

    }


    @Override
    protected void initDataBuilder() {
        mBuilder.setCurrentViewEnum(SingleDataBuilder.ShowViewEnum.SUCCESSVIEW)
                .setSuccessRid(R.layout.success_activity_helpcenter);
    }

    @Override
    protected void initDmzBuilder() {

        dBuilder.setaClass(HelpCenterBean.class)
                .setUrl(ApiContant.HELPCENTER_URL);
    }


    @Override
    protected void initBarView() {
        super.initBarView();
        dmzBar.setText("帮助中心");
    }


    @Override
    public void onItemClick(int viewType, AdapterHelper adapterHelper, int position) {

        switch (viewType) {
            case 0:
                HelpCenterBean.HotsBean hotsBean = (HelpCenterBean.HotsBean) adapterHelper.getDatas().get(position);
                Go.goWebView(hotsBean.getHtml());
                break;
            case 1:
                HelpCenterBean.CategorysBean categorysBean = (HelpCenterBean.CategorysBean) adapterHelper.getDatas().get(position);
                Go.goHelpCenterNext(categorysBean.getPid(), categorysBean.getName());
                break;
        }
    }

    @OnClick(R.id.tv_fankuai)
    void onClick() {
        Go.goFanKui();
    }


    @Override
    public void convert(int viewType, ViewHolder holder, IType iType, int position) {

        switch (viewType) {
            case 0:
                HelpCenterBean.HotsBean hotsBean = (HelpCenterBean.HotsBean) iType;
                holder
                        .setText(R.id.tv_title, hotsBean.getTitle());
                break;
            case 1:
                HelpCenterBean.CategorysBean category = (HelpCenterBean.CategorysBean) iType;
                holder
                        .setText(R.id.tv_title, category.getName());
                break;
        }
    }

    @Override
    protected int getRid() {
        return R.layout.activity_space_desc;
    }
}
