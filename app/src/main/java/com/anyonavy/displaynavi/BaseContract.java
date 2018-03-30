package com.anyonavy.displaynavi;

import android.content.Context;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by zza on 2018/2/27.
 */

public interface BaseContract {

    interface BasePresenter{
        void start();
    }
    interface BaseView<T>{
        void initView(Context context);
        void setPresenter(T presenter);
    }
    interface BaseMode{

    }
}
