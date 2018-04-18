package com.anyonavy.displaynavi.view.bottombar;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.anyonavy.displaynavi.data.weatherInfo.HeWeather6Bean;
import com.anyonavy.displaynavi.data.weatherInfo.UpdateBean;
import com.anyonavy.displaynavi.utils.BaiduLocationUtils;
import com.anyonavy.displaynavi.utils.ContsUtils;
import com.anyonavy.displaynavi.utils.DateUtils;
import com.anyonavy.displaynavi.utils.WeatherUtils;

import java.io.StringReader;


/**
 * Created by zza on 2018/2/27.
 */

public class BottomPresenter implements BaseBottomContract.Presenter {

    private  BaseBottomContract.View bottomBarview;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            case ContsUtils.MSG_WHAT_WEATHER_INFO:
                String weather = msg.getData().getString(ContsUtils.MSG_KEY_WEATHER_INFO);
                bottomBarview.setCurWeather(weather);
            break;
            default:
            break;
            }

        }
    };
    private Runnable mUpdateDate;
    private Runnable mUpdateWeather;

    public BottomPresenter(BaseBottomContract.View view) {
        this.bottomBarview=view;
    }

    @Override
    public void start() {
        bottomBarview.setPresenter(this);
        final DateUtils dateUtils = DateUtils.getInstance();
        final BaiduLocationUtils baiduLocationUtils = BaiduLocationUtils.getInstance();
        baiduLocationUtils.setOnReceiverLoction(new BaiduLocationUtils.OnReceiverLoction() {
            @Override
            public void setLoction(final double longitude, final double latitude) {
                //开启线程进行网络请求网络请求
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String weatherInfos = WeatherUtils.getWeatherInfos(longitude,latitude);
                        HeWeather6Bean weatherBean = parseWeatherInfos(weatherInfos);
                        if (weatherBean.getStatus().equals("ok")){
                            Message msg=Message.obtain();
//                            weatherBean.getBasic()
                            String infos = weatherBean.getBasic().getParent_city()+" "+
                                    weatherBean.getBasic().getLocation()+"\n"+
                                    weatherBean.getNow().getCond_txt()+" "+
                                    weatherBean.getNow().getWind_dir()+
                                    weatherBean.getNow().getWind_sc()+"级 温度"+
                                    weatherBean.getNow().getTmp()+"°";
                            msg.getData().putString(ContsUtils.MSG_KEY_WEATHER_INFO,infos);
                            msg.what=ContsUtils.MSG_WHAT_WEATHER_INFO;
                            mHandler.sendMessage(msg);
                        }else {
                            baiduLocationUtils.getLocation();
                        }
                    }
                }).start();
            }
        });

        mUpdateWeather=new Runnable(){
            @Override
            public void run() {
                baiduLocationUtils.getLocation();
                mHandler.postDelayed(this,3600000);
            }
        };

        mUpdateDate = new Runnable() {
            @Override
            public void run() {
                bottomBarview.setCurDate(dateUtils.getDate());
                mHandler.postDelayed(this,1000);
            }
        };
    }

    private HeWeather6Bean parseWeatherInfos(String weatherInfos) {
        JSONObject jsonObject= JSON.parseObject(weatherInfos);
        String heWeather6 = jsonObject.getString("HeWeather6");
        String newHeWeather6 = heWeather6.substring(1, heWeather6.length()-1);
        HeWeather6Bean heWeatherBean = JSON.parseObject(newHeWeather6, HeWeather6Bean.class);
        return heWeatherBean;
//        Log.e(ContsUtils.TAG, "heWeatherBean = "+heWeatherBean);

//        JSONObject heWeather6JsonObject= JSON.parseObject(newHeWeather6);
//        JSONReader heWeather6Reader = new JSONReader(new StringReader(heWeather6JsonObject+""));
//        heWeather6Reader.startObject();
//        while (heWeather6Reader.hasNext()){
//            String heWeatherKey = heWeather6Reader.readString();
//            if (heWeatherKey.equals("basic")){
//                heWeather6Reader.startObject();
//                while (heWeather6Reader.hasNext()){
//                    String basicKey = heWeather6Reader.readString();
//                    String basicValue = heWeather6Reader.readObject().toString();
//                    Log.e(ContsUtils.TAG, "basic: basicKey="+ basicKey+",basicValue="+basicValue);
//                }
//                heWeather6Reader.endObject();
//
//            }else if(heWeatherKey.equals("now")){
//                heWeather6Reader.startObject();
//                while (heWeather6Reader.hasNext()){
//                    String nowKey = heWeather6Reader.readString();
//                    String nowValue = heWeather6Reader.readObject().toString();
//                    Log.e(ContsUtils.TAG, "now: nowKey="+ nowKey+",nowValue="+nowValue);
//                }
//                heWeather6Reader.endObject();
//
//            }else if(heWeatherKey.equals("status")){
//                String statuscValue = heWeather6Reader.readString();
//                Log.e(ContsUtils.TAG, "status: statuscValue="+ statuscValue);
//            }else if(heWeatherKey.equals("update")){
//                heWeather6Reader.startObject();
//                while (heWeather6Reader.hasNext()){
//                    String updateKey = heWeather6Reader.readString();
//                    String updateValue = heWeather6Reader.readObject().toString();
//                    Log.e(ContsUtils.TAG, "update: updateKey="+ updateKey+",updateValue="+updateValue);
//                }
//                heWeather6Reader.endObject();
//            }
//        }
//        heWeather6Reader.endObject();
    }

    @Override
    public void startUpdateDate() {
        mHandler.postDelayed(mUpdateDate,1000);
        mHandler.postDelayed(mUpdateWeather,1000);
    }

    @Override
    public void stopUpdateDate() {
        mHandler.removeCallbacks(mUpdateDate);
        mHandler.removeCallbacks(mUpdateWeather);
    }
}
