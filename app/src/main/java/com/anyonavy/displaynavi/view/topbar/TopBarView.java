package com.anyonavy.displaynavi.view.topbar;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anyonavy.displaynavi.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zza on 2018/2/27.
 */

public class TopBarView extends LinearLayout implements BaseTopbarContract.BaseTopBarView {

    private Context mContext;
    private TextView tvTopbarTime;
    private ImageView ivTopbarBluetooth;
    private ImageView lvTopbarWifi;
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

        tvTopbarTime = (TextView) view.findViewById(R.id.tv_topbar_time);
        ivTopbarBluetooth = (ImageView) view.findViewById(R.id.iv_topbar_bluetooth);
        lvTopbarWifi = (ImageView) view.findViewById(R.id.lv_topbar_wifi);
        new TopbarPresenter(this).start();

    }

    public void startUpdateTime() {
        presenter.startUpdateTime();
    }

    public void stopUpdateTime() {
        presenter.stoptUpdateTime();
    }

    @Override
        public void setPresenter(BaseTopbarContract.BaseTopBarPresenter presenter) {
            this.presenter=presenter;
        }

    @Override
    public void setCurTime(String time) {
        tvTopbarTime.setText(time);
    }
}
