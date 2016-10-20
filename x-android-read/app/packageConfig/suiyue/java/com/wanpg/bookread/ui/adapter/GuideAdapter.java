package com.wanpg.bookread.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class GuideAdapter extends PagerAdapter {



    private List<ImageView> list;

    public GuideAdapter(List<ImageView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null ?0:list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }


    /**
     * 初始化引导页
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = list.get(position);

          container.addView(imageView);


        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ImageView imageView = list.get(position);
        container.removeView(imageView);
    }
}
