package com.anyonavy.displaynavi.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zza on 2017/1/19.
 */

public class ToastManagerUtils {

    private Toast toast;
    private static ToastManagerUtils INSTANCE;

    public static ToastManagerUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ToastManagerUtils();
        }
        return INSTANCE;
    }

    private ToastManagerUtils(){

    }

    public void showTextToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
