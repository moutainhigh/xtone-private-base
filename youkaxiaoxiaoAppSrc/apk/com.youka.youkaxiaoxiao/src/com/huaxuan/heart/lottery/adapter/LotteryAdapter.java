package com.huaxuan.heart.lottery.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.huaxuan.heart.lottery.entry.LotteryBean;
import com.youka.youkaxiaoxiao.R;

public class LotteryAdapter extends AbcAdapter<LotteryBean>{

	
	
	
	public LotteryAdapter(Context context, int layout, List<LotteryBean> list) {
		super(context, layout, list);
	}
	
	
	

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}




	@Override
	public void convert(ViewHolder holder, final LotteryBean t) {
		
		holder.setText(R.id.txt_01, t.getExchangeCode());
		holder.setText(R.id.txt_02, t.getPasswordCode());
		
		holder.setText(R.id.xl_text, "序列号"+t.getTag()+":");
		holder.setText(R.id.yzm_txt, "验证码"+t.getTag()+":");
		
		
		TextView xlh = holder.getView(R.id.txt_011);
		TextView yzm = holder.getView(R.id.txt_022);
		
		View line_view = holder.getView(R.id.line_view);
		if(t.isShowLine()){
			line_view.setVisibility(View.VISIBLE);
		}else {
			line_view.setVisibility(View.GONE);
		}
		
		xlh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				copyCode(t.getExchangeCode(), "序列号拷贝成功");
			}
		});
		
		
		yzm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				copyCode(t.getPasswordCode(), "验证码拷贝成功");
			}
		});
	}
	
	@SuppressLint("NewApi") 
	private void copyCode(String data,String info) {
		ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("simple text",data);
		clipboard.setPrimaryClip(clip);
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}
	

}
