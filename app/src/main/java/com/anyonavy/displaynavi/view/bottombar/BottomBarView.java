package com.anyonavy.displaynavi.view.bottombar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anyonavy.displaynavi.R;

/**
 * Created by zza on 2018/2/27.
 */

public class BottomBarView extends LinearLayout implements BaseBottomContract.View{

    private Context mContext;
    private BaseBottomContract.Presenter presenter;
    private ImageView ivBottombarRing;
    private TextView tvBottombarDate;
    private TextView tvBottombarWeather;
    private ImageView ivBottombarSet;

    public BottomBarView(Context context) {
        super(context);
        initView(context);
    }

    public BottomBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public void initView(Context context) {
        mContext=context;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_bottombar, this);

        ivBottombarRing = (ImageView) view.findViewById(R.id.iv_bottombar_ring);
        tvBottombarWeather = (TextView) view.findViewById(R.id.tv_bottombar_weather);
        tvBottombarDate = (TextView) view.findViewById(R.id.tv_bottombar_date);
        ivBottombarSet = (ImageView) view.findViewById(R.id.iv_bottombar_set);

        new BottomPresenter(this).start();
    }

    public void startUpdateDate(){
        presenter.startUpdateDate();
    }
    public void stopUpdateDate(){
        presenter.stopUpdateDate();
    }

    @Override
    public void setPresenter(BaseBottomContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void setCurDate(String date) {
        tvBottombarDate.setText(date);
    }
}
