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
import org.json.JSONObject;

import com.example.wx.MyDialog;
import com.xinmei365.game.proxy.GameProxy;
import com.xinmei365.game.proxy.PayCallBack;
import com.xinmei365.game.proxy.XMUser;
import com.xinmei365.game.proxy.XMUserListener;
import com.xinmei365.game.proxy.XMUtils;
import com.xinmei365.game.proxy.exit.LJExitCallback;
import com.xinmei365.game.proxy.init.XMInitCallback;
import com.xinmei365.game.proxy.pay.XMPayParams;
import com.xinmei365.game.proxy.util.LogUtils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HelloWorld extends BaseActivity  implements OnClickListener,XMUserListener{
	
	
	private XMUser mUser;
	private Dialog dialog;
	
	private boolean isLogin = false;
	
	private static HelloWorld s_Instance;
	
	public static Object rtnObject(){
		return s_Instance;
	}
	
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			switch (msg.what) {
			case 0:
				if(isLogin == false){
					Toast.makeText(getApplicationContext(), "用户登陆", Toast.LENGTH_SHORT).show();
					doInit();
					doLoginLJ();
				}else {
					
					runOnGLThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							loginSuccess2("userInfo", 1,100);
						}
					});
					
				}
				
				
				break;
			case 1:
				
				
				int price = (Integer) msg.obj;
				Toast.makeText(getApplicationContext(), "购买"+price+"元", Toast.LENGTH_SHORT).show();
				//doPay();
				if(6 == price)
				{
					runOnGLThread(new Runnable() {

						@Override
						public void run() {
							paySuccess("6");
						}
					});
					
				}else
				{
				 doPayWX();
				}
				break;
				
			case 2:
				Toast.makeText(getApplicationContext(), "退出", Toast.LENGTH_SHORT).show();
				
				doExit();
				break;

			default:
				break;
			}
			
		}
		
	};
	
	public void doPayWX(){
		
		MyDialog myDialog = new MyDialog(this, new MyDialog.MyHander() {
			
			@Override
			public void onSuc() {
				runOnGLThread(new Runnable() {

					@Override
					public void run() {
						paySuccess("sucessInfo");
					}
				});

			}
			
			@Override
			public void onFail() {
				runOnGLThread(new Runnable() {

					@Override
					public void run() {
						// paynSuccess("sucessInfo");
						payFail("failInfo");
					}
				});

			}
		});
		myDialog.show();
		
	}
	
	
    public  void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		s_Instance = this;
		
		GameProxy.getInstance().setUserListener(this, this);
		
	}

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
     * @param uName
     * @param paw
     */
    public   void doLogin(){
    	//Toast.makeText(this, "登陆", 0).show();
    	handler.sendEmptyMessage(0);
    }
    
    public void lottery(String str)
    {
         runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "xxx", Toast.LENGTH_SHORT).show();
				
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
    
    
    public void Logout(){
    //	handler.sendEmptyMessage(0);
    	Log.e("zgt", "Logout");
    }
    
    public void payTag(int price,String tag){
    	Log.e("zgt", "tag:"+tag);
    	//handler.sendEmptyMessage(0);
    	runOnGLThread(new Runnable() {
			
			@Override
			public void run() {
				paySuccess("cherry");
			}
		});
    }
    
    
    /**
     * 退出
     */
    public void exit(){
    	handler.sendEmptyMessage(2);
    }
    
    
    
    public void pay(int money){
    	//Toast.makeText(this, "钱 ："+money, 0).show();
    	Message msg = handler.obtainMessage();
    	msg.obj = money;
    	msg.what = 1;
    	handler.sendMessage(msg);
    }
    
    
    /**
     * 登陆成功
     * @param userInfo
     */
    public native void loginSuccess(String userInfo,int state);
    
    /**
     * 登陆成功
     * @param userInfo
     * @param state
     * @param cherrys
     */
    public native void loginSuccess2(String userInfo,int state,int cherrys);
    
    
   
    
     /**
      * 支付成功
      * @param sucessInfo
      */
    public native void paySuccess(String sucessInfo);
	
    /**
     * 支付失败
     * @param failInfo  
     */
	public native void payFail(String failInfo);

	
	
	
	
	
	
	
	
	/**
	 * 初始化
	 */
	private void doInit() {
		GameProxy.getInstance().init(this, new XMInitCallback() {

			@Override
			public void onInitSuccess() {
				showToast("初始化成功");
			}

			@Override
			public void onInitFailure(String msg) {
				showToast("初始化失败");
			}
		});
	}

	/**
	 * 登陆接口说明：
	 * 
	 * @param activity
	 *            当前activity
	 * @param customObject
	 *            用户自定义参数，在登陆回调中原样返回
	 */
	private void doLoginLJ() {
		LogUtils.d("doLogin");
		GameProxy.getInstance().login(HelloWorld.this, "test login");
	}

	/**
	 * 定额支付接口说明
	 * 
	 * @param context
	 *            上下文Activity
	 * @param total
	 *            定额支付总金额，单位为人民币分
	 * @param unitName
	 *            游戏币名称，如金币、钻石等
	 * @param count
	 *            购买商品数量，如100钻石，传入100；10魔法石，传入10
	 * @param callBackInfo
	 *            游戏开发者自定义字段，会与支付结果一起通知到游戏服务器，游戏服务器可通过该字段判断交易的详细内容（金额 角色等）
	 * @param callBackUrl
	 *            支付结果通知地址，支付完成后我方后台会向该地址发送支付通知
	 * @param payCallBack
	 *            支付回调接口
	 */
	private void doPay() { 
		XMPayParams params = new XMPayParams();
		
		params.setAmount(600);
		params.setItemName("元宝");
		params.setCount(60);
		params.setChargePointName("60元宝");
		params.setCustomParam("test pay");
		params.setCallbackUrl("http://testpay");
		GameProxy.getInstance().pay(HelloWorld.this, params, new PayCallBack() {

			@Override
			public void onSuccess(String sucessInfo) {
				// 此处回调仅代表用户已发起支付操作，不代表是否充值成功，具体充值是否到账需以服务器间通知为准；
				// 在此回调中游戏方可开始向游戏服务器发起请求，查看订单是否已支付成功，若支付成功则发送道具。
				showToast("已发起支付，请向游戏服务器查询充值是否到账");
				
runOnGLThread(new Runnable() {
					
					@Override
					public void run() {
						paySuccess("sucessInfo");
					}
				});
			}
			
			@Override
			public void onFail(String failInfo) {
				// 此处回调代表用户已放弃支付，无需向服务器查询充值状态
				showToast("用户已放弃支付");
				
runOnGLThread(new Runnable() {
					
					@Override
					public void run() {
						//paynSuccess("sucessInfo");
						payFail("cherry");
					}
				});
				
			}
		});
	}

	/**
	 * 登出接口说明：
	 * 
	 * @param activity
	 *            当前activity
	 * @param customObject
	 *            用户自定义参数，在登陆回调中原样返回
	 */
	private void doLogout() {
		GameProxy.getInstance().logout(HelloWorld.this, "test logout");
		isLogin = false;
	}

	/**
	 * 退出接口说明：
	 * 
	 * @param context
	 *            当前activity
	 * @param callback
	 *            退出回调
	 */
	private void doExit() {
		GameProxy.getInstance().exit(HelloWorld.this, new LJExitCallback() {

			@Override
			public void onGameExit() {
				// 渠道不存在退出界面，如百度移动游戏等，此时需在此处弹出游戏退出确认界面，否则会出现渠道审核不通过情况
				// 游戏定义自己的退出界面 ，实现退出逻辑
				AlertDialog.Builder builder = new Builder(HelloWorld.this);
				builder.setTitle("游戏自带退出界面");
				builder.setPositiveButton("退出",new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,int which) {
						dialog.dismiss();
						// 该方法必须在退出时调用
						GameProxy.getInstance().applicationDestroy(
								HelloWorld.this);

						/**** 退出逻辑需确保能够完全销毁游戏 ****/
						HelloWorld.this.finish();
						onDestroy();
						android.os.Process.killProcess(android.os.Process.myPid());
						/**** 退出逻辑请根据游戏实际情况，勿照搬Demo ****/
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				
				dialog = builder.create();
				dialog.show();
			}

			@Override
			public void onChannelExit() {
				// 渠道存在退出界面，如91、360等，此处只需进行退出逻辑即可，无需再弹游戏退出界面；
				Toast.makeText(HelloWorld.this, "由渠道退出界面退出",Toast.LENGTH_LONG).show();
				// 该方法必须在退出时调用
				GameProxy.getInstance().applicationDestroy(HelloWorld.this);

				/**** 退出逻辑需确保能够完全销毁游戏 ****/
				HelloWorld.this.finish();
				onDestroy();
				android.os.Process.killProcess(android.os.Process.myPid());
				/**** 退出逻辑请根据游戏实际情况，勿照搬Demo ****/

			}
		});
	}

	/**
	 * 数据上传接口说明：(需在进入服务器、角色升级、创建角色处分别调用，否则无法上传apk)
	 * 
	 * @param activity
	 *            上下文Activity，不要使用getApplication()
	 * @param data
	 *            上传数据
	 *            _id 当前情景，目前支持 enterServer，levelUp，createRole
	 *            游戏方需在进入服务器、角色升级、创建角色处分别调用 roleId 当前登录的玩家角色ID，必须为数字，若如，传入userid
	 *            roleName 当前登录的玩家角色名，不能为空，不能为null，若无，传入"游戏名称+username"
	 *            roleLevel 当前登录的玩家角色等级，必须为数字，若无，传入1 zoneId
	 *            当前登录的游戏区服ID，必须为数字，若无，传入1 zoneName
	 *            当前登录的游戏区服名称，不能为空，不能为null，若无，传入"无区服" balance
	 *            当前用户游戏币余额，必须为数字，若无，传入0 vip 当前用户VIP等级，必须为数字，若无，传入1 partyName
	 *            当前用户所属帮派，不能为空，不能为null，若无，传入"无帮派"
	 */
	private void doSetExtData() {
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("_id", "enterServer");
		datas.put("roleId", "13524696");
		datas.put("roleName", "方木");
		datas.put("roleLevel", "24");
		datas.put("zoneId", "1");
		datas.put("zoneName", "墨土1区");
		datas.put("balance", "88");
		datas.put("vip", "2");
		datas.put("partyName", "无尽天涯");
		JSONObject obj = new JSONObject(datas);
		GameProxy.getInstance().setExtData(this, obj.toString());
	}

	/**
	 * 用于获取渠道标识，游戏开发者可在任意处调用该方法获取到该字段，含义请参照《如何区分渠道》中的渠道与ChannelLabel对照表
	 * 
	 * @return
	 */
	public String getChanelLabel() {
		return XMUtils.getChannelLabel(this);
	}
	
	public void showToast(final String content) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();				
			}
		});
	}

	@Override
	public void onLoginSuccess(final XMUser user, Object customParams) {
		
		
		runOnGLThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loginSuccess("userInfo", 1);
			}
		});
		
		isLogin = true;
		
		showToast("登录成功");
		//登陆成功之后需要调用用户信息扩展接口
		doSetExtData();
		mUser = user;
		//以下代码仅供demo测试是弹出用户信息，CP请勿照搬。
		AlertDialog.Builder builder = new Builder(HelloWorld.this);
		builder.setTitle("登陆成功的用户信息");
		builder.setMessage("username: " + mUser.getUsername()
				+ "\nuid:" + mUser.getUserID() + "\ntoken: "
				+ mUser.getToken() + "\nchannelCode: "
				+ mUser.getChannelID() + "\nchannelUserId:"
				+ mUser.getChannelUserId() + "\nchannellabel: "
				+ mUser.getChannelLabel());
		dialog = builder.create();
		dialog.show();
		// 登录成功后，进行登录信息校验，此步为必须完成操作，若不完成用户信息验证，我方平台拒绝提包
	}

	@Override
	public void onLoginFailed(String reason, Object customParams) {
		showToast("登录失败");
	}

	@Override
	public void onLogout(Object customParams) {
		// customObject为logout方法中传入的参数，原样返回
		mUser = null;
		showToast("已成功登出");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
    
}
