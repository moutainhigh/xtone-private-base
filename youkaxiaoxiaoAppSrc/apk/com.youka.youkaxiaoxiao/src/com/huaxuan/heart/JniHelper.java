package com.huaxuan.heart;

import java.util.HashMap;
import java.util.Map;

import com.more.pay.EtoneFee;
import com.more.pay.EtoneFeeResultListener;
import com.more.pay.constant.EtoneContance;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class JniHelper {
	// code = 0为成功，1为失败。此方法为java调用通知cpp客户端
	public static native void payResultNative(int code, String orderNo);

	// 通知领取礼包
	//public static native void giftNotifyNative();

	public static final int PAY_NOTIFY_ID = 5000;
	private static final int TOAST_MGS = 5001;
	
	public static Activity mContext;

	public static void init(Activity cntx) {
		mContext = cntx;
	}

	public static Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PAY_NOTIFY_ID: {
				String goodCodes = "";
				String goodNames = "";
				String goodPrice = "";

				if ("001".equals(thisOrderNo)) {
					goodCodes = "15e17z";
					goodNames = "10个钻石送5个礼包";
					goodPrice = "200";

				} else if ("002".equals(thisOrderNo)) {
					goodCodes = "bj8i3z";
					goodNames = "20个钻石送10个礼包";
					goodPrice = "400";
				} else if ("003".equals(thisOrderNo)) {
					goodCodes = "5fu9bx";
					goodNames = "30个钻石送20个礼包";
					goodPrice = "600";
				} else if ("004".equals(thisOrderNo)) {
					goodCodes = "5xkga3";
					goodNames = "40个钻石送35个礼包";
					goodPrice = "800";
				} else if ("005".equals(thisOrderNo)) {
					goodCodes = "15kbxc";
					goodNames = "50个钻石送50个礼包";
					goodPrice = "1000";
				} else if ("006".equals(thisOrderNo)) {
					goodCodes = "12hoo7";
					goodNames = "60个钻石送60个礼包";
					goodPrice = "1200";
				} else if ("007".equals(thisOrderNo)) {
					goodCodes = "fto4ea";
					goodNames = "70个钻石送70个礼包";
					goodPrice = "1500";
				} else if ("008".equals(thisOrderNo)) {
					goodCodes = "57kz3h";
					goodNames = "80个钻石送80个礼包";
					goodPrice = "2000";
				} else if ("009".equals(thisOrderNo)) {
					goodCodes = "dhbd2z";
					goodNames = "90个钻石送90个礼包";
					goodPrice = "2500";
				} else if ("010".equals(thisOrderNo)) {
					goodCodes = "11hj7p";
					goodNames = "100个钻石送100个礼包";
					goodPrice = "3000";
				} else if ("200".equals(thisOrderNo)) { // 0.1元
					goodCodes = "cppjey";
					goodNames = "1个特惠礼包";
					goodPrice = "10";
				}

				Log.v("payment", "goodCodes=" + goodCodes + ", goodNames="
						+ goodNames + " goodPrice=" + goodPrice);

				Map<String, String> map = new HashMap<String, String>();
				map.put(EtoneContance.BESTPAY_APP_CODE, "2066809433");
				map.put(EtoneContance.BESTPAY_APP_NAME, "心心对对碰");
				map.put(EtoneContance.BESTPAY_GOODS_CODE, goodCodes);// 道具ID
				map.put(EtoneContance.BESTPAY_GOODS_NAME, goodNames);
				map.put(EtoneContance.BESTPAY_CP_INFO, "x70ah2");
				map.put(EtoneContance.BESTPAY_GOODS_PRICE, goodPrice);

				EtoneFee.getInstance().pay(mContext, map,
						new EtoneFeeResultListener() {
							@Override
							public void etoneFeeCancel(String arg0,
									String reusltInfo, int resultCode) {
								payResultNative(1, thisOrderNo);

								Log.v("payment", "payResultNative=cancel");

								Toast.makeText(
										mContext,
										"支付取消" + ". error code : " + resultCode
												+ " resultInfo :" + reusltInfo,
										Toast.LENGTH_LONG).show();
							}

							@Override
							public void etoneFeeFailed(String arg0,
									String reusltInfo, int resultCode) {
								payResultNative(1, thisOrderNo);

								Log.v("payment", "payResultNative=failed"
										+ "result code=" + resultCode
										+ "result info=" + reusltInfo);

								Toast.makeText(
										mContext,
										"支付失败 . " + "error code : "
												+ resultCode + " resultInfo :"
												+ reusltInfo, Toast.LENGTH_LONG)
										.show();
							}

							@Override
							public void etoneFeeSucceed(int arg0,
									String cpInfo, String goodsCode) {
								payResultNative(0, thisOrderNo);

								Log.v("payment", "payResultNative=success");

								Toast.makeText(mContext, "支付成功",
										Toast.LENGTH_LONG).show();
							}
						});
				break;
			}
			case TOAST_MGS: {
				String text = (String) msg.obj;
				// Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	public static String thisOrderNo;
	
	// 由cpp客户端发起调用
	public static void startPayment(int dollar, String orderNo, String orderDesc) {
		Log.v("payment", "dollar=" + dollar + ", orderNo=" + orderNo + " orderDesc=" + orderDesc);

		String title = "您将花费" + dollar + "元" + " OrderNo=" + orderNo + " orderDesc=" + orderDesc;
		
		thisOrderNo = orderNo;

		 Message message = new Message();
		 message.what = PAY_NOTIFY_ID;
		 message.obj = "ok"; 
		 myHandler.sendMessage(message);
	}

	public static void exchangeCode(String strCode) {
		Log.v("payment", "exchangeCode=" + strCode);
	}

	public static void fetchGift() {
		Log.v("payment", "fetch gift");
		
//		JniHelper.startPayment(100, 10);
	}

	public static void gotoUrl() {
		Log.v("payment", "gotoUrl");
	}

	public static void reqGift() {
		Log.v("payment", "reqGift");
	}

	public static void Toast(String text) {
		Message message = new Message();
		message.what = TOAST_MGS;
		message.obj = text;

		myHandler.sendMessage(message);
	}
}
