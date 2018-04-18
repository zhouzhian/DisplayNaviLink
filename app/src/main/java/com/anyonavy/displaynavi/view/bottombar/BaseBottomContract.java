package com.anyonavy.displaynavi.view.bottombar;

import com.anyonavy.displaynavi.BaseContract;

/**
 * Created by zza on 2018/2/27.
 */

public interface BaseBottomContract {

    interface Presenter extends BaseContract.BasePresenter{
        void startUpdateDate();
        void stopUpdateDate();
    }

    interface View extends BaseContract.BaseView<Presenter>{
        void setCurDate(String date);

        void setCurWeather(String weatherInfos);
    }

}
