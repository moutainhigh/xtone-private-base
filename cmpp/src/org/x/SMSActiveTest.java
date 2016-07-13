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
 * 用于发送网关连接测试包。
 */
import comsd.commerceware.cmpp.*;
import java.lang.*;
import java.io.*;
import com.xiangtone.util.FormatSysTime;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SMSActiveTest implements Runnable {
	CMPP p = new CMPP();
  	public static conn_desc con = new conn_desc();
  	cmppe_login cl = new cmppe_login();
  	CMPPSingleConnect cmppcon = CMPPSingleConnect.getInstance();
  	
  	public SMSActiveTest() 
  	{		
  					
  	}
  	/**
  	*
  	*
  	*/
  	public void run()
  	{
  		//////////////////日志记录/////////////
  		/*
	 		Logger myLogger = Logger.getLogger("MsgSendLogger");
	  	Logger mySonLogger = Logger.getLogger("myLogger.mySonLogger");
    	PropertyConfigurator.configure("log4j.properties");
*/
	 		//////////////////////////////////////
  		try
  		{
  			int i = 0;
  				while(true)
  				{
  					System.out.println("-------send active test -------");
  					if(cmppcon == null )
  					{
  						cmppcon = CMPPSingleConnect.getInstance();
  					}
                	try
                	{	
                		p.cmpp_active_test(cmppcon.con);
                		/*
                		i++;
                		if(i%6 == 0){
                			myLogger.info(FormatSysTime.getCurrentTimeA() + "ActiveTest Msg");
                		}
                		if(i == 90000){
                			i = 0;	
                		}
                		*/
                			
                	}
                	catch(Exception e)
                	{
                		Logger myLogger = Logger.getLogger("MsgSendLogger");
	  								Logger mySonLogger = Logger.getLogger("myLogger.mySonLogger");
//    								PropertyConfigurator.configure("log4j.properties");
                		myLogger.info(FormatSysTime.getCurrentTimeA() + "testActive exception msg--Exception:" + e.toString());

                		System.out.println(e.toString());
                		System.out.println("重新连接...");
    		    	    p.cmpp_disconnect_from_ismg(con);
    		    	    cmppcon =null;
    		    	    cmppcon = CMPPSingleConnect.getInstance(); //重连
                	}
  					Thread.currentThread().sleep(2500);
  			}
  			
  		}
  		catch(Exception e)
  		{
  			System.out.println(e.toString());
  		}	
  	}	 	
}
