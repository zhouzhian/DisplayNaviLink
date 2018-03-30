package com.anyonavy.displaynavi.view.display.viewpager.homeviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anyonavy.displaynavi.R;
import com.anyonavy.displaynavi.utils.AppManagerUtils;
import com.anyonavy.displaynavi.utils.ContsUtils;
import com.anyonavy.displaynavi.utils.ToastManagerUtils;


/**
 * Created by zza on 2018/2/27.
 */

public class HomeViewpager extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    private LinearLayout lltBigIcon;
    private ImageView ivBigMusic;
    private ImageView ivBigRadio;
    private ImageView ivBigVehicle;
    private LinearLayout lltSmallIcon;
    private ImageView ivSmallNavi;
    private ImageView ivSmallWechat;
    private ImageView ivSmallBluetooth;
    private OnOpenIconListener onOpenIconListener;
    private AppManagerUtils appManagerUtils;

    public HomeViewpager(Context context) {
        super(context);
        initView(context);
    }

    public HomeViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeViewpager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_display_viewpager_home, this);

        lltBigIcon = (LinearLayout) view.findViewById(R.id.llt_big_icon);
        ivBigMusic = (ImageView) view.findViewById(R.id.iv_big_music);
        ivBigRadio = (ImageView) view.findViewById(R.id.iv_big_radio);
        ivBigVehicle = (ImageView) view.findViewById(R.id.iv_big_vehicle);
        lltSmallIcon = (LinearLayout) view.findViewById(R.id.llt_small_icon);
        ivSmallNavi = (ImageView) view.findViewById(R.id.iv_small_navi);
        ivSmallWechat = (ImageView) view.findViewById(R.id.iv_small_wechat);
        ivSmallBluetooth = (ImageView) view.findViewById(R.id.iv_small_bluetooth);

        appManagerUtils=AppManagerUtils.getInstance();
        setListener();
    }

    private void setListener() {
        ivBigMusic.setOnClickListener(this);
        ivBigRadio.setOnClickListener(this);
        ivBigVehicle.setOnClickListener(this);

        ivSmallNavi.setOnClickListener(this);
        ivSmallWechat.setOnClickListener(this);
        ivSmallBluetooth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.iv_big_music:
            if (true){
                //打开音乐
                onOpenIconListener.openMusic();
            }else {
                ToastManagerUtils.getInstance().showTextToast(mContext,"该应用未安装，请到应用商店中下载");
            }
        break;
        case R.id.iv_big_radio:
            if (true){
                //打开收音机
                onOpenIconListener.openRadio();
            }else {
                ToastManagerUtils.getInstance().showTextToast(mContext,"该应用未安装，请到应用商店中下载");
            }

        break;
        case R.id.iv_big_vehicle:
            if (false){
//                appManagerUtils.launchActivity(ContsUtils.PACKAGE_NAME_NAVI);
            }else {
                ToastManagerUtils.getInstance().showTextToast(mContext,"车况APP不存在");
            }
        break;
        case R.id.iv_small_navi:
            if (appManagerUtils.appIsExist(ContsUtils.PACKAGE_NAME_NAVI)){
                //打开导航
                appManagerUtils.launchActivity(ContsUtils.PACKAGE_NAME_NAVI);
            }else {
                ToastManagerUtils.getInstance().showTextToast(mContext,"导航APP不存在");
            }
        break;
        case R.id.iv_small_wechat:
            if (appManagerUtils.appIsExist(ContsUtils.PACKAGE_NAME_WEBCHAT)){
                //打开微信
                appManagerUtils.launchActivity(ContsUtils.PACKAGE_NAME_WEBCHAT);
            }else {
                ToastManagerUtils.getInstance().showTextToast(mContext,"同行易聊APP不存在");
            }
        break;
        case R.id.iv_small_bluetooth:
            if (appManagerUtils.appIsExist(ContsUtils.PACKAGE_NAME_BLUETOOTH)){
                //打开蓝牙
                appManagerUtils.launchActivity(ContsUtils.PACKAGE_NAME_BLUETOOTH);
            }else {
                ToastManagerUtils.getInstance().showTextToast(mContext,"蓝牙APP不存在");
            }
        break;
        }
    }

    public interface OnOpenIconListener{
        void openMusic();
        void openRadio();
    }

    public void setOnOpenIconListener(OnOpenIconListener onOpenIconListener){
        this.onOpenIconListener=onOpenIconListener;
    }
}
