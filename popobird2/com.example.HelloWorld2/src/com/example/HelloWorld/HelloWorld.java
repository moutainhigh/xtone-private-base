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
package com.example.HelloWorld;

import java.util.HashMap;
import java.util.Map;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.account.bean.UserInfo;
import com.ep.lottery.LotterySdk;
import com.ep.lottery.MsgCallBack;
import com.ep.lottery.MsgDialog;
import com.ep.sdk.XTSDK;
import com.epplus.view.PayParams;
import com.xqt.now.paysdk.PublicClass;

public class HelloWorld extends Cocos2dxActivity implements OnClickListener {

	private Dialog dialog;

	private boolean isLogin = false;

	private static HelloWorld s_Instance;
	
//	UserInfo userInfo;

	public static Object rtnObject() {
		return s_Instance;
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(final Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case 0:
				
				final int a = msg.arg1;
				Log.e("zgt", "http="+a);
				runOnGLThread(new Runnable() {
				@Override
				public void run() {
					//loginSuccess2(userInfo.getUserID(),1,msg.arg1);
					loginSuccess2("userInfo.getUserID()",1,a);
				}
			   });

				break;
			case 1:

				int price = (Integer) msg.obj;
//				Toast.makeText(getApplicationContext(), "购买" + price + "元",
//						Toast.LENGTH_SHORT).show();
				if(6 == price){
					payNewBorid();
				}else{
				   doPay();
				}

				break;

			case 2:
				Toast.makeText(getApplicationContext(), "退出",
						Toast.LENGTH_SHORT).show();
				doExit();
				break;

			default:
				break;
			}

		}

	};

	private void doExit() {
		// XTSDK.getInstance().logout();
		Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
		isLogin = false;
		finish();
	}

	private void doLoginXT() {
//		XTSDK.getInstance().login(this);
	}

	private void doPay() {
		MsgDialog msg_dialog=new MsgDialog(HelloWorld.this, "神器的气泡", new MsgCallBack() {
			public void clickOk(){
				String productId = "QP" + System.currentTimeMillis();
				PayParams pa = new PayParams(1, productId, "神器的气泡", "神器的气泡id为123456");
				boolean b = XTSDK.getInstance().pay(HelloWorld.this, pa);
//				if (!b) {
//					Toast.makeText(this, "你还未登录", Toast.LENGTH_SHORT).show();
//				}
			}
		});
		msg_dialog.show();
		

	}
	
	
	boolean xingxiang = false;
	private void payNewBorid(){
		MsgDialog msg_dialog=new MsgDialog(HelloWorld.this,"新形象",new MsgCallBack() {
			public void clickOk(){
				xingxiang = true;
				String productId = "QP" + System.currentTimeMillis();
				PayParams pa = new PayParams(2, productId, "新形象", "新形象为你开启");
				boolean b = XTSDK.getInstance().pay(HelloWorld.this, pa);
//				if (!b) {
//					Toast.makeText(HelloWorld.this, "你还未登录", Toast.LENGTH_SHORT).show();
//				}
			}
		});
		msg_dialog.show();
	}
	

	private Handler payHandler = new Handler() {
		public void handleMessage(Message msg) {
			System.out.println(msg);
			switch (msg.what) {

			case 1070:
				Toast.makeText(HelloWorld.this, "失败-" + msg.obj.toString(),
						1000).show();
				break;
			case 1078:
				Toast.makeText(HelloWorld.this, "失败*" + msg.what, 1000).show();
				break;
			case 4001:
				Toast.makeText(HelloWorld.this, msg.what + "", 1000).show();
				
				
				
				if(buyChreey){
					buyChreey = false;
					runOnGLThread(new Runnable() {
						
						@Override
						public void run() {
							paySuccess("cherry");
						}
					});
					return;
				}
				if(xingxiang){
					xingxiang = false;
                     runOnGLThread(new Runnable() {
						
						@Override
						public void run() {
							paySuccess("6");
						}
					});
                   return;
				}
				
				runOnGLThread(new Runnable() {

					@Override
					public void run() {
						paySuccess("sucessInfo");
					}
				});
				
				
				
				break;
			case 4002:
				Toast.makeText(HelloWorld.this, msg.what + "", 0).show();
				runOnGLThread(new Runnable() {

					@Override
					public void run() {
						// paynSuccess("sucessInfo");
						payFail("failInfo");
					}
				});
				
				if(buyChreey){
					buyChreey = false;
					runOnGLThread(new Runnable() {
						
						@Override
						public void run() {
							//paySuccess("cherry");
							payFail("failInfo");
						}
					});
				}
				
				if(xingxiang){
					xingxiang = false;
                     runOnGLThread(new Runnable() {
						
						@Override
						public void run() {
							//paySuccess("6");
							payFail("failInfo");
						}
					});
				}
				
				break;
			case 4010:
				Toast.makeText(HelloWorld.this, "初始化成功*" + msg.what, 1000)
						.show();
				break;
			case 4011:
//				userInfo = (UserInfo) msg.obj;
//				Toast.makeText(
//						HelloWorld.this,
//						"自动登陆成功*" + msg.what + userInfo.getUserID()
//								+ userInfo.getUsername(), Toast.LENGTH_SHORT)
//						.show();
//				isLogin = true;

				break;
			case 4012:
//				userInfo = (UserInfo) msg.obj;
//				Toast.makeText(
//						HelloWorld.this,
//						"登陆成功*" + msg.what + userInfo.getUserID()
//								+ userInfo.getUsername(), Toast.LENGTH_SHORT)
//						.show();
//				isLogin = true;
				
				updateCherry();
				
//				runOnGLThread(new Runnable() {
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						loginSuccess(userInfo.getUserID(), 1);
//					}
//				});
				break;
			default:
				// Toast.makeText(MainActivity.this, "未知原因*"+msg.what,
				// 1000).show();
				break;
			}
			xingxiang = false;
		};
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		s_Instance = this;
		XTSDK.getInstance().init(this, "4001059566", payHandler);

	}

	@SuppressLint("NewApi") 
	public Cocos2dxGLSurfaceView onCreateView() {
		Cocos2dxGLSurfaceView glSurfaceView = new Cocos2dxGLSurfaceView(this);
		// HelloWorld should create stencil buffer
		glSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8); 

		return glSurfaceView;
	}

	static {
		System.loadLibrary("cocos2dcpp");
	}

	/**
	 * 登陆
	 * 
	 * @param uName
	 * @param paw
	 */
	public void doLogin() {
		// Toast.makeText(this, "登陆", 0).show();
		//handler.sendEmptyMessage(0);
		
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
//				if (isLogin == false) {
//					Toast.makeText(getApplicationContext(), "用户登陆",
//							Toast.LENGTH_SHORT).show();
					doLoginXT();
//				} else {
//					try {
						updateCherry();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					
//				}
			}
		});
	}
	
	
	public void updateCherry(){
		
		//String uri = "http://192.168.0.111:8080/PopoBird/BirdService";
		String uri = "http://popobird.n8wan.com:29141/BirdService";
		Map<String, String> map = new HashMap<String, String>();
//		map.put("uid", userInfo.getUserID());
		HttpUtils.asyPost(uri, map, new IHttpResult() {
			
			@Override
			public void result(String result) {
				
				int cherrys = 0 ;
				if(!TextUtils.isEmpty(result)){
					cherrys= Integer.parseInt(result);
				}
				Message msg = handler.obtainMessage();
				msg.what=0;
				if(cherrys<0){
					cherrys = 0;
				}
				msg.arg1 = cherrys;
				handler.sendMessage(msg);
				
			}
		});
		
	}
	

	/**
	 * 退出
	 */
	public void exit() {
		handler.sendEmptyMessage(2);
	}

	public void pay(int money) {
		// Toast.makeText(this, "钱 ："+money, 0).show();
		Message msg = handler.obtainMessage();
		msg.obj = money;
		msg.what = 1;
		handler.sendMessage(msg);
	}
	
	public void Logout(){
		
		Log.e("zgt", "Logout");
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if(isLogin)Toast.makeText(getApplicationContext(), "退出成功", Toast.LENGTH_SHORT).show();
//				XTSDK.getInstance().logout();
				isLogin = false;
			}
		});
	}
	
	
	
	
	boolean buyChreey = false;
	public void payTag(int price,String tag){
		Log.e("zgt", "tag:"+tag);
		
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				MsgDialog msg_dialog=new MsgDialog(HelloWorld.this,"100个樱桃(1元)",new MsgCallBack() {
					public void clickOk(){
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage("10660840", null, "CB06", null, null);
						buyChreey = true;
						String productId = "QP" + System.currentTimeMillis();
//						PayParams pa = new PayParams(1, productId, "买100个樱桃", "买100个樱桃123456");
//						boolean b = XTSDK.getInstance().pay(HelloWorld.this, pa);
//						if (!b) {
//							Toast.makeText(HelloWorld.this, "你还未登录", Toast.LENGTH_SHORT).show();
//						}
					}
				});
				msg_dialog.show();
			}
		});
		
        
	}
	
	
	
	public void lottery(String str)
    {
         runOnUiThread(new Runnable() {
			@Override
			public void run() {
				//Toast.makeText(getApplicationContext(), "xxx", Toast.LENGTH_SHORT).show();
//				if(userInfo!=null){
//					LotterySdk sdk = LotterySdk.install();
//					sdk.show(HelloWorld.this, userInfo.getUserID());
//				}else {
//					Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
//				}
				
				
				runOnGLThread(new Runnable() {
					
					@Override
					public void run() {
						lotteryNative("hello");
					}
				});
				
			}
		});
    }
    
    /**
     * 彩票native
     * @param str
     */
    public native void lotteryNative(String str);
	
	

	/**
	 * 登陆成功
	 * 
	 * @param userInfo
	 */
	public native void loginSuccess(String userInfo, int state);
	
	
	 /**
     * 登陆成功
     * @param userInfo
     * @param state
     * @param cherrys
     */
    public native void loginSuccess2(String userInfo,int state,int cherrys);
    
	

	/**
	 * 支付成功
	 * 
	 * @param sucessInfo
	 */
	public native void paySuccess(String sucessInfo);

	/**
	 * 支付失败
	 * 
	 * @param failInfo
	 */
	public native void payFail(String failInfo);

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		XTSDK.getInstance().payCallResult(this, requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			XTSDK.getInstance().exit(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
