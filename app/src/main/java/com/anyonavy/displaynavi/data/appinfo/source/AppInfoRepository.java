package com.anyonavy.displaynavi.data.appinfo.source;


import android.util.Log;

import com.anyonavy.displaynavi.data.appinfo.AppInfo;
import com.anyonavy.displaynavi.data.appinfo.source.local.AppInfoLocalDataSource;
import com.anyonavy.displaynavi.utils.ContsUtils;

import java.util.ArrayList;
import java.util.List;

import static com.anyonavy.displaynavi.utils.ContsUtils.*;



public class AppInfoRepository implements AppInfoDataSource{

    private List<AppInfo> mCacheInstalledAppInfo;
    private List<AppInfo> mCacheAllAppInfo;
    private boolean mCacheDirty = false;

    private static AppInfoRepository INSTANCE = null;
    private int mInstalledAppNum;


    public static AppInfoRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppInfoRepository();
        }
        return INSTANCE;
    }

    private AppInfoRepository(){

    }


    @Override
    public void loadInstalledAppInfo(LoadInstalledAppInfoCallBack callBack) {
        if (mCacheInstalledAppInfo == null || mCacheDirty){
            loadAppInfo();
        }
        callBack.getInstalledAppInfo(mCacheInstalledAppInfo);
    }

    private void loadAppInfo() {
        if (mCacheAllAppInfo == null || mCacheDirty){
            mCacheAllAppInfo = AppInfoLocalDataSource.getInstance().getAppInfo();
            Log.e(TAG, "loadAppInfo: "+ mCacheAllAppInfo.size());
        }

        if (mCacheInstalledAppInfo == null || mCacheDirty){
            mCacheInstalledAppInfo = new ArrayList<>();
            mInstalledAppNum = 0;
            for (int i = 0; i < mCacheAllAppInfo.size(); i++){
//                if (mCacheAllAppInfo.get(i).getIsInstalled()){
//                    mInstalledAppNum++;
//                    mCacheInstalledAppInfo.add(mCacheAllAppInfo.get(i));
//                }
                mInstalledAppNum++;
                mCacheInstalledAppInfo.add(mCacheAllAppInfo.get(i));
            }
        }
        mCacheDirty = false;
    }

    @Override
    public void loadAppInfo(LoadAppInfoCallBack callBack) {
        if (mCacheInstalledAppInfo == null || mCacheAllAppInfo == null || mCacheDirty){
            loadAppInfo();
        }
        callBack.getInstalledAppNum(mInstalledAppNum);
    }

    @Override
    public void reloadAppInfo() {
        mCacheDirty = true;
    }
}
