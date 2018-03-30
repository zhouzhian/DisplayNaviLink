package com.anyonavy.displaynavi.data.appinfo.source.local;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;


import com.anyonavy.displaynavi.MyApplication;
import com.anyonavy.displaynavi.R;
import com.anyonavy.displaynavi.data.appinfo.AppInfo;
import com.anyonavy.displaynavi.utils.AppManagerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZuiC on 2017/1/18.
 */

public class AppInfoLocalDataSource {

    private static final String TAG = "AppInfoManager";
    private Context mContext;
    private static AppInfoLocalDataSource INSTANCE = null;


    public static AppInfoLocalDataSource getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new AppInfoLocalDataSource();
        }
        return INSTANCE;
    }

    private AppInfoLocalDataSource(){
        this.mContext = MyApplication.getContextObject();
    }

/*    public void launchApplication(String packageName){
        if (packageName != null && isAppInstalled(packageName)){
            PackageManager packageManager = mContext.getPackageManager();
            Intent appIntent = packageManager.getLaunchIntentForPackage(packageName);
            mContext.startActivity(appIntent);
        }else {
            Toast.makeText(mContext, "该应用暂不可用", Toast.LENGTH_SHORT).show();
        }
    }*/

    public List<AppInfo> getAppInfo(){
        List<AppInfo> mAllAppInfo = AppManagerUtils.getAllNonsystemProgramInfo(mContext);
        return mAllAppInfo;
    }

    private String getAppVersionName(String packageName) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "找不到版本号";
        }
    }

    private String getAppVersionCode(String packageName) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            String version = info.versionCode + "";
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "找不到版本号";
        }
    }

    private boolean isAppInstalled(String packageName) {
        PackageManager pm = mContext.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
}
