package com.thirdpay.main;

import org.common.util.ConfigManager;

public class Main {

	public static void main(String[] args) {
	    // TODO Auto-generated method stub
	    ServiceScan ss = ServiceScan.getInstance();
	    while (true) {
	      ss.scan();
	      try {
	    	  //线程休眠时间
	        Thread.sleep(Long.parseLong(ConfigManager.getConfigData("nextTryInterval")));
	      } catch (NumberFormatException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	    }
	  }

}
