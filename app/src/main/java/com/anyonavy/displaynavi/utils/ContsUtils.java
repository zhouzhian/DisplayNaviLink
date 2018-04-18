package com.anyonavy.displaynavi.utils;


/**
 * Created by zza on 2018/3/16.
 */

public class ContsUtils {
    public static String PACKAGE_NAME_BLUETOOTH = "com.autochips.bluetooth";
    public static String PACKAGE_NAME_WEBCHAT = "com.txznet.webchat";
    public static String PACKAGE_NAME_NAVI = "com.fundrive.navi";


    public static int DEFAULT_APP = 0x1000;
    public static int SYSTEM_APP = 0x1001;
    public static int NONSYSTEM_APP = 0x1002;
    public static String TAG = "zza";

    //https://free-api.heweather.com/s6/weather/now?location=116.40,39.9&key=9aba9801d8254819bf90b807ca8475a2
    public static String WEATHER_URL = "https://free-api.heweather.com/s6/weather/now";
    public static String WEATHER_URL_PARAM_KEY = "?key=9aba9801d8254819bf90b807ca8475a2";
    public static String WEATHER_URL_PARAM_LOCATION = "&location=";

    public static String MSG_KEY_WEATHER_INFO = "WEATHERINFO";
    public final static int MSG_WHAT_WEATHER_INFO = 0x201;

    public final static int BLU_STATUS_NULL = -1;
    public final static int BLU_STATUS_CLOSE = 0;
    public final static int BLU_STATUS_OPEN = 1;
    public final static int BLU_STATUS_CONNECTED = 2;
}
