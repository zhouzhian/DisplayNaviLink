package com.anyonavy.displaynavi.view.topbar;

import android.os.Handler;

import com.anyonavy.displaynavi.utils.DateUtils;

/**
 * Created by zza on 2018/2/27.
 */

public class TopbarPresenter implements BaseTopbarContract.BaseTopBarPresenter {

    private final BaseTopbarContract.BaseTopBarView baseTopBarView;
    private Handler mHandler;
    private Runnable mUpdateTime;

    public TopbarPresenter(BaseTopbarContract.BaseTopBarView baseTopBarView) {
        this.baseTopBarView=baseTopBarView;
    }

    @Override
    public void start() {
        baseTopBarView.setPresenter(this);
        mHandler = new Handler();
        final DateUtils dateUtils = DateUtils.getInstance();
        mUpdateTime = new Runnable() {
            @Override
            public void run() {
                baseTopBarView.setCurTime(dateUtils.getTime());
                mHandler.postDelayed(this,1000);
            }
        };
    }

    @Override
    public void startUpdateTime() {
        mHandler.post(mUpdateTime);
    }

    @Override
    public void stoptUpdateTime() {
        mHandler.removeCallbacks(mUpdateTime);
    }
}
