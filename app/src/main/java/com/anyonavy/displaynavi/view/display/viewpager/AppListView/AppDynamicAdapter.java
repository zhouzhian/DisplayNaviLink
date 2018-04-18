package com.anyonavy.displaynavi.view.display.viewpager.AppListView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyonavy.displaynavi.R;
import com.anyonavy.displaynavi.data.appinfo.AppInfo;
import com.anyonavy.displaynavi.utils.AppManagerUtils;
import com.anyonavy.displaynavi.widget.dynamicgrid.BaseDynamicGridAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZuiC on 2017/1/19.
 */

public class AppDynamicAdapter extends BaseDynamicGridAdapter {

    private static final String[] sPosition = {
            "1", "2", "3", "4", "5", "6"
    };

    private Map<String, AppInfo> appPositionInfo = new LinkedHashMap<>();


    public AppDynamicAdapter(Context context, List<AppInfo> appInfoList, int columnCount,
                             int applistPagernum) {
        super(context, new ArrayList<Object>(Arrays.asList(sPosition)), columnCount);
        int startNum = applistPagernum*6;
        int endNum = startNum+6;
        for (int i = startNum; i < endNum; i++){
            if (endNum > appInfoList.size()){
                appPositionInfo.put(sPosition[i-startNum], new AppInfo("NULL", "NULL", "NULL", "NULL" , -1 , -1 ,false));
            }else {
                appPositionInfo.put(sPosition[i-startNum], appInfoList.get(i));
            }
        }
//        for (int i = 0; i < 6 - appInfoList.size(); i++){
//            appPositionInfo.put(sPosition[i + appInfoList.size()], new AppInfo("NULL", "NULL", "NULL", "NULL" , -1 , -1 ,false));
//        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_gv_appicon, null);
            holder = new AppViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (AppViewHolder) convertView.getTag();
        }
        holder.build(getItem(position).toString());
        return convertView;
    }


    private class AppViewHolder implements View.OnClickListener {

        private ImageView ivAppIcon;
        private TextView tvAppName;
        private String appPosition;
        private String appPackageName;

        private AppViewHolder(View view) {
            ivAppIcon = (ImageView) view.findViewById(R.id.iv_app_icon);
            tvAppName = (TextView) view.findViewById(R.id.tv_app_name);
            ivAppIcon.setOnClickListener(this);
        }

        void build(String position) {
            AppInfo appInfo = appPositionInfo.get(position);
            if (!appInfo.getAppPackageName().equals("NULL")) {
//                ivAppIcon.setImageResource(appInfo.getAppIcon());
                ivAppIcon.setImageDrawable(appInfo.getInstallAppIcon());
                tvAppName.setText(appInfo.getAppName());
            } else {
                ivAppIcon.setImageResource(R.drawable.icon_blank);
                tvAppName.setText("");
            }
            appPosition = position;
            appPackageName = appInfo.getAppPackageName();
        }

        @Override
        public void onClick(View v) {
            if (!appPackageName.equals("NULL")) {
                AppManagerUtils.getInstance().launchActivity(appPackageName);
            }
        }
    }
}
