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
 * ���ڷ����������Ӳ��԰���
 */
import comsd.commerceware.cmpp.*;
import comsd.commerceware.cmpp.CMPP;
import java.lang.*;
import java.io.*;
import com.xiangtone.util.FormatSysTime;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SMSActiveTest implements Runnable {
	CMPP p = null;//new CMPP();
  	//public static conn_desc con = new conn_desc();
  	public conn_desc con ;//= new conn_desc();
  	cmppe_login cl = new cmppe_login();
  	CMPPSingleConnect cmppcon ;//= CMPPSingleConnect.getInstance();
  	
  	public SMSActiveTest() 
  	{		
  						//p = new CMPP();
  		//instance2 = CMPPSingleConnect_fj.getInstance();
  		//con = instance2.con;		
  					p = new CMPP();
  					cmppcon = CMPPSingleConnect.getInstance();
  					con = cmppcon.con;
  					
  	}
  	/**
  	*
  	*
  	*/
  	public void run()
  	{
  		//////////////////��־��¼/////////////
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
  					//if(cmppcon == null )
  					//{
  						//cmppcon = CMPPSingleConnect.getInstance();
  					//}
                	try
                	{	
                		p.cmpp_active_test(cmppcon.con);
                		Thread.currentThread().sleep(3000);
                		//i++;
                		/*
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
                		System.out.println("��������...");
    		    	    p.cmpp_disconnect_from_ismg(con);
    		    	    cmppcon.destroy();
  								
    		    	    //cmppcon =null;
    		    	    try{
    		    	    	Thread.currentThread().sleep(10 * 1000);
    		    	    	cmppcon = CMPPSingleConnect.getInstance(); //����
  									con = cmppcon.con;
  									myLogger.info(FormatSysTime.getCurrentTimeA() + "ƽ̨0������������:" + e.toString());
    		    	    }catch(Exception e1){
    		    	    		
    		    	    }
    		    	    //cmppcon = CMPPSingleConnect.getInstance(); //����
                	}
  					
  			}
  			
  		}
  		catch(Exception e)
  		{
  			System.out.println(e.toString());
  		}	
  	}	 	
}
