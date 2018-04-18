package com.anyonavy.displaynavi.view.topbar;


import com.anyonavy.displaynavi.BaseContract;

/**
 * Created by zza on 2018/2/27.
 */

public interface BaseTopbarContract {

    interface BaseTopBarPresenter extends BaseContract.BasePresenter{
        void startUpdateTime();
        void stoptUpdateTime();

        void updateBluAndWifiStatus();
        void removeBroadcastRec();

        void openBluIntent();

        void openWifiIntent();
    }

    interface BaseTopBarView extends BaseContract.BaseView<BaseTopBarPresenter>{
        void setCurTime(String time);

        void setCurBluStatus(int status);
        void setCurWifiStatus(int wifiStatus);
    }
}
