package org.x;

/*
 * Created on 2006-11-15
 *
 * 用于发送网关连接测试包。
 */
import comsd.commerceware.cmpp.*;
import java.lang.*;
import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.xiangtone.util.FormatSysTime;

public class SMSActiveTest implements Runnable {
	CMPP p = null;// new CMPP();
	public ConnDesc con;// = new conn_desc();
	CmppeLogin cl = new CmppeLogin();
	CMPPSingleConnect cmppcon;// = CMPPSingleConnect.getInstance();
	private static Logger logger = Logger.getLogger(SMSActiveTest.class);

	public SMSActiveTest() {
		p = new CMPP();
		cmppcon = CMPPSingleConnect.getInstance();
		con = cmppcon.con;

	}

	public void run() {
		try {
			int i = 0;
			while (true) {
				logger.debug("send active test");
				try {
					p.cmppActiveTest(cmppcon.con);
					Thread.currentThread().sleep(3000);
				} catch (Exception e) {
					logger.error("testActive exception msg--Exception:", e);
					logger.debug("重新连接...");
					p.cmppDisConnectFromIsmg(con);
					cmppcon.destroy();

					// cmppcon =null;
					try {
						Thread.currentThread().sleep(10 * 1000);
						cmppcon = CMPPSingleConnect.getInstance(); // 重连
						con = cmppcon.con;

					} catch (Exception e1) {
						logger.error("重连失败:", e);
					}
					// cmppcon = CMPPSingleConnect.getInstance(); //重连
				}

			}

		} catch (Exception e) {
			logger.error("SMSActiveTest", e);
		}
	}
}
