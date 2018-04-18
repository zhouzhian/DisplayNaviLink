package com.anyonavy.displaynavi.view.topbar;

import android.os.Handler;

import com.anyonavy.displaynavi.utils.BluetoothStatusUtils;
import com.anyonavy.displaynavi.utils.DateUtils;
import com.anyonavy.displaynavi.utils.WifiStatusUtils;

/**
 * Created by zza on 2018/2/27.
 */

public class TopbarPresenter implements BaseTopbarContract.BaseTopBarPresenter {

    private final BaseTopbarContract.BaseTopBarView baseTopBarView;
    private Handler mHandler;
    private Runnable mUpdateTime;
    private BluetoothStatusUtils bluUtils;
    private WifiStatusUtils wifiUtils;

    public TopbarPresenter(BaseTopbarContract.BaseTopBarView baseTopBarView) {
        this.baseTopBarView=baseTopBarView;
    }

    @Override
    public void start() {
        baseTopBarView.setPresenter(this);
        //时间状态更新
        mHandler = new Handler();
        final DateUtils dateUtils = DateUtils.getInstance();
        mUpdateTime = new Runnable() {
            @Override
            public void run() {
                baseTopBarView.setCurTime(dateUtils.getTime());
                mHandler.postDelayed(this,1000);
            }
        };
        //蓝牙状态更新及监听
        bluUtils = BluetoothStatusUtils.getInstance();
        bluUtils.registerBroadcastReceiver();
        bluUtils.setOnBroadcastChanged(new BluetoothStatusUtils.OnBroadcastChanged() {
            @Override
            public void updateBluStatus(int status) {
                baseTopBarView.setCurBluStatus(status);
            }
        });
        //wifi状态更新及监听
        wifiUtils = WifiStatusUtils.getInstance();
        wifiUtils.registerWifiBroadcastRec();
        wifiUtils.setOnWifiChangedListener(new WifiStatusUtils.OnWifiChangedCallBack() {
            @Override
            public void updateWifiStatus(int level) {
                baseTopBarView.setCurWifiStatus(level);
            }
        });
    }

    @Override
    public void startUpdateTime() {
        mHandler.post(mUpdateTime);
    }

    @Override
    public void stoptUpdateTime() {
        mHandler.removeCallbacks(mUpdateTime);
    }

    @Override
    public void updateBluAndWifiStatus() {
        int blueStatus = bluUtils.getCurBluStatus();
        baseTopBarView.setCurBluStatus(blueStatus);

        int wifiStatus= wifiUtils.getWifiStatus();
        baseTopBarView.setCurWifiStatus(wifiStatus);
    }

    @Override
    public void removeBroadcastRec() {
        bluUtils.unregisterRec();
        wifiUtils.unRegisterRec();
    }

    @Override
    public void openBluIntent() {
        bluUtils.openSettingBlu();
    }

    @Override
    public void openWifiIntent() {
        wifiUtils.openSettingWifi();
    }
}
