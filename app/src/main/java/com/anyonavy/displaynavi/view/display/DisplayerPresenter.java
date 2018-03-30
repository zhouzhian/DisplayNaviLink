package com.anyonavy.displaynavi.view.display;



import com.anyonavy.displaynavi.data.appinfo.source.AppInfoDataSource;
import com.anyonavy.displaynavi.data.appinfo.source.AppInfoRepository;

/**
 * Created by zza on 2018/2/27.
 */

public class DisplayerPresenter implements BaseDisplayContract.BaseDisplayPresenter {

    private final BaseDisplayContract.BaseDisplayView displayView;
    private final AppInfoRepository appInfoRepository;

    public DisplayerPresenter(BaseDisplayContract.BaseDisplayView displayView, AppInfoRepository instance) {
        this.displayView=displayView;
        appInfoRepository=instance;
        this.displayView.setPresenter(this);
    }

    @Override
    public void start() {
        loadAppInfo(false);
    }

    public void loadAppInfo(boolean reloadAppInfo) {
        if (reloadAppInfo){
            appInfoRepository.reloadAppInfo();
        }
        loadAppInfo();
    }

    private void loadAppInfo(){
        appInfoRepository.loadAppInfo(new AppInfoDataSource.LoadAppInfoCallBack() {
            @Override
            public void getInstalledAppNum(int num) {
                //应用数量6的倍数，加上一个主页，如果9有余数，再加1页。各寄应用数量算出页数
                int size = num / 6 + (num % 6 != 0 ? 1 : 0) + 1;
                //页数传到mainview，mainview中的viewpager执行页数更新
                displayView.updatePageSize(size);
                displayView.updateDotIconNum();//点点数量
                displayView.updateDotIconState(0);//点点状态
            }
        });
    }

    public void onPageSelected(int position) {
        //viewpage页数发生变化，执行更新页数点点变化操作
        displayView.updateDotIconState(position);
    }

}
