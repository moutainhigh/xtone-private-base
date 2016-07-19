package org.x;
/*

 * Created on 2006-11-15

 *

 * TODO To change the template for this generated file go to

 * Window - Preferences - Java - Code Style - Code Templates

 */

import org.apache.log4j.Logger;

import com.xiangtone.util.FormatSysTime;
import com.xiangtone.util.IntByteConvertor;

import comsd.commerceware.cmpp.CMPP;
import comsd.commerceware.cmpp.CmppeDeliverResult;
import comsd.commerceware.cmpp.CmppeResult;
import comsd.commerceware.cmpp.CmppeSubmitResult;
import comsd.commerceware.cmpp.ConnDesc;

public class SMSRecive implements Runnable {

	CMPP p = new CMPP();

	// connDesc con = new connDesc();

	CmppeDeliverResult cd = new CmppeDeliverResult();

	CmppeSubmitResult sr = new CmppeSubmitResult();

	CmppeResult cr = new CmppeResult();

	public SMSoperate handle;

	public CMPPSingleConnect cmppcon = CMPPSingleConnect.getInstance();;
	ConnDesc con = cmppcon.con;
	public static final String ISMGID = "01";

	public SMSRecive()

	{

		handle = new SMSoperate();

		// cmppcon = CMPPSingleConnect.getInstance();

		// con = cmppcon.con;

	}

	public void run() {

		////////////////// 日志记录/////////////

		// ThreadPoolManager moPoolManger = new
		// ThreadPoolManager(500,this.ISMGID);

		//////////////////////////////////////

		while (true) {

			try {

				// SMSoperate handle = new SMSoperate();

				p.readPa(con);
				Thread.currentThread().sleep(100);
				if (sr.flag == 0) // submit resp

				{

					sr.flag = -1; // 复位

					String strRespMsgId = IntByteConvertor.getLong(sr.msgId, 0) + "";// MyTools.Bytes2HexString(sr.msgId);MyTools.Bytes2HexString(sr.msgId);//new
																						// String(sr.msgId2)//IntByteConvertor.getLong(sr.msgId,0)
																						// +
																						// "";//MyTools.Bytes2HexString(sr.msgId);

					int iRespResult = sr.result;

					int iRespSeq = sr.seq;

					System.out.println("sr.result:" + iRespResult);

					System.out.println("sr.seq:" + iRespSeq);

					System.out.println("sr.msgId:" + strRespMsgId);

					handle.receiveSubmitResp(this.ISMGID, (int) iRespSeq, (String) strRespMsgId, (int) iRespResult);

					// handle.receiveSubmitResp(this.ISMGID,(int)iRespSeq,(String)strRespMsgId,(int)iRespResult);

				}

				if (cd.STAT == 0) // 说明有消息上来了

				{

					cd.STAT = -1;

					System.out.println("有消息上来了...");

					/////////////////////////////

					// moPoolManger.process1(cd);

					/////////////////////// 进行重构，加入线程池 date:2008-11-26 16:09

					String msgId = IntByteConvertor.getLong(cd.msgId, 0) + "";// MyTools.Bytes2HexString(cd.getMsgId());

					String strIsmgid = this.ISMGID;

					String strSpcode = cd.getSPCode();

					String strCpn = cd.getCpn();

					int cpnType = cd.getsrcType();// add 061121

					int iLen = cd.getLen();

					int iFmt = cd.getFmt();

					int iTp_udhi = cd.getTpUdhi();

					String strSvcType = cd.getServerType();

					String linkId = cd.getLinkId();

					System.out.println("content:" + cd.getMessage());

					byte[] strContent = cd.getMessage();

					int iReportFlag = cd.getRegisteredDelivery();

					System.out.println("iReportFlag:" + iReportFlag);

					System.out.println("........................");

					System.out.println("........................");

					System.out.println("........................");

					System.out.println("strSpcode:" + strSpcode);

					System.out.println("........................");

					System.out.println("........................");

					System.out.println("........................");

					// myLogger.info(FormatSysTime.getCurrentTimeA() + " new
					// msg--spcode:"
					// + strSpcode +" cpn:" + strCpn.trim() + " linkId:" +
					// linkId + "
					// content:" + new String(strContent));

					if (iReportFlag == 1)

					{

						System.out.println("状态报告信息....");

						String reportDestCpn = cd.getDestCpn();

						msgId = IntByteConvertor.getLong(cd.msgId2, 0) + "";// MyTools.Bytes2HexString(cd.getMsgId());

						String submitTime = cd.getSubmitTime();

						String doneTime = cd.getDoneTime();

						String stat2 = cd.getStat();

						System.out.println("stat2:" + stat2);

						// myLogger.info(FormatSysTime.getCurrentTimeA() +
						// "report
						// msg--spcode:" + strSpcode +" cpn:" +
						// reportDestCpn.trim() + "
						// msgid:" + msgId + " submitTime:" + submitTime +"
						// doneTime:" +
						// doneTime + " statDev:" + stat2);

						int statDev = 0;

						if (stat2.equals("DELIVRD"))

							statDev = 0;

						else

							statDev = -1;

						handle.receiveReport(this.ISMGID, msgId, linkId, reportDestCpn, strSpcode, strCpn, submitTime,
								doneTime, statDev, stat2);

						// handle.receiveReport(this.ISMGID,msgId,reportDestCpn,strSpcode,strCpn,submitTime,doneTime,statDev);

						continue;

					}

					cd.printAll(); // 打印mo消息;

					///////////////////////////////
					///////////////////////////////

					handle.setDeliverIsmgID(strIsmgid);

					handle.setDeliverMsgID(msgId);

					handle.setDeliverSpCode(strSpcode);

					handle.setDeliverServerID(strSvcType);

					handle.setDeliverFmt(iFmt);

					handle.setDeliverSrcCpn(strCpn);

					handle.setDeliverSrcCpnType(cpnType);

					handle.setDeliverContentLen(iLen);

					handle.setDeliverContent(strContent);

					handle.setDeliverLinkId(linkId);

					handle.receiveDeliver();

				}

				Thread.currentThread().sleep(100);
			} catch (Exception e) {

				Logger myLogger = Logger.getLogger("MsgSendLogger");

				Logger mySonLogger = Logger.getLogger("myLogger.mySonLogger");

				// PropertyConfigurator.configure("log4j.properties");

				myLogger.info(FormatSysTime.getCurrentTimeA() + " exception msg--Exception:" + e.toString());

				System.out.println(e.toString());

				System.out.println("SmsRecive 重新连接....");

				p.cmppDisconnectFromIsmg(con);

				cmppcon.destroy();

				cmppcon = null;

				try {

					Thread.currentThread().sleep(30 * 1000);

				} catch (Exception e1) {

				}

				cmppcon = CMPPSingleConnect.getInstance(); // 重连

				con = cmppcon.con;

			}

		}

	}

}
