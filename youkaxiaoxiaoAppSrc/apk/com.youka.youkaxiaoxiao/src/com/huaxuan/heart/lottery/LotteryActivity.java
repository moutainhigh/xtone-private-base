package com.huaxuan.heart.lottery;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Typeface;
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
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ep.sdk.XTSDK2;
import com.huaxuan.heart.lottery.adapter.LotteryAdapter;
import com.huaxuan.heart.lottery.entry.LotteryBean;
import com.huaxuan.heart.utils.Debug2;
import com.huaxuan.heart.utils.HttpUtils;
import com.huaxuan.heart.utils.IHttpResult;
import com.huaxuan.heart.utils.UrlUtils;
import com.youka.youkaxiaoxiao.R;

public class LotteryActivity extends Activity{
	
	private TextView text_uname,text_email;
	
	private ListView listView;
	private LotteryAdapter adapter;
	private ArrayList<LotteryBean> list = new ArrayList<LotteryBean>();
	
	private String uid;
	private String uname;
	private String email;
	
	private TextView info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_lotter);
		
		uid = getIntent().getStringExtra("uid");
		uname = getIntent().getStringExtra("uname");
		email = getIntent().getStringExtra("email");
		
		initView();
		initData();
		getWebData();
		setInitonClick();
	}

	private void setInitonClick() {
		findViewById(R.id.back_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		findViewById(R.id.exit_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				XTSDK2.getInstance().logout(LotteryActivity.this);
                finish();
			}
		});
		
	}

	private void initView() {
		listView = (ListView) findViewById(R.id.lotter_list);
		info = (TextView) findViewById(R.id.txt_info);
		
		text_uname = (TextView) findViewById(R.id.text_uname);
		text_email = (TextView) findViewById(R.id.text_email);
		
	}
	
	private void initData() {
		
		text_uname.setText(uname);
		text_email.setText(email);
		
		adapter = new LotteryAdapter(this, R.layout.lotter_item, list);
		listView.setAdapter(adapter);
		
		//setText();  
		
	}

	private void setText(String htmlLinkText) {
		//String htmlLinkText =  "我是超链接"+ "<a style=\"color:red;\" href='lianjie'>超链接点击事件</a>";   
		//String htmlLinkText = "请在中彩宝APP(<a href=\"http://www.youkala.com/download/1.0.0.apk\">点击打开或下载</a>)中<br/>兑换彩豆进行彩票兑换。<br/>过期日期：2017-05-15";
        // 文字的样式（style）被覆盖，不能改变…… 
		Spanned  textWithLinkText = Html.fromHtml(htmlLinkText);
		int end = textWithLinkText.length();  
	    URLSpan[] urls = textWithLinkText.getSpans(0, end, URLSpan.class);
	  
	    SpannableStringBuilder style = new SpannableStringBuilder(textWithLinkText);  
        style.clearSpans(); 
		
      //重新设置textWithLinkText中的URLSpan  
        for (URLSpan url : urls) {  
            MyURLSpan myURLSpan = new MyURLSpan(this,url.getURL());  
            style.setSpan(myURLSpan, textWithLinkText.getSpanStart(url), textWithLinkText.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
        }  
        
//        style.setSpan(new StyleSpan(Typeface.BOLD), 2, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        int n = 0;
        for (int i = 0; i < style.length(); i++) {
        	if(style.charAt(i)== ')')
        	{
        		n = i;
        		break;
        	}
		}
        
        style.setSpan(new StyleSpan(Typeface.BOLD), 2, n+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //style.setSpan(new StyleSpan(Typeface.BOLD), 9, 15, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //将重新设置Span的文本设置为forwardToXiaoMiLogin的显示文字  
        info.setText(style);  
        //通过setMovementMethod设置LinkMovementMethod类型来使LinkText有效  
        info.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	
	
	
	
	private void getWebData() {
//		HttpUtils.asyPost(UrlUtils.LotterService, UrlUtils.getUrlParm("receiveUserId", uid), new IHttpResult() {
//			
//			@Override
//			public void result(Object obj) {
//				String json = (String) obj;
//				
//				List<LotteryBean> data =  JSON.parseArray(json, LotteryBean.class);
//				if(data!=null){
//					list.addAll(data);
//					adapter.notifyDataSetChanged();
//				}
//			}
//		});
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", uname);
        HttpUtils.asyPost(UrlUtils.account, map, new IHttpResult() {
			
			@Override
			public void result(Object obj) {
				if(obj!=null)Debug2.e(LotteryActivity.this, obj.toString());
				
				if(obj!=null){
					try {
						JSONObject jsonObject = new JSONObject(obj.toString());
						
						int STATUS = jsonObject.getInt("STATUS") ;
						String DESCRIPTION = jsonObject.getString("DESCRIPTION") ;
						String timeText = null;
						int tagLength = 0;
						if(STATUS == 1){
							int temp = -1;
							org.json.JSONArray jsonArray = jsonObject.getJSONArray("DATA");
							for (int j = 0; j < jsonArray.length(); j++) {
								JSONObject jsonObject2 = jsonArray.getJSONObject(j);
								String LOTTERY_LIST = jsonObject2.getString("LOTTERY_LIST");
								String[] strings = LOTTERY_LIST.split("\\|");
								temp = j;
								for (int i = 0; i < strings.length; i++) {
									if(!TextUtils.isEmpty(strings[i])){
										String str[] = strings[i].split(",");
										LotteryBean bean = new LotteryBean(str[0], str[1], str[2]);
										bean.setTag(i+1 + j*tagLength);
										if(temp == j){
											bean.setShowLine(true);
										}
										temp = -1;
										timeText = str[2];
										list.add(bean);
									}
								}
								
								tagLength = strings.length;
							}
							
							
							String htmlLinkText = "请在中彩宝APP(<a href=\"http://www.youkala.com/download/1.0.0.apk\">点击打开或下载</a>)中<br/>兑换彩豆进行彩票兑换。<br/>过期日期：" +
									timeText;//"2017-05-15";
							setText(htmlLinkText);
							
							adapter.notifyDataSetChanged();
						}else {
							Toast.makeText(LotteryActivity.this, DESCRIPTION, Toast.LENGTH_SHORT).show();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				
				//String json = (String) obj;
				
//				List<LotteryBean> data =  JSON.parseArray(json, LotteryBean.class);
//				if(data!=null){
//					list.addAll(data);
//					adapter.notifyDataSetChanged();
//				}
			}
		});
		
		
	}

	
}
