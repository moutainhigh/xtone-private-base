package org.x;
/**

*Copyright 2003 Xiamen Xiangtone Co. Ltd.

*All right reserved.

*/

import java.io.*;

import java.net.*;

import org.apache.log4j.Logger;

public class VCPServer extends Thread {
	private static Logger logger = Logger.getLogger(Test.class);
	protected int listenPort;

	public VCPServer(int aListenPort) {
		listenPort = aListenPort;
	}

	public void acceptConnections() {

		try {

			ServerSocket server = new ServerSocket(listenPort, 1000);

			logger.debug("接收vcp信息的服务线程.... on port " + listenPort + " waiting ...");

			Socket incomingConnection = null;

			while (true) {

				incomingConnection = server.accept();

				// System.out.println(incomingConnection);

				handleConnection(incomingConnection);
			}

		}

		catch (BindException e) {
			e.printStackTrace();
			logger.debug("Unable to bind to port " + listenPort);

		}

		catch (IOException e) {
			e.printStackTrace();
			logger.debug("Unable to instantiate a ServerSocket on port: " + listenPort);

		}

	}

	public void handleConnection(Socket connectionToHandle) {

		new Thread(new VCPConnectionHandler(connectionToHandle)).start();

	}

	public void run() {

		VCPServer server = new VCPServer(listenPort);

		server.acceptConnections();

	}
	/*
	 * private void writeLog(String logStr){
	 * 
	 * Logger myLogger = Logger.getLogger("MsgSendLogger"); Logger mySonLogger =
	 * Logger.getLogger("myLogger.mySonLogger");
	 * PropertyConfigurator.configure("log4j.properties"); //logStr =
	 * this.compUrl + "\n" + logStr; myLogger.info(logStr); }
	 */
}
