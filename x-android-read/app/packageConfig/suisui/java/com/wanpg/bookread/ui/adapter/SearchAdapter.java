package com.wanpg.bookread.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.wanpg.bookread.R;

import com.wanpg.bookread.utils.ImageLod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/11.
 */
public class SearchAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> listItems;





    private LayoutInflater listContainer;

    public final class ListItemView { //自定义控件集合
        public ImageView img;
        public TextView name;
        public ImageView jh;
        public TextView author;
        public TextView remark;
        public TextView size;
    }

    public SearchAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        this.listItems = listItems;
        this.listContainer = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return listItems.size();
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

        //  final int selectID = position;
        ListItemView listItemView = null;

        if (convertView == null) {

            listItemView = new ListItemView();

            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.searchfragment_listviewadaoter, null);

            listItemView.img = (ImageView) convertView.findViewById(R.id.search_imge_icon);
            listItemView.name = (TextView) convertView.findViewById(R.id.search_name);
            listItemView.jh = (ImageView) convertView.findViewById(R.id.search_jh);
            listItemView.author = (TextView) convertView.findViewById(R.id.search_author);
            listItemView.remark = (TextView) convertView.findViewById(R.id.search_remark);
            listItemView.size = (TextView) convertView.findViewById(R.id.search_size);
            //设置控件集到convertView
            convertView.setTag(listItemView);


        } else {

            listItemView = (ListItemView) convertView.getTag();
        }


//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), (Integer) listItems.get(position).get("img"));
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        baos.reset();//重置baos即清空baos
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
//        listItemView.img.setImageBitmap(bitmap);

        // listItemView.img.setImageResource((Integer) listItems.get(position).get("img"));
//        listItemView.img.setBackgroundResource((Integer) listItems.get(position).get("img"));

        //  listItemView.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),(Integer) listItems.get(position).get("img")));
        listItemView.name.setText((String) listItems.get(position).get("name"));
        listItemView.author.setText((String) listItems.get(position).get("author"));
        listItemView.remark.setText((String) listItems.get(position).get("remark"));
        listItemView.size.setText((String) listItems.get(position).get("size"));


        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.name_1)
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





}
