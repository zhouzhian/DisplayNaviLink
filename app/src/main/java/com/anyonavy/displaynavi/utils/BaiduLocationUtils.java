package com.anyonavy.displaynavi.utils;

import com.anyonavy.displaynavi.MyApplication;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by zza on 2018/4/2.
 */

public class BaiduLocationUtils {
    private LocationClient mLocationClient;
    private static BaiduLocationUtils mBaiduLocationUtils = null;
    private MyLocationListener myListener = new MyLocationListener();
    private OnReceiverLoction onReceiverLoction;

    private BaiduLocationUtils(){
        mLocationClient = new LocationClient(MyApplication.getContextObject());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        initLocationClient();
    }

    public static BaiduLocationUtils getInstance(){
        synchronized(BaiduLocationUtils.class){
            if(mBaiduLocationUtils == null){
                mBaiduLocationUtils = new BaiduLocationUtils();
            }
        }
        return mBaiduLocationUtils;
    }

    public void getLocation() {
        if (HttpUtils.isConnected(MyApplication.getContextObject())){
            mLocationClient.start();
        }
    }

    public void stopLocation(){
        mLocationClient.stop();
    }

    private void initLocationClient() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(false);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(false);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
        option.setIgnoreKillProcess(false);//可选，定位SDK内部是一个service，并放到了独立进程。//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.SetIgnoreCacheException(false);//可选，设置是否收集Crash信息，默认收集，即参数为false
        option.setWifiCacheTimeOut(5*60*1000);//可选，7.2版本新增能力//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
        option.setEnableSimulateGps(false);//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        mLocationClient.setLocOption(option);//将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
    }

    class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            onReceiverLoction.setLoction(longitude,latitude);
        }
    }

    public interface OnReceiverLoction{
        void setLoction(double longitude,double latitude);
    }

    public void setOnReceiverLoction(OnReceiverLoction onReceiverLoction){
        this.onReceiverLoction=onReceiverLoction;
    }
}
