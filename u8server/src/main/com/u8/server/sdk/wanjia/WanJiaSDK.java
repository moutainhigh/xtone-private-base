package com.u8.server.sdk.wanjia;

import com.u8.server.data.UChannel;
import com.u8.server.data.UOrder;
import com.u8.server.data.UUser;
import com.u8.server.log.Log;
import com.u8.server.sdk.*;
import com.u8.server.sdk.wanjia.AuthInfo;
import com.u8.server.utils.JsonUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ant on 2016/6/16.
 */
public class WanJiaSDK implements ISDKScript {

	@Override
	public void verify(final UChannel channel, String extension, final ISDKVerifyListener callback) {

		try {

			JSONObject json = JSONObject.fromObject(extension);
			final String sid = json.getString("token");

			Map<String, String> params = new HashMap<String, String>();
			params.put("token", sid);

			UHttpAgent.getInstance().get(channel.getChannelAuthUrl(), params, new UHttpFutureCallback() {
				@Override
				public void completed(String content) {

					Log.e("The wanjia auth result is " + content);
					try {

						AuthInfo info = (AuthInfo)JsonUtils.decodeJson(content, AuthInfo.class);
						
						if (info != null && info.getStatus().equals("success")) {
							SDKVerifyResult vResult = new SDKVerifyResult(true, sid + "", "", "",info.getData().getToken());
							callback.onSuccess(vResult); 
							return;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					callback.onFailed(
							channel.getMaster().getSdkName() + " verify failed. the auth result is " + content);
				}

				@Override
				public void failed(String e) {
					callback.onFailed(channel.getMaster().getSdkName() + " verify failed. " + e);
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
			callback.onFailed(
					channel.getMaster().getSdkName() + " verify execute failed. the exception is " + e.getMessage());
		}

	}

	@Override
	public void onGetOrderID(UUser user, UOrder order, ISDKOrderListener callback) {
		if (callback != null) {
			callback.onSuccess("");
		}
	}

}
