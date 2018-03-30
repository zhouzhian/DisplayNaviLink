package com.anyonavy.displaynavi.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.anyonavy.displaynavi.MyApplication;
import com.anyonavy.displaynavi.data.appinfo.AppInfo;

import java.util.ArrayList;
import java.util.List;

import static com.anyonavy.displaynavi.utils.ContsUtils.*;


/**
 * Created by ZuiC on 2017/2/7.
 */

public class AppManagerUtils {

    private static AppManagerUtils INSTANCE = null;
    private Context mContext;


    public static AppManagerUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppManagerUtils();
        }
        return INSTANCE;
    }

    private AppManagerUtils(){
        mContext = MyApplication.getContextObject();
    }

    public void launchActivity(String packageName) {
        PackageManager packageManager = mContext.getPackageManager();
        Intent appIntent = packageManager.getLaunchIntentForPackage(packageName);
        mContext.startActivity(appIntent);
    }

    public boolean appIsExist(String packageName) {
        List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if (p.versionName == null) {
                continue;
            }
            if (p.packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据包名获取相应的应用信息
     * @param context
     * @param packageName
     * @return 返回包名所对应的应用程序的名称。
     */
    public static String getProgramNameByPackageName(Context context, String packageName) throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        String name = null;
        try {
            name = pm.getApplicationLabel(
                    pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 获取手机所有应用信息
     *
     * @param allApplist
     * @param context
     */
    public static void getAllProgramInfo(List<AppInfo> allApplist, Context context) {
        getAllProgramInfo(allApplist, context, DEFAULT_APP);
    }

    /**
     * 获取手机所有应用信息
     *
     * @param applist
     * @param context
     * @param type 标识符 是否区分系统和非系统应用
     */
    public static void getAllProgramInfo(List<AppInfo> applist, Context context, int type) {

        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tmpInfo = new AppInfo();
            tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(
                    context.getPackageManager()).toString());
            tmpInfo.setAppPackageName(packageInfo.packageName);
            tmpInfo.setAppVersionName(packageInfo.versionName);
            tmpInfo.setAppVersionCode(""+packageInfo.versionCode);
            tmpInfo.setInstallAppIcon(packageInfo.applicationInfo.loadIcon(
                    context.getPackageManager()));

            switch (type) {
                case 0x1002:
                    if (!isSystemAPP(packageInfo)) {
                        applist.add(tmpInfo);
                    }
                    break;
                case 0x1001:
                    if (isSystemAPP(packageInfo)) {
                        applist.add(tmpInfo);
                    }
                    break;
                default:
                    applist.add(tmpInfo);
                    break;
            }
        }
    }

    /**
     * 获取所有系统应用信息
     *
     * @param context
     * @return
     */
    public static List<AppInfo> getAllSystemProgramInfo(Context context) {
        List<AppInfo> systemAppList = new ArrayList<AppInfo>();
        getAllProgramInfo(systemAppList, context, SYSTEM_APP);
        return systemAppList;
    }

    /**
     * 获取所有非系统应用信息
     *
     * @param context
     * @return
     */
    public static List<AppInfo> getAllNonsystemProgramInfo(Context context) {
        List<AppInfo> nonsystemAppList = new ArrayList<AppInfo>();
        getAllProgramInfo(nonsystemAppList, context, NONSYSTEM_APP);
        return nonsystemAppList;
    }

    /**
     * 判断是否是系统应用
     *
     * @param packageInfo
     * @return
     */
    public static Boolean isSystemAPP(PackageInfo packageInfo) {
        if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // 非系统应用
            return false;
        } else { // 系统应用
            return true;
        }
    }

}
