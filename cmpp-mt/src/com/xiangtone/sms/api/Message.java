package com.xiangtone.sms.api;
/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/

import java.io.*;
import java.net.Socket;

import comsd.commerceware.cmpp.ByteCode;
import comsd.commerceware.cmpp.ConnDesc;
import comsd.commerceware.cmpp.UnknownPackException;

public class Message {

	public Message() {
	}

	// connect xtsms platform server
	public void connectToServer(String host, int port, ConnDesc conn) throws IOException {
		Socket s = null;
		try {
			s = new Socket(host, port);
			s.setSoTimeout(0x927c0);
			System.out.println(s.toString());
		} catch (IOException e) {
			throw e;
		}
		conn.sock = s;

	}

	// disconnect from xtsms platform server
	public void disconnectFromServer(ConnDesc conn) {
		try {
			conn.sock.getOutputStream().close();
			conn.sock.getInputStream().close();
			conn.sock.close();
			conn.sock = null;
			// conn.sock.close();
		} catch (Exception e) {
			return;
		}
	}

	// send SmDeliver
	public void sendSmDeliver(ConnDesc conn, SmDeliver deliver) throws IOException {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(conn.sock.getOutputStream());

			byte[] buf = deliver.getBytes(); // 信息体 message body
			int body_len = buf.length; // 信息体长度
			byte[] header = new byte[8]; // 信息头
			ByteCode bc = new ByteCode(8);
			System.out.println(8 + body_len);
			bc.AddInt(8 + body_len); // 信息头 add total length
			bc.AddInt(StateCode.sm_deliver); // 信息头 add message type
			// bc.AddInt(3);
			bc.AddBytes(buf);// 信息体 add message body
			out.write(bc.getBytes());
			out.flush();

		} catch (IOException e) {
			out = null;
			throw e;
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	// send SmSubmit
	public void sendSmSubmit(ConnDesc conn, SmSubmit sub) throws IOException {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(conn.sock.getOutputStream());

			byte[] buf = sub.getBytes(); // 信息体 message body
			int body_len = buf.length; // 信息体长度
			byte[] header = new byte[8]; // 信息头
			ByteCode bc = new ByteCode(8);

			bc.AddInt(8 + body_len); // 信息头 add total length
			bc.AddInt(StateCode.sm_submit); // 信息头 add message type
			// bc.AddInt(3);
			bc.AddBytes(buf);// 信息体 add message body
			out.write(bc.getBytes());
			out.flush();
		} catch (IOException e) {
			out = null;
			throw e;
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	// read message head
	protected boolean readHead(DataInputStream in, SmPack p) throws IOException {
		try {
			p.pk_head.pk_len = in.readInt();
			p.pk_head.pkCmd = in.readInt();
			System.out.println("readHead ...pkCmd:" + p.pk_head.pkCmd);
		} catch (IOException e) {
			throw e;
		}
		return true;
	}

	// send message head
	protected void sendHeader(SmHeader ch, DataOutput out) throws IOException {
		try {
			ByteCode bc = new ByteCode(4); // 4 bytes of head

			bc.AddInt32(ch.pk_len);
			bc.AddInt32(ch.pkCmd);

			out.write(bc.getBytes());
		} catch (IOException e) {

			System.out.println("send Head Exception" + e.getMessage());
			throw e;
		}
	}

	// read respack
	public void readPa(ConnDesc conn) {

		SmResult sr = null;

		try {
			sr = readResPack(conn);

			switch (sr.packCmd) {
			case 1: // StateCode.SmSubmit
				SmSubmitResult ssr = (SmSubmitResult) sr;
				System.out.println("----receiver vcp ------stat=" + ssr.stat);
				System.out.println("----receiver vcp submit message------");
				smSendSubmitAck(conn, ssr.stat);
				break;
			case 2: // StatCode.SmSubmitAck
				SmSubmitAckResult ssra = (SmSubmitAckResult) sr;
				System.out.println("--------" + ssra.stat);
				break;
			case 3:
				SmDeliverResult sdr = (SmDeliverResult) sr;
				System.out.println(" ------receiver platform-----stat =" + sdr.stat);
				smSendDeliverAck(conn, sdr.stat);
				break;
			case 4:
				SmDeliverAckResult sdar = (SmDeliverAckResult) sr;
				System.out.println("---------deliverAck-----:" + sdar.stat);
				break;

			default:
				System.out.println("---------Error packet-----------");
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println("have a exception");
		} finally {
			try {
				if (conn.sock != null)
					conn.sock.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// send SmSubmitAck
	public void smSendSubmitAck(ConnDesc conn, String ackCode) throws Exception {
		SmHeader ch = new SmHeader();
		try {
			DataOutput out = new DataOutputStream(conn.sock.getOutputStream());
			ch.pkCmd = 2;// SmSubmitAck
			int len1 = ackCode.getBytes().length;
			ch.pk_len = 8 + 3 + len1;
			sendHeader(ch, out);

			ByteCode ack = new ByteCode(3);
			ack.AddByte(StateCode.ACKCODE); // add Type
			ack.AddInt16((short) (3 + len1)); // add length
			ack.addAsciiz(ackCode, len1); // add value string
			byte[] b = ack.getBytes();
			for (int i = 0; i < b.length; i++)
				System.out.print(b[i] + ",");
			out.write(ack.getBytes());

		} catch (Exception e) {
			throw e;
		}

	}

	// send SmDeliverAck
	public void smSendDeliverAck(ConnDesc conn, String ackCode) throws Exception {
		SmHeader ch = new SmHeader();
		try {
			DataOutput out = new DataOutputStream(conn.sock.getOutputStream());
			ch.pkCmd = 4;// SmDeliverAck
			int len1 = ackCode.getBytes().length;
			ch.pk_len = 8 + 3 + len1;
			sendHeader(ch, out);

			ByteCode ack = new ByteCode(3);
			ack.AddByte(StateCode.ACKCODE); // add Type
			ack.AddInt16((short) (3 + len1)); // add length
			ack.addAsciiz(ackCode, len1); // add value string
			byte[] b = ack.getBytes();
			for (int i = 0; i < b.length; i++)
				System.out.print(b[i] + ",");
			out.write(ack.getBytes());

		} catch (Exception e) {
			throw e;
		}

	}

	public SmResult readResPack(ConnDesc conn) throws IOException, UnknownPackException {
		DataInputStream in = null;
		SmResult sr = new SmResult();
		SmPack pack = new SmPack();

		in = new DataInputStream(conn.sock.getInputStream());

		readHead(in, pack); // read header
		// System.out.println("total_len:" +pack.pk_head.pk_len);
		byte packbuf[] = new byte[pack.pk_head.pk_len - 8];
		in.read(packbuf); // read body message
		//////////////////// add at 061206
		// for(int k = 0;k < packbuf.length;k++){
		// System.out.print(packbuf[k] + " ");
		// }
		//////////////////////////////////
		switch (pack.pk_head.pkCmd) {

		case 1:
			System.out.println("------- Case 1 -------");
			SmSubmitResult ssr = new SmSubmitResult();
			try {
				ssr.readInBytes(packbuf); // 处理信息体
				ssr.packCmd = 1;
				return ssr;
			} catch (Exception e1) {
				throw new UnknownPackException();
			}
			// break;
		case 2:
			System.out.println("-------case 2--------");
			SmSubmitAckResult ssra = new SmSubmitAckResult();
			try {
				ssra.readInBytes(packbuf);
				ssra.packCmd = 2;
				return ssra;
			} catch (Exception e) {
				throw new UnknownPackException();
			}

		case 3:
			System.out.println("-------Case 3 --------");
			SmDeliverResult sdr = new SmDeliverResult();
			try {
				sdr.readInBytes(packbuf);
				sdr.packCmd = 3;
				return sdr;
			} catch (Exception e2) {
				throw new UnknownPackException();
			}
			// break;
		case 4:
			System.out.println("-----case 4------");
			SmDeliverAckResult adar = new SmDeliverAckResult();
			try {
				adar.readInBytes(packbuf);
				adar.packCmd = 4;
				return adar;
			} catch (Exception e) {
				throw new UnknownPackException();
			}

		default:
			// throw new UnknowPackException();
			break;

		}
		UnknownPackException un = new UnknownPackException();
		throw un;
	}

}