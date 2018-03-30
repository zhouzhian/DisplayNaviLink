package com.anyonavy.displaynavi.view.display;

import com.anyonavy.displaynavi.BaseContract;

/**
 * Created by zza on 2018/3/2.
 */

public interface BaseDisplayContract {

    interface BaseDisplayView extends BaseContract.BaseView<BaseDisplayPresenter>{
        void updateDotIconState(int position);
        void updateDotIconNum();
        void updatePageSize(int size);
    }
    interface BaseDisplayPresenter extends BaseContract.BasePresenter {
        void onPageSelected(int position);
        void loadAppInfo(boolean reloadAppInfo);
    }
}
