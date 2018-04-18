package com.anyonavy.displaynavi.view.display.viewpager.AppListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;


import com.anyonavy.displaynavi.R;
import com.anyonavy.displaynavi.data.appinfo.AppInfo;
import com.anyonavy.displaynavi.data.appinfo.source.AppInfoRepository;
import com.anyonavy.displaynavi.widget.dynamicgrid.DynamicGridView;

import java.util.List;


/**
 * Created by ZuiC on 2017/1/16.
 */

public class AppListView extends LinearLayout implements AppListContract.view {

    private Context mContext;
    private DynamicGridView gridAppList;
    private AppListContract.Presenter mPresenter;

    public AppListView(Context context) {
        super(context);
        initView(context);
    }

    public AppListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AppListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context){
        this.mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_display_applist_applistview, this);
        gridAppList = (DynamicGridView) view.findViewById(R.id.grid_applist);
    }

    public AppListView applistViewStart(int num){
        new AppListPresenter(this, AppInfoRepository.getInstance(),num).start();
        setListener();
        return this;
    }


    private void setListener(){

        gridAppList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridAppList.startEditMode(position);
                return true;
            }
        });

    }

    @Override
    public void initAppListContent(List<AppInfo> appInfoList,int applistPagernum) {
        gridAppList.setAdapter(new AppDynamicAdapter(mContext, appInfoList, 3,applistPagernum));
    }

    @Override
    public void setPresenter(AppListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public boolean onBackPressed() {
//        gridAppList.startEditMode();
//        gridAppList.stopEditMode();
       if (gridAppList.isEditMode()) {
            gridAppList.stopEditMode();
            return false;
        } else {
            return true;
        }
    }
}
