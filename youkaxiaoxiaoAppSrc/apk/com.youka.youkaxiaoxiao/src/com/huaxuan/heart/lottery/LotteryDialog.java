package com.huaxuan.heart.lottery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.huaxuan.heart.lottery.adapter.LotteryAdapter;
import com.huaxuan.heart.lottery.entry.LotteryBean;
import com.huaxuan.heart.utils.Debug2;
import com.huaxuan.heart.utils.HttpUtils;
import com.huaxuan.heart.utils.IHttpResult;
import com.huaxuan.heart.utils.UrlUtils;
import com.youka.youkaxiaoxiao.R;

public class LotteryDialog extends Dialog implements View.OnClickListener {

	
	public static interface LotteryCall{
		public void getLotterySucces();
	}
	
	private Button cancle_btn;

	private ListView listView;
	private LotteryAdapter adapter;
	private ArrayList<LotteryBean> list = new ArrayList<LotteryBean>();
	private String uid,uname;

	private Context context;

	private TextView info;

	private LotteryCall call;
	
	public LotteryDialog(Context context, String uid,String uname,LotteryCall call) {
		super(context);
		setCancelable(false);
		this.uid = uid;
		this.uname = uname;
		this.context = context;
		this.call = call;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.dialog_lottery);
		getWindow().setBackgroundDrawable(new ColorDrawable(0));

		initView();
		initData();
		getWebData();
	}

	private void initView() {
		cancle_btn = (Button) findViewById(R.id.cancle_btn);
		cancle_btn.setOnClickListener(this);

		listView = (ListView) findViewById(R.id.lottery_dialog_list);

		info = (TextView) findViewById(R.id.txt_dilaog);

	}

	private void getWebData() {
//		HttpUtils.asyPost(UrlUtils.LotterService,
//				UrlUtils.getUrlParm("obtainLottery", uid), new IHttpResult() {
//					@Override
//					public void result(Object obj) {
//						String json = (String) obj;
//						List<LotteryBean> data = JSON.parseArray(json,
//								LotteryBean.class);
//						if (data != null && data.size() > 0) {
//							list.addAll(data);
//							adapter.notifyDataSetChanged();
//						}
//					}
//				});
		
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", uname);
		
		HttpUtils.asyPost(UrlUtils.gift,
				map, new IHttpResult() {
					@Override
					public void result(Object obj) {
						if(obj!=null){
							Debug2.e(context, obj.toString());
							try {
								JSONObject jsonObject = new JSONObject(obj.toString());
								
								int STATUS = jsonObject.getInt("STATUS") ;
								String DESCRIPTION = jsonObject.getString("DESCRIPTION") ;
								if(STATUS == 1){
									call.getLotterySucces();
									String timeText = null;
									String LOTTERY_LIST = jsonObject.getString("LOTTERY_LIST");
									String[] strings = LOTTERY_LIST.split("\\|");
									for (int i = 0; i < strings.length; i++) {
										if(!TextUtils.isEmpty(strings[i])){
											String str[] = strings[i].split(",");
											LotteryBean bean = new LotteryBean(str[0], str[1], str[2]);
											bean.setTag(i+1);
											timeText = str[2];
											list.add(bean);
										}
									}
									
									String htmlLinkText = "请在中彩宝APP(<a href=\"http://www.youkala.com/download/1.0.0.apk\">点击打开或下载</a>)中<br/>兑换彩豆进行彩票兑换。<br/>过期日期：" +
											timeText;//"2017-05-15";
									setText(htmlLinkText);
									adapter.notifyDataSetChanged();
								}else {
									Toast.makeText(context, DESCRIPTION, Toast.LENGTH_SHORT).show();
								}
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
						}
						
//						String json = (String) obj;
//						List<LotteryBean> data = JSON.parseArray(json,
//								LotteryBean.class);
//						if (data != null && data.size() > 0) {
//							list.addAll(data);
//							adapter.notifyDataSetChanged();
//						}
					}
				});
		
		

	}

	private void initData() {
		adapter = new LotteryAdapter(context, R.layout.lotter_item, list);
		listView.setAdapter(adapter);
		//setText();

	}

	private void setText(String htmlLinkText) {
		//String htmlLinkText = "请在中彩宝APP(<a href=\"http://www.youkala.com/download/1.0.0.apk\">点击打开或下载</a>)中<br/>兑换彩豆进行彩票兑换。<br/>过期日期：2017-05-15";
		// 文字的样式（style）被覆盖，不能改变……
		Spanned textWithLinkText = Html.fromHtml(htmlLinkText);
		int end = textWithLinkText.length();
		URLSpan[] urls = textWithLinkText.getSpans(0, end, URLSpan.class);

		SpannableStringBuilder style = new SpannableStringBuilder(
				textWithLinkText);
		style.clearSpans();

		// 重新设置textWithLinkText中的URLSpan
		for (URLSpan url : urls) {
			MyURLSpan myURLSpan = new MyURLSpan(context, url.getURL());
			style.setSpan(myURLSpan, textWithLinkText.getSpanStart(url),
					textWithLinkText.getSpanEnd(url),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

		int n = 0;
        for (int i = 0; i < style.length(); i++) {
        	if(style.charAt(i)== ')')
        	{
        		n = i;
        		break;
        	}
		}
        style.setSpan(new StyleSpan(Typeface.BOLD), 2, n+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		info.setText(style);
		// 通过setMovementMethod设置LinkMovementMethod类型来使LinkText有效
		info.setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}

}
