package com.anyonavy.displaynavi.view.bottombar;

import android.os.Handler;

import com.anyonavy.displaynavi.utils.DateUtils;

/**
 * Created by zza on 2018/2/27.
 */

public class BottomPresenter implements BaseBottomContract.Presenter {

    private  BaseBottomContract.View bottomBarview;
    private Handler mHandler;
    private Runnable mUpdateDate;

    public BottomPresenter(BaseBottomContract.View view) {
        this.bottomBarview=view;
    }

    @Override
    public void start() {
        bottomBarview.setPresenter(this);
        mHandler = new Handler();
        final DateUtils dateUtils = DateUtils.getInstance();
        mUpdateDate = new Runnable() {
            @Override
            public void run() {
                bottomBarview.setCurDate(dateUtils.getDate());
                mHandler.postDelayed(this,1000);
            }
        };
    }

    @Override
    public void startUpdateDate() {
        mHandler.postDelayed(mUpdateDate,1000);
    }

    @Override
    public void stopUpdateDate() {
        mHandler.removeCallbacks(mUpdateDate);
    }
}
