package com.wanpg.bookread.ui.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wanpg.bookread.R;
import com.wanpg.bookread.ui.MainActivity;
import com.wanpg.bookread.ui.adapter.GuideAdapter;
import com.wanpg.bookread.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/6/2.
 */
public class GuideActivity extends Activity{


    private ViewPager viewPager;
    private Button button;


    //图片初始化
    private int[] bitmaps =new int[]{

            R.drawable.bg_guide_01,
            R.drawable.bg_guide_02,
            R.drawable.bg_guide_03,
            R.drawable.bg_guide_04,

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);

        viewPager = (ViewPager) findViewById(R.id.guide_vp);
        button = (Button) findViewById(R.id.guide_btn);


        //资源初始化
        List<ImageView> list = new ArrayList<>();

        for (int i = 0; i < bitmaps.length; i++) {

            ImageView imageView = new ImageView(this);
            //填充控件
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView.setImageResource(bitmaps[i]);
            list.add(imageView);


        }




    //设置adapter
    GuideAdapter adapter = new GuideAdapter(list);
    viewPager.setAdapter(adapter);


    // viewPager.setOnPageChangeListener();



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==bitmaps.length-1){

                    button.setVisibility(View.VISIBLE);
                }else{

                    button.setVisibility(View.GONE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });






    //按钮点击事件
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(GuideActivity.this,MainActivity.class);
            startActivity(intent);


            //点击立刻体验修改把SharedPreferences的值是否第一次使用改成false
            SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_FIRST_USED, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(Constants.PREFERENCE_FIRST_USED_KEY,false);
            editor.commit();

            //为了避免从HomeActivity返回的时候又到了当前这个界面
            finish();

        }
    });




}



}
