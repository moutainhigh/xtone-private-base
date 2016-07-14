package org.x;
/*
 * Created on 2006-11-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import comsd.commerceware.cmpp.*;

import com.xiangtone.util.ConfigManager;
import com.xiangtone.util.MailUtil;

public class CMPPSingleConnect {
	private  static CMPPSingleConnect cmppcon = null;
    private  CMPP p = new CMPP();
  	public   static conn_desc con = new conn_desc();
  	private  cmppe_login cl = new cmppe_login();
  	public static int count=0;
  	private int maxConnect=Integer.parseInt(ConfigManager.getInstance().getConfigData("max_connect"));
  	private CMPPSingleConnect(){
  		connectIsmg();
  	}
  	public static synchronized CMPPSingleConnect getInstance(){
  		if(cmppcon == null){
  			cmppcon = new CMPPSingleConnect();		
  		}
  		return cmppcon;
  	}
  	
  	private void connectIsmg(){
  		try{
  			System.out.println("login gateway:" + SMSIsmgInfo.gd_ismg_ip);
			p.cmpp_connect_to_ismg(SMSIsmgInfo.gd_ismg_ip, SMSIsmgInfo.gd_ismg_port, con);
  			cl.set_icpid(SMSIsmgInfo.gd_icpID);
  			cl.set_auth(SMSIsmgInfo.gd_icpShareKey);
  			cl.set_version((byte)0x30);
  			cl.set_timestamp(1111101020);
  			p.cmpp_login(con,cl);
  			count=0;
  		}catch(Exception e){
  			count++;
  			if(count>=maxConnect){
  				count=0;
  				MailUtil.send("短信网关连接异常", ConfigManager.getInstance().getConfigData("SENDMAIL"), ConfigManager.getInstance().getConfigData("MAILTO"), "短信网关尝试重连次数超过"+maxConnect+"次！");
  			}
  			System.out.println("err:login ismg failed! --CMPP_receive.java");
    		System.out.println(e.toString());
  		}
  	}
  	synchronized public static void destroy()
	{
		System.out.println("destory connect instance.......");
		cmppcon = null;
	}
  	
}
