//package com.ep.lottery;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.ep.lottery.bean.LotteryBean;
//import com.epplus.statistics.JSON;
//import com.example.HelloWorld.HttpUtils;
//import com.example.HelloWorld.IHttpResult;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.text.TextUtils;
//import android.widget.Toast;
//
//public class LotterySdk {
//	
//	//public static String BASE_URL = "http://192.168.0.111:8080/PopoBird/";
//	public static String BASE_URL = "http://popobird.n8wan.com:29141/";
//	
//	private static LotterySdk sdk;
//	
//	private LotterySdk(){
//		
//	}
//	
//	public static LotterySdk install(){
//		if(sdk == null){
//			sdk = new LotterySdk();
//		}
//		return sdk;
//	}
//	
//	
//	public void show(final Activity ac,String uid){
//		
//		
//		final ProgressDialog progressDialog = new ProgressDialog(ac);
//		progressDialog.setMessage("获取中...");
//		progressDialog.setCancelable(false);
//		progressDialog.show();
//		
//		String uri = BASE_URL+"LotteryService";
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("uid", uid);
//		HttpUtils.asyPost(uri, map, new IHttpResult() {
//			@Override
//			public void result(String result) {
//				progressDialog.dismiss();
//				if(!TextUtils.isEmpty(result)){
//					LotteryBean bean = JSON.parseObject(result, LotteryBean.class);
//					LotteryDialog dialog = new LotteryDialog(ac, bean);
//					dialog.show();
//				}else {
//					Toast.makeText(ac, "彩票获取失败", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
//		
//		
//	}
//	
//	
//
//}
