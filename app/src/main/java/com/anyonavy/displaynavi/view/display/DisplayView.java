package com.anyonavy.displaynavi.view.display;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anyonavy.displaynavi.R;
import com.anyonavy.displaynavi.data.appinfo.source.AppInfoRepository;
import com.anyonavy.displaynavi.view.display.viewpager.DisplayViewpager;

/**
 * Created by zza on 2018/2/27.
 */

public class DisplayView extends LinearLayout implements BaseDisplayContract.BaseDisplayView {

    private Context mContext;
    private LinearLayout lltViewpagerDot;
    private DisplayViewpager vpDisplayViewpager;
    private OnOpenIconListener dvOpenIconListener;
    private int mCurSelectedPage,mPageSize;
    private ImageView mCurSelectedDot,mPreSelectedDot;
    private BaseDisplayContract.BaseDisplayPresenter mPresenter;

    public DisplayView(Context context) {
        super(context);
        initView(context);
    }

    public DisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DisplayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public void updateDotIconState(int position) {
        mCurSelectedPage = position;//当前页=传入页
        if (mPreSelectedDot != null){//如果上个选择页对象存在，上个页变为非选择状态
            mPreSelectedDot.setImageResource(R.drawable.icon_dot_unselected);
        }
        //当前页对应的点点对象拿到，
        mCurSelectedDot = ((ImageView)lltViewpagerDot.getChildAt(mCurSelectedPage));
        //给当前页点点更改当前页点点的选择状态
        mCurSelectedDot.setImageResource(R.drawable.icon_dot_selected);
        //当前页的点对象赋值给上一页的点对象，用于下一次的更新点点状态
        mPreSelectedDot = mCurSelectedDot;
    }

    @Override
    public void updateDotIconNum() {
        lltViewpagerDot.removeAllViews();//linearlayout先清除所有子控件
        for (int i = 0; i < mPageSize; i++){
            //开始添加子控件，点点图片
            ImageView dot_icon = new ImageView(mContext);
            //布局自适应大小
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i < mPageSize - 1){
                //非最后一个点点都距右20，参数单位是像素px
                layoutParams.setMargins(0, 0, 20, 0);
            }
            //view添加布局参数
            dot_icon.setLayoutParams(layoutParams);
            //view添加src
            dot_icon.setImageResource(R.drawable.icon_dot_unselected);
            //添加view
            lltViewpagerDot.addView(dot_icon);
        }
    }

    @Override
    public void updatePageSize(int size) {
        mPageSize = size;
        vpDisplayViewpager.updatePageSize(size);
    }


    @Override
    public void initView(Context context) {
        this.mContext = context;
        mCurSelectedDot = null;
        mPreSelectedDot = null;
        mCurSelectedPage = 0;
        mPageSize = 0;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_display, this);

        lltViewpagerDot = (LinearLayout) view.findViewById(R.id.llt_viewpager_dot);
        vpDisplayViewpager = (DisplayViewpager) findViewById(R.id.vp_display_viewpager);

        new DisplayerPresenter(DisplayView.this, AppInfoRepository.getInstance()).start();

        vpDisplayViewpager.setOnOpenIconListener(new DisplayViewpager.OnOpenIconListener() {
            @Override
            public void openMusic() {
                dvOpenIconListener.openMusic();
            }

            @Override
            public void openRadio() {
                dvOpenIconListener.openRadio();
            }
        });

        vpDisplayViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setPresenter(BaseDisplayContract.BaseDisplayPresenter presenter) {
        mPresenter=presenter;
    }

    public boolean onBackPressed() {
        return vpDisplayViewpager.onBackPressed();
    }

    public void onResume(boolean reloadAppInfo) {
        if (reloadAppInfo){
            mPresenter.loadAppInfo(reloadAppInfo);
        }
    }

    public interface OnOpenIconListener{
        void openMusic();
        void openRadio();
    }

    public void setOnOpenIconListener(OnOpenIconListener onOpenIconListener){
        dvOpenIconListener=onOpenIconListener;
    }
}
