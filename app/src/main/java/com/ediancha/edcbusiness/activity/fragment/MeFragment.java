package com.ediancha.edcbusiness.activity.fragment;

import com.ediancha.edcbusiness.view.LazyLoadFragment;

/**
 * Created by Admin on 2017/10/9.
 */

public class MeFragment extends LazyLoadFragment {

    public static MeFragment newInstance(){
        return new MeFragment();
    }
    @Override
    public void initBaseView() {

    }

    @Override
    protected int setContentView() {
        return 0;
    }

    @Override
    protected void lazyLoad() {

    }
}