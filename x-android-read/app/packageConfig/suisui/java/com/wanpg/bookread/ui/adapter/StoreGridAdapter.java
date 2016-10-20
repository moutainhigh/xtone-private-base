package com.wanpg.bookread.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.wanpg.bookread.R;
import com.wanpg.bookread.utils.ImageLod;
import com.wanpg.bookread.widget.Search_SoftAboutDialog;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/12.
 */
public class StoreGridAdapter extends BaseAdapter {


    private Context context;
    private List<Map<String,Object>> listItems;



    private LayoutInflater listContainer;

    public StoreGridAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        this.listItems = listItems;
        listContainer = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listItems.size() ;
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int selectID = position;
        ListItemView  listItemView = null;

        if(convertView==null){

            listItemView = new ListItemView();

            convertView = listContainer.inflate(R.layout.layout_griditem,null);

            listItemView.img = (ImageView) convertView.findViewById(R.id.iv_image);
            listItemView.name = (TextView) convertView.findViewById(R.id.tv_title);
            //设置控件集到convertView
            convertView.setTag(listItemView);


        }else {

            listItemView = (ListItemView) convertView.getTag();

        }



//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), (Integer) listItems.get(position).get("img"));
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        baos.reset();//重置baos即清空baos
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
//        listItemView.img.setImageBitmap(bitmap);

    //  listItemView.img.setBackgroundResource((Integer) listItems.get(position).get("img"));

        listItemView.name.setText((String) listItems.get(position).get("name"));


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.name_1)
                .showImageOnFail(R.drawable.name_1)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();


        //图片来源于
        String drawableUrl = ImageDownloader.Scheme.DRAWABLE.wrap(listItems.get(position).get("img").toString());
        Log.i("url",drawableUrl);
        ImageLoader.getInstance().displayImage(drawableUrl,listItemView.img,options);


        return convertView;
    }




    class ListItemView{ //自定义控件集合
        public ImageView img;
        public TextView name;

    }
}
