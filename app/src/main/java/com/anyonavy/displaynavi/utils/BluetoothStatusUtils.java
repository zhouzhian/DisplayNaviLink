package com.anyonavy.displaynavi.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.anyonavy.displaynavi.MyApplication;

import java.util.Iterator;
import java.util.Set;

import static android.R.attr.action;
import static android.R.attr.actionOverflowButtonStyle;
import static android.R.attr.filter;
import static android.R.attr.value;
import static android.content.ContentValues.TAG;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.baidu.location.g.j.B;
import static com.baidu.location.g.j.ac;
import static com.baidu.location.g.j.m;

/**
 * Created by zza on 2018/4/8.
 */

public class BluetoothStatusUtils {

    private static BluetoothStatusUtils mBluetoothStatusUtils = null;
    private Context context;
    private OnBroadcastChanged onBroadcastChanged;
    private BluetoothBroadcastRec bluetoothBroadcastRec;

    private BluetoothStatusUtils(){
        context = MyApplication.getContextObject();
        bluetoothBroadcastRec= new BluetoothBroadcastRec();
    }

    public static BluetoothStatusUtils getInstance(){
        synchronized(BluetoothStatusUtils.class){
            if(mBluetoothStatusUtils == null){
                mBluetoothStatusUtils = new BluetoothStatusUtils();
            }
        }
        return mBluetoothStatusUtils;
    }

    public int getCurBluStatus(){
        BluetoothAdapter bluetoothAdapter = null;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            final BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
            bluetoothAdapter = bluetoothManager.getAdapter();
        }

        if (bluetoothAdapter==null){
            return ContsUtils.BLU_STATUS_NULL;
        }else {
            if (bluetoothAdapter.isEnabled()){
                int mHeadset = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET);
                int mA2dp = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP);
                int mHealth = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEALTH);
                if (mHeadset == BluetoothProfile.STATE_CONNECTED ||
                        mA2dp == BluetoothProfile.STATE_CONNECTED ||
                        mHealth == BluetoothProfile.STATE_CONNECTED ){
                    return ContsUtils.BLU_STATUS_CONNECTED;
                }else {
                    return ContsUtils.BLU_STATUS_OPEN;
                }
            }else {
                return ContsUtils.BLU_STATUS_CLOSE;
            }
        }
    }

    public void registerBroadcastReceiver(){
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        context.registerReceiver(bluetoothBroadcastRec,mFilter);
    }

    public void unregisterRec() {
        context.unregisterReceiver(bluetoothBroadcastRec);
    }

    public void openSettingBlu() {
        Intent intent=new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    class BluetoothBroadcastRec extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action == BluetoothAdapter.ACTION_STATE_CHANGED){
                int mAction = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);
                Log.e("zza", "onReceive: mAction="+ mAction);
                switch (mAction) {
                    case BluetoothAdapter.STATE_OFF:
                        onBroadcastChanged.updateBluStatus(ContsUtils.BLU_STATUS_CLOSE);
                        break;
                    case BluetoothAdapter.STATE_ON:
                        onBroadcastChanged.updateBluStatus(ContsUtils.BLU_STATUS_OPEN);
                        break;
                }
            }else if (action == BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED){

            }
        }
    }

    public interface OnBroadcastChanged{
        void updateBluStatus(int status);
    }

    public void setOnBroadcastChanged(OnBroadcastChanged onBroadcastChanged){
        this.onBroadcastChanged = onBroadcastChanged;
    }
}
