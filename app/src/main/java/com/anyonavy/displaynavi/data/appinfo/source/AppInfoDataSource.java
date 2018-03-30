package com.anyonavy.displaynavi.data.appinfo.source;


import com.anyonavy.displaynavi.data.appinfo.AppInfo;

import java.util.List;

/**
 * Created by ZuiC on 2017/1/18.
 */

public interface AppInfoDataSource {

    interface LoadAppInfoCallBack{
        void getInstalledAppNum(int num);
    }

    interface LoadInstalledAppInfoCallBack{
        void getInstalledAppInfo(List<AppInfo> installedAppInfo);
    }


    void loadInstalledAppInfo(LoadInstalledAppInfoCallBack callBack);
    void loadAppInfo(LoadAppInfoCallBack callBack);
    void reloadAppInfo();
}
