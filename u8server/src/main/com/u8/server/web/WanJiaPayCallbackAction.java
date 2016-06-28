package com.u8.server.web;

import com.google.gson.Gson;
import com.u8.server.common.UActionSupport;
import com.u8.server.constants.PayState;
import com.u8.server.data.UChannel;
import com.u8.server.data.UOrder;
import com.u8.server.log.Log;
import com.u8.server.sdk.wanjia.PayInfo;
import com.u8.server.service.UOrderManager;
import com.u8.server.utils.EncryptUtils;
import com.u8.server.utils.HmacSHA1Encryption;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.util.TextUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 万家SDK充值回调接口
 * Created by ant on 2016/6/20.
 */
@Controller
@Namespace("/pay/wanjia")
public class WanJiaPayCallbackAction extends UActionSupport{
	
	
	private String payment;
	
	@Autowired
    private UOrderManager orderManager;

    @Action("payCallback")
    public void payCallback(){

        try{
        	Log.i("payment: "+payment);
        	
        	Gson gson = new Gson();
        	PayInfo info = gson.fromJson(payment, PayInfo.class);
        	
            long orderID = Long.parseLong(info.getCpOrderId());
            
            Log.i("=======================================================================================");
            Log.i("");
            Log.i("            The /pay/wanjia/payCallback orderID is "+orderID);
            Log.i("");
            Log.i("=======================================================================================");

            UOrder order = orderManager.getOrder(orderID);

            if(order == null || order.getChannel() == null){
                Log.d("The order is null or the channel is null.");
                Log.i("The order is null or the channel is null.");
                this.renderState(false);
                return;
            }

            if(order.getState() == PayState.STATE_COMPLETE){
                Log.d("The state of the order is complete. The state is "+order.getState());
                Log.i("The state of the order is complete. The state is "+order.getState());
                this.renderState(true);
                return;
            }


            if(isValid(order.getChannel())){
                order.setRealMoney(info.getPrice());
                order.setSdkOrderTime("");
                order.setCompleteTime(new Date());
                order.setChannelOrderID(info.getWebOrderid());
                order.setState(PayState.STATE_SUC);
                orderManager.saveOrder(order);
                Log.i("sendCallbackToServer!");
                SendAgent.sendCallbackToServer(this.orderManager, order);
                this.renderState(true);
            }else{
            	Log.i("isValid ERROE!");
                order.setChannelOrderID(info.getCpOrderId());
                order.setState(PayState.STATE_FAILED);
                orderManager.saveOrder(order);
                this.renderState(true);
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private boolean isValid(UChannel channel){
    	Log.i("The WanjiaSDK is not Valid!");
        return true;
    }

    private void renderState(boolean suc) throws IOException {

        String res = "200";
        if(suc){
            //PrintWriter out = this.response.getWriter();
        	this.response.getWriter().append("200");
            //out.write(res);
            //out.flush();
        }


    }

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public UOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(UOrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
	
	
	
	

}
