package com.anyonavy.displaynavi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * Created by zza on 2018/3/2.
 */

public class DateUtils {

    private static DateUtils mDateUtils = null;

    private DateUtils(){}
    public static DateUtils getInstance(){
        synchronized(DateUtils.class){
            if(mDateUtils == null){
                mDateUtils = new DateUtils();
            }
        }
        return mDateUtils;
    }


    public String getTime(){
        long time=System.currentTimeMillis();
        Date curDate=new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(curDate);
    }

    public String getDate(){
        long time=System.currentTimeMillis();
        Date curDate=new Date(time);// 获取当前时间
        // 获取并格式化当前时间
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(curDate);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String date=year+"年"+month+"月"+day+"日"+" 星期"+week;
        return date;
    }
}
