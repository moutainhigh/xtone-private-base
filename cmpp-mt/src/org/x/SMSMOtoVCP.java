package org.x;

/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/
import java.io.*;
import java.util.*;
import com.xiangtone.sms.api.*;
import com.xiangtone.util.*;

import comsd.commerceware.cmpp.ConnDesc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
*
*
*/
public class SMSMOtoVCP {

	private static Logger logger = Logger.getLogger(SMSMOtoVCP.class);
	public Message xtsms;
	public SmDeliver xtdeliver;
	public SmDeliverAckResult xtdeliver_ack;
	public ConnDesc xtconn;
	// public SMSMO smsmo;

	String vcpIp1 = (String) ConfigManager.getInstance().getConfigItem("vcp_ip1", "vcp_ip1 not found");
	String vcpPort1 = (String) ConfigManager.getInstance().getConfigItem("vcp_port1", "vcp_port1 not found");
	String vcpPort2 = (String) ConfigManager.getInstance().getConfigItem("vcp_port2", "vcp_port2 not found");
	String vcpPort3 = (String) ConfigManager.getInstance().getConfigItem("vcp_port3", "vcp_port2 not found");
	String vcpPort4 = (String) ConfigManager.getInstance().getConfigItem("vcp_port4", "vcp_port2 not found");

	String vcpIp2 = (String) ConfigManager.getInstance().getConfigItem("vcp_ip2", "vcp_ip2 not found");
	String vcpip2port2 = (String) ConfigManager.getInstance().getConfigItem("vcpip2_port2", "vcp_port2 not found");

	// String vcp_ip1="192.168.1.154";
	// int vcp_port1=7100;
	// String vcp_ip2 = "192.168.1.4";
	// String vcp_ip2="192.168.1.154";
	// int vcp_port2=7200;

	public SMSMOtoVCP() {
		xtsms = new Message();
		xtdeliver_ack = new smDeliverAckResult();
		// xtdeliver = new sm_deliver();
		xtconn = new ConnDesc();
		// smsmo = new smsmo();
	}

	public String send_mosms_to_vcp(SMSMO smsmo) {

		String stat = "-1";
		try {

			System.out.println("send..cpn:" + smsmo.getMOCpn());
			System.out.println("send..spcode:" + smsmo.getMOSpCode());
			System.out.println("send.serverAction:" + smsmo.getMOServerAction());
			System.out.println("send.serverAction:" + smsmo.getMOLinkID());

			xtdeliver = new SmDeliver();
			xtdeliver.set_mobileCode(smsmo.getMOCpn());
			xtdeliver.set_mobileType(smsmo.getMOCpnType());
			xtdeliver.set_gameCode(smsmo.getMOServerName());
			xtdeliver.set_actionCode(smsmo.getMOServerAction());
			xtdeliver.set_spCode(smsmo.getMOSpCode());
			xtdeliver.setIsmgCode(smsmo.getMOIsmgID());
			xtdeliver.set_linkID(smsmo.getMOLinkID());
			xtdeliver.set_MsgId(smsmo.getMOMsgId());

			logger.debug("开始连接...发送mo消息....");

			int vcpID = smsmo.getMO_vcpID();
			logger.debug("派发给的vcpID:" + vcpID);

			switch (vcpID) {
			case 0:
				logger.debug(vcpIp1);
				xtsms.connect_to_server(vcpIp1, Integer.parseInt(vcpPort1), xtconn); // 连接服务器
				break;
			case 1:
				logger.debug(vcpIp1);
				xtsms.connect_to_server(vcpIp1, Integer.parseInt(vcpPort2), xtconn); // 连接服务器
				break;
			case 2:
				logger.debug(vcpIp1);
				xtsms.connect_to_server(vcpIp1, Integer.parseInt(vcpPort3), xtconn);
				// xtsms.connect_to_server(vcpIp2,Integer.parseInt(vcp_port2),xtconn);
				break;
			case 3:
				logger.debug(vcpIp1);
				xtsms.connect_to_server(vcpIp1, Integer.parseInt(vcpPort4), xtconn);
				break;
			default:
				logger.debug(vcpIp1);
				xtsms.connect_to_server(vcpIp1, Integer.parseInt(vcpPort1), xtconn); // 连接服务器
				break;
			/*
			 * case 1: System.out.println(vcpIp1);
			 * xtsms.connect_to_server(vcpIp1,Integer.parseInt(vcp_port1),
			 * xtconn); // 连接服务器 break; case 2: System.out.println(vcpIp2);
			 * xtsms.connect_to_server(vcpIp2,Integer.parseInt(vcp_port2),
			 * xtconn); break; default: System.out.println(vcpIp1);
			 * xtsms.connect_to_server(vcpIp1,Integer.parseInt(vcp_port1),
			 * xtconn); // 连接服务器 break;
			 */
			}

			xtsms.sendSmDeliver(xtconn, xtdeliver); // 提交信息
			xtsms.readPa(xtconn);// 读取返回

			stat = xtdeliverAck.getAckStat();
			logger.debug("stat:::::" + stat);
			logger.debug("xtconn::::" + xtconn);
			xtsms.disconnect_from_server(xtconn);
		} catch (Exception e) {
//			Logger myLogger = Logger.getLogger("MsgSendLogger");
//			Logger mySonLogger = Logger.getLogger("myLogger.mySonLogger");
//			PropertyConfigurator.configure("mo2vcplog4j.properties");
//			myLogger.info(FormatSysTime.getCurrentTimeA() + " exception mo2vcp--Exception:" + e.toString());
			logger.error("断开连接",e);
			e.printStackTrace();
		}
		return stat;
	}
}
