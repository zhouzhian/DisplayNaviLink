package com.anyonavy.displaynavi.data.appinfo;

import android.graphics.drawable.Drawable;

/**
 * Created by ZuiC on 2017/1/18.
 */

public class AppInfo {

    private String mAppName;
    private String mAppPackageName;
    private String mAppVersionCode;
    private String mAppVersionName;
    private int mAppIcon;
    private int mAppPosition;
    private boolean isInstalled;
    public Drawable installAppIcon;

    public AppInfo() {

    }

    public AppInfo(String mAppName, String mAppPackageName, String mAppVersionCode, String mAppVersionName
    , int mAppIcon, int mAppPosition, boolean isInstalled){

        this.mAppName = mAppName;
        this.mAppPackageName = mAppPackageName;
        this.mAppVersionCode = mAppVersionCode;
        this.mAppVersionName = mAppVersionName;
        this.mAppIcon = mAppIcon;
        this.mAppPosition = mAppPosition;
        this.isInstalled = isInstalled;
    }

    public String getAppPackageName(){
        return this.mAppPackageName;
    }
    public void setAppPackageName(String packageName){
        this.mAppPackageName = packageName;
    }

    public boolean getIsInstalled(){
        return this.isInstalled;
    }
    public void setIsInstalled(boolean isInstalled){
        this.isInstalled = isInstalled;
    }


    public String getAppName() {return mAppName;}
    public void setAppName(String mAppName) {this.mAppName = mAppName;}

    public String getAppVersionCode() {return mAppVersionCode;}
    public void setAppVersionCode(String mAppVersionCode) {this.mAppVersionCode = mAppVersionCode;}

    public String getAppVersionName() {return mAppVersionName;}
    public void setAppVersionName(String mAppVersionName) {this.mAppVersionName = mAppVersionName;}

    public int getAppIcon(){return mAppIcon;}

    public Drawable getInstallAppIcon(){return installAppIcon;}
    public void setInstallAppIcon(Drawable mAppIcon) {this.installAppIcon = mAppIcon;}


    public int getAppPosition() {return mAppPosition;}
    public void setAppPosition(int mAppPosition) {this.mAppPosition = mAppPosition;}

}
