package com.anyonavy.displaynavi.view.topbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anyonavy.displaynavi.R;
import com.anyonavy.displaynavi.utils.ContsUtils;


import static android.R.attr.value;
import static com.anyonavy.displaynavi.R.id.tv_topbar_time;

/**
 * Created by zza on 2018/2/27.
 */

public class TopBarView extends LinearLayout implements BaseTopbarContract.BaseTopBarView, View.OnClickListener {

    private Context mContext;
    private TextView tvTopbarTime;
    private ImageView ivTopbarBluetooth;
    private ImageView ivTopbarWifi;
    private BaseTopbarContract.BaseTopBarPresenter presenter;

    public TopBarView(Context context) {
        super(context);
        initView(context);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TopBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public void initView(Context context) {
        mContext=context;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_topbar, this);

        tvTopbarTime = (TextView) view.findViewById(tv_topbar_time);
        ivTopbarBluetooth = (ImageView) view.findViewById(R.id.iv_topbar_bluetooth);
        ivTopbarWifi = (ImageView) view.findViewById(R.id.iv_topbar_wifi);
        new TopbarPresenter(this).start();

        tvTopbarTime.setOnClickListener(this);
        ivTopbarBluetooth.setOnClickListener(this);
        ivTopbarWifi.setOnClickListener(this);
    }

    public void startUpdateTime() {
        presenter.startUpdateTime();
    }

    public void stopUpdateTime() {
        presenter.stoptUpdateTime();
    }

    public void updateBluAndWifiStatus(){
        presenter.updateBluAndWifiStatus();
    }
    public void RemoveBroadcastRec(){
        presenter.removeBroadcastRec();
    }

    @Override
    public void setPresenter(BaseTopbarContract.BaseTopBarPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void setCurTime(String time) {
        tvTopbarTime.setText(time);
    }

    @Override
    public void setCurBluStatus(int status) {
        switch (status) {
            case ContsUtils.BLU_STATUS_CLOSE:
                ivTopbarBluetooth.setBackgroundColor(Color.GRAY);
                //ivTopbarBluetooth.setImageBitmap(null);//暂无图片用background表示状态变化
                break;
            case ContsUtils.BLU_STATUS_OPEN:
                ivTopbarBluetooth.setBackgroundColor(Color.WHITE);
                //ivTopbarBluetooth.setImageBitmap(null);
                break;
            case ContsUtils.BLU_STATUS_CONNECTED:
                ivTopbarBluetooth.setBackgroundColor(Color.BLUE);
                //ivTopbarBluetooth.setImageBitmap(null);
                break;
            case ContsUtils.BLU_STATUS_NULL:
                ivTopbarBluetooth.setBackgroundColor(Color.RED);
                //ivTopbarBluetooth.setImageBitmap(null);

                break;
        }
    }

    @Override
    public void setCurWifiStatus(int wifiStatus) {
        switch (wifiStatus) {
            case 0:

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_topbar_time:

                break;
            case R.id.iv_topbar_bluetooth:
                presenter.openBluIntent();
                break;
            case R.id.iv_topbar_wifi:
                presenter.openWifiIntent();
                break;
        }
    }
}
