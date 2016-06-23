package com.wanpg.bookread.ui.hall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wanpg.bookread.BaseFragment;
import com.wanpg.bookread.R;
import com.wanpg.bookread.ui.adapter.GuideAdapter;
import com.wanpg.bookread.ui.adapter.StoreGridAdapter;
import com.wanpg.bookread.widget.MyGridView;
import com.wanpg.bookread.widget.Search_SoftAboutDialog;
import com.wanpg.bookread.widget.Search_SoftAboutDialogone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/11.
 */
public class StoreFragmentone extends BaseFragment {


    private View parent;

    private GridView gr_tj;
    private GridView gr_nv;
    private GridView gr_man;

    private String[] info_Names={"虚空里的盛宴","清风明月","心缘","幽默的智慧"};
    private Integer[] info_icon={
            R.drawable.name_5,R.drawable.name_6,R.drawable.name_7,R.drawable.name_8};


    private String[] info_Names_nv={"不经意的成长","红山女神故乡","有一种心情叫心酸","遥远的天堂"};
    private Integer[] info_icon_nv={
            R.drawable.bjydcz,R.drawable.hsnsgx,R.drawable.yyzxqjxs,R.drawable.yydtt};

    private String[] info_Names_man={"生死华尔街","给自己一片悬崖","窗外窗内","生死十七天"};
    private Integer[] info_icon_man={
            R.drawable.sshej,R.drawable.gjjypxy,R.drawable.clcw,R.drawable.sssqt};

    private ArrayList<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
    private ArrayList<Map<String,Object>> listItems_nv = new ArrayList<Map<String, Object>>();
    private ArrayList<Map<String,Object>> listItems_man = new ArrayList<Map<String, Object>>();






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(parent==null){
          //  parent = inflater.inflate(R.layout.layout_storefragmentone, null);

            parent = inflater.inflate(R.layout.layout_storeone, null);
            initData();
        }else{
            ((ViewGroup)parent.getParent()).removeView(parent);
        }
        return parent;

    }

    private void initData() {

        gr_tj = (MyGridView) parent.findViewById(R.id.store_grid_tj);  //推荐区
        gr_nv = (MyGridView) parent.findViewById(R.id.store_grid_nv); //女生区
        gr_man = (MyGridView) parent.findViewById(R.id.store_grid_man); //男生区



        inittj(); //推荐区

        initnv();//女生区

        inttman();//男生区
    }

    private void inttman() {


        for(int i=0;i<info_Names_man.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",info_icon_man[i] );
            map.put("name", info_Names_man[i]);
            listItems_man.add(map);
        }

        StoreGridAdapter storeGridAdapter = new StoreGridAdapter(getActivity(),listItems_man);
        gr_man.setAdapter(storeGridAdapter);

        gr_man.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* Search_SoftAboutDialog aboutDialog = new Search_SoftAboutDialog(getActivity(), R.style.about_dialog);
                Window win = aboutDialog.getWindow();

                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.x = 0;
                layoutParams.y = 0;
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                win.setAttributes(layoutParams);
                aboutDialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
                aboutDialog.show();*/


                final  Search_SoftAboutDialogone ab = new Search_SoftAboutDialogone(getActivity());

                ab.setIcon((Integer) listItems_man.get(position).get("img"));
                ab.setTitle( listItems_man.get(position).get("name")+"");

                ab.setConfirm();
            }
        });


    }

    private void initnv() {

        for(int i=0;i<info_Names_nv.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",info_icon_nv[i] );
            map.put("name", info_Names_nv[i]);
            listItems_nv.add(map);
        }

        StoreGridAdapter storeGridAdapter = new StoreGridAdapter(getActivity(),listItems_nv);
        gr_nv.setAdapter(storeGridAdapter);

        gr_nv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  Search_SoftAboutDialog aboutDialog = new Search_SoftAboutDialog(getActivity(), R.style.about_dialog);
                Window win = aboutDialog.getWindow();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.x = 0;
                layoutParams.y = 0;
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                win.setAttributes(layoutParams);
                aboutDialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
                aboutDialog.show();*/


                final  Search_SoftAboutDialogone ab = new Search_SoftAboutDialogone(getActivity());

                ab.setIcon((Integer) listItems_nv.get(position).get("img"));
                ab.setTitle( listItems_nv.get(position).get("name")+"");

                ab.setConfirm();
            }
        });




    }

    private void inittj() {

        for(int i=0;i<info_Names.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",info_icon[i] );
            map.put("name", info_Names[i]);
            listItems.add(map);
        }

        StoreGridAdapter storeGridAdapter = new StoreGridAdapter(getActivity(),listItems);
        gr_tj.setAdapter(storeGridAdapter);

        gr_tj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  Search_SoftAboutDialog aboutDialog = new Search_SoftAboutDialog(getActivity(), R.style.about_dialog);
                Window win = aboutDialog.getWindow();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.x = 0;
                layoutParams.y = 0;
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                win.setAttributes(layoutParams);
                aboutDialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
                aboutDialog.show();*/

                final Search_SoftAboutDialogone ab = new Search_SoftAboutDialogone(getActivity());

                ab.setIcon((Integer) listItems.get(position).get("img"));
                ab.setTitle( listItems.get(position).get("name")+"");

                ab.setConfirm();



            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
    }
}
