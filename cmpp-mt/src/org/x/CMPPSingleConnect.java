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

import java.lang.*;

import org.apache.log4j.Logger;

import java.io.*;
public class CMPPSingleConnect {
	private  static CMPPSingleConnect cmppcon = null;
	private static Logger logger = Logger.getLogger(Test.class);
    private  CMPP p = new CMPP();
  	public   static ConnDesc con = new ConnDesc();
  	private  CmppeLogin cl = new CmppeLogin();
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
  			System.out.println("登陆北京网关:" + SMSIsmgInfo.qwIsmgIp);
  			p.cmppConnectToIsmg(SMSIsmgInfo.qwIsmgIp,SMSIsmgInfo.qwIsmgPort,con);
  			cl.setIcpid(SMSIsmgInfo.qwIcpID);
  			cl.setAuth(SMSIsmgInfo.qwIcpShareKey);
  			cl.setVersion((byte)0x30);
  			cl.setTimestamp(1111101020);
  			p.cmppLogin(con,cl);
  		}catch(Exception e){
  			logger.error("login ismg failed!",e);
    		e.printStackTrace();
  		}
  	}
  	synchronized public static void destroy()
	{
  		logger.debug("destory connect instance.......");
		cmppcon =null;
	}
}
