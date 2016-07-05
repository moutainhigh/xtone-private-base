/****************************************************************************
Copyright (c) 2010-2011 cocos2d-x.org

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 ****************************************************************************/
package com.huaxuan.heart;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

import com.ep.sdk.XTSDK;
import com.ep.sdk.YKSDKComponents;
//import com.ep.sdk.XTSDK2;
import com.epplus.view.PayParams;
import com.huaxuan.heart.login.AccountUtils;
import com.huaxuan.heart.login.IAccount;
import com.huaxuan.heart.lottery.LotteryActivity;
import com.huaxuan.heart.lottery.LotteryDialog;
import com.huaxuan.heart.lottery.LotteryDialog.LotteryCall;
import com.huaxuan.heart.utils.UserInfoUtils;
import com.more.pay.EtoneFee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

public class MainActivity extends Cocos2dxActivity {

	public static Activity activity;
	
	
	private int flag = -1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		JniHelper.init(this);
		EtoneFee.getInstance().initFeeInfo(this);

		activity = this;

		initData();
	}

	public static Object rtnObject() {
		return activity;
	}

	public Cocos2dxGLSurfaceView onCreateView() {
		Cocos2dxGLSurfaceView glSurfaceView = new Cocos2dxGLSurfaceView(this);
		// huaxuan_heart should create stencil buffer
		glSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);

		return glSurfaceView;
	}

	static {
		System.loadLibrary("cocos2dcpp");
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 4011:
				autoLogin();
				showGiftCall();
				break;
			case 4012:
				showGiftCall();
				break;
			case 4001:
				// 支付成功了
				doPaySuccess();
				break;
			case 4002:
				//支付失败
				//doPaySuccess();
				doPayFail();
				break;

			default:
				break;
			}
			
			pa = null;
		}

		

		

		
	};
	
	
	private void showGiftCall() {
		AccountUtils.instances(this).getGift(new IAccount() {
			@Override
			public void loginSuccess(Object obj) {
				int start = (Integer) obj;
				flag = start;
				if (start != 0) {
					runOnGLThread(new Runnable() {

						@Override
						public void run() {
							showGift(111, "");
						}
					});
				} else {
					runOnGLThread(new Runnable() {

						@Override
						public void run() {
							showGift(0, "");
						}
					});
				}
			}

		});
		
	}
	
	/**
	 * 自动登陆
	 * @param start
	 */
	private void autoLogin() {
		if(XTSDK.getInstance().getUserInfo()!=null){
			Toast.makeText(activity, "自动登陆", Toast.LENGTH_SHORT).show();
		}
	}
	

	
	/**
	 * 支付失败
	 */
	private void doPayFail() {
		Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
		
//      if (XTSDK.getInstance().getUserInfo() != null){
//			
//			if(pa!=null){
//				String uid = XTSDK.getInstance().getUserInfo().getUserID();
//				String money = pa.getPrice()+"";
//				YKSDKComponents.instances(activity).sendLottery(uid, money);
//			}
//		}
		
	}
	
	private void doPaySuccess() {
		flag = -1;
		runOnGLThread(new Runnable() {
			public void run() {
				paySuccess(1);
			}
		});
		if (XTSDK.getInstance().getUserInfo() != null){
			
			if(pa!=null){
				String uid = XTSDK.getInstance().getUserInfo().getUserID();
				String money = pa.getPrice()+"";
				YKSDKComponents.instances(activity).sendLottery(uid, money);
			}
		}
		
	}

	private void initData() {
		XTSDK.getInstance().init(this, "110", handler);
		showGiftCall();
	}

	/**
	 * 登陆 C==>Java
	 * 
	 * @param flag
	 * @param str
	 */
	public void login(int flag, final String str) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (XTSDK.getInstance().getUserInfo() != null&&!TextUtils.isEmpty(XTSDK.getInstance().getUserInfo().getUserName())) {
					String uid = XTSDK.getInstance().getUserInfo().getUserID(); 
					YKSDKComponents.instances(activity).myLottery(uid);

				} else {
					XTSDK.getInstance().login(MainActivity.this);
				}
			}
		});
	}

	
	PayParams pa;
	
	/**
	 * 支付价格 C ==>Java
	 * 
	 * @param price
	 * @param info
	 */
	public void pay(final int price, final String info) {
		
		   pa = null;
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					
					pa = new PayParams(1, "12345", "钻石", "钻石可以消灭星星");
					boolean b = XTSDK.getInstance().pay(MainActivity.this, pa);
					if(!b){
						Toast.makeText(activity, "你还未登陆哦", 0).show();
					}
					
					/*if(flag==2){
						if(XTSDK2.getInstance().getUserInfo()!=null){
							Toast.makeText(MainActivity.this, "活动你已经参与过了", Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(activity, "你还未登陆哦", 0).show();
						}
						return ;
					}else if(flag !=-1){

						PayParams pa = new PayParams(1, "12345", "钻石", "钻石可以消灭星星");
						boolean b = XTSDK2.getInstance().pay(MainActivity.this, pa);
						if(!b){
							Toast.makeText(activity, "你还未登陆哦", 0).show();
						}
					}*/
				}
			});
		
	}

	/**
	 * Java==>C++ 支付成功
	 * 
	 * @param flag
	 */
	public native void paySuccess(int flag);
	
	
    /**
     * 
     * @param n 111 表示显示
     * @param info  信息
     */
    public static native void showGift(int n,String info);
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		XTSDK.getInstance().payCallResult(this, requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}
}
