package com.anyonavy.displaynavi.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.provider.Settings;

import com.anyonavy.displaynavi.MyApplication;
import com.anyonavy.displaynavi.R;

import static android.R.attr.value;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by zza on 2018/4/12.
 */

public class WifiStatusUtils {

    private static WifiStatusUtils mWifiStatusUtils = null;
    private WifiStateChangeRec wifiRec;
    private Context context;
    private OnWifiChangedCallBack onWifiChangedListener;

    private WifiStatusUtils(){
        context = MyApplication.getContextObject();
        wifiRec= new WifiStateChangeRec();
    }
    public static WifiStatusUtils getInstance(){
        synchronized(WifiStatusUtils.class){
            if(mWifiStatusUtils == null){
                mWifiStatusUtils = new WifiStatusUtils();
            }
        }
        return mWifiStatusUtils;
    }

    public int getWifiStatus(){
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int wifiLevel = wifiManager.calculateSignalLevel(wifiInfo.getRssi(), 4);
        return wifiLevel;
    }

    public void registerWifiBroadcastRec(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        context.registerReceiver(wifiRec,filter);
    }

    public void unRegisterRec(){
        context.unregisterReceiver(wifiRec);
    }

    public void openSettingWifi() {
        Intent intent=new Intent(Settings.ACTION_WIFI_SETTINGS);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    class WifiStateChangeRec extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case WifiManager.WIFI_STATE_CHANGED_ACTION:
                    int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                    if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
                        //可用
                    } else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
                        //不可用
                    }
                    break;
                case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                    Parcelable parcelableExtra = intent
                            .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                    if (null != parcelableExtra) {
                        NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                        NetworkInfo.State state = networkInfo.getState();
                        boolean isConnected = state== NetworkInfo.State.CONNECTED;
                        //链接和未连接
                    }
                    break;
                case WifiManager.RSSI_CHANGED_ACTION:
                    //更新level

                    break;
            }
        }
    }

    public interface OnWifiChangedCallBack{
        void updateWifiStatus(int level);
    }

    public void setOnWifiChangedListener(OnWifiChangedCallBack onWifiChangedListener) {
        this.onWifiChangedListener = onWifiChangedListener;
    }
}
