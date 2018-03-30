package com.anyonavy.displaynavi;

import android.content.Context;

/**
 * Created by ZuiC on 2017/1/18.
 */

public class MyApplication extends android.app.Application {

    private static Context mApplicationContext;
    private String mActiveActivityName = "";
    private boolean mIsBackendReturn;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }

    public static Context getContextObject(){
        return mApplicationContext;
    }

    public void setStartActivityName(String name) {
        if(name.compareTo(mActiveActivityName) == 0) {
            mIsBackendReturn = true;
        } else {
            mIsBackendReturn = false;
        }
        mActiveActivityName = name;
    }

    public boolean isBackendReturn() {
        return mIsBackendReturn;
    }
}
