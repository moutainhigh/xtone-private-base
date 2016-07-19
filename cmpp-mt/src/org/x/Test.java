package org.x;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import comsd.commerceware.cmpp.CMPP;
import comsd.commerceware.cmpp.OutOfBoundsException;

public class Test {
//	private static Logger logger = Logger.getLogger(.class);
	private static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args){
		byte[] b={1,2};
		System.out.println(Arrays.toString(b));
		
		try {
			Socket socket = new Socket("12", 21);
			logger.debug(socket);
			List s=null;
			s.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e,e);
		}
		logger.error("e");
		logger.info("i");
		logger.debug("d");
		logger.warn("w");
		System.out.println("0");
		try {
			new CMPP().cmppCancel(null, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e,e);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			logger.error(e,e);
		}
//		logger.error("e");
//		logger.error("e");
//		logger.error("e");
	}
}

