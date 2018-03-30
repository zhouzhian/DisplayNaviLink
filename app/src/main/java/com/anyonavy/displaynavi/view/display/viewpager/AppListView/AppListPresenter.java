package com.anyonavy.displaynavi.view.display.viewpager.AppListView;



import com.anyonavy.displaynavi.data.appinfo.AppInfo;
import com.anyonavy.displaynavi.data.appinfo.source.AppInfoDataSource;
import com.anyonavy.displaynavi.data.appinfo.source.AppInfoRepository;

import java.util.List;


/**
 * Created by ZuiC on 2017/1/16.
 */

public class AppListPresenter implements AppListContract.Presenter {

    private static final String TAG = "AppListPresenter";
    private final AppListView mAppListView;
    private final AppInfoRepository mAppInfoRepository;
    private List<AppInfo> mAppListInfo;

    public AppListPresenter(AppListView appListView, AppInfoRepository appInfoRepository){
        this.mAppListView = appListView;
        this.mAppInfoRepository = appInfoRepository;
        mAppListView.setPresenter(this);
    }


    @Override
    public void start() {
        loadInstalledAppInfo();
    }

    private void loadInstalledAppInfo(){
        mAppInfoRepository.loadInstalledAppInfo(new AppInfoDataSource.LoadInstalledAppInfoCallBack() {
            @Override
            public void getInstalledAppInfo(List<AppInfo> installedAppInfo) {
                mAppListInfo = installedAppInfo;
            }
        });
        mAppListView.initAppListContent(mAppListInfo);
    }
}
