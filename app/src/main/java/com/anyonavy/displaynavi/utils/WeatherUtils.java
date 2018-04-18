package com.anyonavy.displaynavi.utils;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by zza on 2018/4/2.
 */

public class WeatherUtils {

    private static String url = ContsUtils.WEATHER_URL + ContsUtils.WEATHER_URL_PARAM_KEY +
            ContsUtils.WEATHER_URL_PARAM_LOCATION;


    public static String getWeatherInfos(double longitude,double latitude){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url+longitude+","+latitude).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
