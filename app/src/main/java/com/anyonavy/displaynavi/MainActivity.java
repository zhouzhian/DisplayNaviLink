package com.anyonavy.displaynavi;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.anyonavy.displaynavi.view.bottombar.BottomBarView;
import com.anyonavy.displaynavi.view.display.DisplayView;
import com.anyonavy.displaynavi.view.topbar.TopBarView;

public class MainActivity extends Activity {
    private DisplayView dvDisplayApp;
    private ImageView ivShowApp;
    private TopBarView tvTopbarView;
    private BottomBarView bvBottombarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        dvDisplayApp = (DisplayView) findViewById(R.id.dv_display_app);
        ivShowApp = (ImageView) findViewById(R.id.iv_show_app);
        tvTopbarView = (TopBarView) findViewById(R.id.tv_topbar_view);
        bvBottombarView = (BottomBarView) findViewById(R.id.bv_bottombar_view);


        dvDisplayApp.setOnOpenIconListener(new DisplayView.OnOpenIconListener() {
            @Override
            public void openMusic() {
                if (dvDisplayApp.getVisibility()== View.VISIBLE){
                    dvDisplayApp.setVisibility(View.GONE);
                    ivShowApp.setVisibility(View.VISIBLE);
                    ivShowApp.setBackground(getResources().getDrawable(R.drawable.bg_app_music));
                }
            }

            @Override
            public void openRadio() {
                if (dvDisplayApp.getVisibility()== View.VISIBLE){
                    dvDisplayApp.setVisibility(View.GONE);
                    ivShowApp.setVisibility(View.VISIBLE);
                    ivShowApp.setBackground(getResources().getDrawable(R.drawable.bg_app_radio));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyApplication)getApplication()).setStartActivityName(getClass().getName());
        boolean reloadAppInfo = ((MyApplication)getApplication()).isBackendReturn();
        dvDisplayApp.onResume(reloadAppInfo);
        tvTopbarView.startUpdateTime();
        bvBottombarView.startUpdateDate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tvTopbarView.stopUpdateTime();
        bvBottombarView.stopUpdateDate();
    }

    @Override
    public void onBackPressed() {
        dvDisplayApp.onBackPressed();
        if (ivShowApp.isShown()){
            ivShowApp.setVisibility(View.GONE);
            dvDisplayApp.setVisibility(View.VISIBLE);
        }
    }
}
