package com.anyonavy.displaynavi.view.display.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.anyonavy.displaynavi.view.display.viewpager.AppListView.AppListView;
import com.anyonavy.displaynavi.view.display.viewpager.homeviewpager.HomeViewpager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zza on 2018/2/27.
 */

public class DisplayViewpager extends ViewPager{

    private List<View> mViewList = new ArrayList<>();
    private ViewPagerAdapter mViewPagerAdapter;
    private Context mContext;
    private HomeViewpager homeViewpager;
    private OnOpenIconListener mOnOpenIconListener;
    private int parentWidth;
    private int parentHeight;

    public DisplayViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    public DisplayViewpager(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mContext=context;
        homeViewpager=new HomeViewpager(context);
        mViewList.add(homeViewpager);
        homeViewpager.setOnOpenIconListener(new HomeViewpager.OnOpenIconListener() {
            @Override
            public void openMusic() {
                mOnOpenIconListener.openMusic();
            }

            @Override
            public void openRadio() {
                mOnOpenIconListener.openRadio();
            }
        });

        parentWidth=this.getWidth();
        parentHeight=this.getHeight();

    }

    public boolean onBackPressed() {
        boolean result = true;
        for (int i = 0; i < mViewList.size() - 1; i++){
            if (!((AppListView)mViewList.get(i + 1)).onBackPressed()){
                return false;
            }
        }
        //非编辑状态下，返回主界面
        if(result){
            this.setCurrentItem(0);
        }
        return result;
    }

    public void updatePageSize(int size) {
        if (mViewPagerAdapter == null){//如果adapter为null要new adapter，new之前给adapter的数据准备好
            //mViewList初始化的时候添加了主界面，这在根据应用数量添加拖动页面
            for (int i = 0; i < size - 1; i++){
                mViewList.add(new AppListView(mContext).applistViewStart(i));
            }
            mViewPagerAdapter = new ViewPagerAdapter();
            setAdapter(mViewPagerAdapter);//this.setAdapter(mViewPagerAdapter)
        }else {//当数量有变化了，再调用更新的时候
            List<View> temp = new ArrayList<>();//先建立一个模板对象
            temp.add(mViewList.get(0));//还把主页加进去
            for (int i = 0; i < size - 1; i++){//再重新计算添加。
                if (mViewList.get(i + 1) == null){//如果列表对象的页数存在，直接付给模板对象
                    temp.add(mViewList.get(i + 1));
                }else {//如果不存在，再new一个拖动页。
                    temp.add(new AppListView(mContext));
                }
            }
            mViewList.clear();//把列表对象清空
            mViewList = temp;//把模板对象赋给列表对象
            mViewPagerAdapter.notifyDataSetChanged();//刷新viewpage
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(mViewList.get(position ));
        }
    }

    public interface OnOpenIconListener{
        void openMusic();
        void openRadio();
    }

    public void setOnOpenIconListener(OnOpenIconListener onOpenIconListener){
        mOnOpenIconListener=onOpenIconListener;
    }

}
