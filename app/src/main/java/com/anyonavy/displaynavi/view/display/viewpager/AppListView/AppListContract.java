package com.anyonavy.displaynavi.view.display.viewpager.AppListView;



import com.anyonavy.displaynavi.BaseContract;
import com.anyonavy.displaynavi.data.appinfo.AppInfo;

import java.util.List;


/**
 * Created by ZuiC on 2017/1/16.
 */

public interface AppListContract{

    interface view extends BaseContract.BaseView<Presenter> {
        void initAppListContent(List<AppInfo> appInfoList,int num);
    }

    interface Presenter extends BaseContract.BasePresenter{
    }
}
