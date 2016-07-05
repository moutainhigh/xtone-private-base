package com.huaxuan.heart.lottery.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {

	private  SparseArray<View> views;
	private View convertView;
    private  Context context;
    private int mPostion;
	
	
	private  ViewHolder(Context context,ViewGroup viewGroup,int layout,int mPostion){
		this.views = new SparseArray<View>();
		this.context=context;
		convertView = LayoutInflater.from(context).inflate(layout, viewGroup, false);
		convertView.setTag(this);
		this.mPostion = mPostion;
	}
	
	
	public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layout,int position){
		if(convertView==null){
			return new ViewHolder(context, parent, layout, position);
		}else {
			ViewHolder viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.mPostion = position;
			return viewHolder;
		}
	}
	
	

	public <T extends View> T getView(int viewId) {
		View view = views.get(viewId);
		if (view == null) {
			view = convertView.findViewById(viewId);
			views.put(viewId, view);
		}
		return (T) view;
	}
	
	public View getConvertView(){
		return convertView;
	}
	
	public ViewHolder setText(int viewId,String text){
		TextView txtView = getView(viewId);
		txtView.setText(text);
		return this;
	}
	
	
	

}
