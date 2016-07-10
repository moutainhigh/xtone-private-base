package org.x;
/**
*Copyright 2003 Xiamen Xiangtone Co. Ltd.
*All right reserved.
*/

import java.io.*;
import java.net.*;
import com.xiangtone.sms.api.*;
import com.xiangtone.util.*;

public class VCPConnectionHandler implements Runnable 
{
	/**
	*
	*
	*/
    protected Socket socketToHandle;
    protected conn_desc con ;
    protected message mess;
    protected sm_submit_result sm;
    protected SMSFactory myFactory;
    //protected SMSMT mt;
    protected SMSCost cost;
    //protected SMSMonth month;
    //protected SMSCard card;
    protected SMSMO mo;
    /**
    *
    *
    */
    public VCPConnectionHandler(Socket aSocketToHandle) 
    {
        socketToHandle = aSocketToHandle;
        con = new conn_desc(socketToHandle);
        
        //mt = new SMSMT();
        myFactory = new SMSFactory();
        sm = new sm_submit_result();
        mess = new message();
        cost = new SMSCost();
       // month =new SMSMonth();
       // card  = new SMSCard();
        mo = new SMSMO();
        //strart();
    }
    /**
    *
    *
    */
    public void run()
    {
        try 
        {
        	
        	mess.readPa(con); //��ȡ�ύ��Ϣ
        	String stat = sm.get_stat();
        	System.out.println("stat....:"+stat);
        	//
        	//stat = "00";
        	if(stat.equals("00"))
        	{
        	
        		////////////////////////////////////////
        		System.out.println(":::::::::::::");
        		System.out.println(":::::::::::::");
        		System.out.println(":::::::::::::");
        		System.out.println("new message");
        		System.out.println(":::::::::::::");
        		System.out.println(":::::::::::::");
        		System.out.println(":::::::::::::");
        		///////////////////////////////////////
        		String _corpid = "00"; 
        		int    _vcpid   = 1;
        		       _vcpid   = Integer.parseInt(sm.get_vcp_id()); //get_vcpid();
        		String _spcode  = sm.get_server_code();//�ط���û�� //sm.get_server_code();
        		       		
        		int _mediatype  = Integer.parseInt(sm.get_media_type());
        		String _destcpn =  sm.get_dest_cpn();
        		String _feecpn  =  sm.get_fee_cpn();
        		String _infofee =  sm.get_fee_code();
        		String _feetype =  sm.get_fee_type();
        		String s_feetype = _feetype;
        		String _serverid = sm.get_server_type();
        		String _content =   new String(sm.get_content());
                String _ismgid  = sm.get_prov_code();
                ///////////add at 061123
                String _linkid = sm.get_linkid();
                int _feecpntype = sm.get_feecpn_type();
                String msgId = sm.get_msgId();
                System.out.println("linkid is:" + _linkid);
                System.out.println("feecpntype:" + _feecpntype);
                System.out.println("msgid is:" + msgId);
                System.out.println("destcpn:" + _destcpn);
                System.out.println("_feecpn:" + _feecpn);
                
                ////////////////////////
                if(_ismgid ==null || _ismgid.equals(""))
                	_ismgid = mo.getImsgID(_feecpn);
                if(msgId == null){
                	msgId = "";	
                }
                String _servercode = "";      
                String _servername = "";
                //add 
             //GameOperate go = new GameOperate(_serverid,_infofee,_feetype);  
             //
                
                ///////////////////////////////////////////
        	
                /*
                
                  //��ֵ����Ǯ ����
                /*
                 System.out.println("�ӿ��п�Ǯ��....");	
                 System.out.println("_serverid...:..."+_serverid);
                 System.out.println("_spcode::......."+_spcode);
                 System.out.println("_infofee::......"+_infofee);
                 System.out.println("_feetype::......"+_feetype);
                 System.out.println("_servername::..."+_servername);
                 System.out.println("_servercode::..."+_servercode);
                 System.out.println("_mediatype::...."+_mediatype);
                 System.out.println("_feecpn::......."+_feecpn);
                 System.out.println("_destcpn::......"+_destcpn);
                 //���⴦���ط����
                */
        	
        	/*
                if(_spcode!=null && _spcode.length()>=7)
                        _corpid = _spcode.substring(5,7);
                 
                 System.out.println("_spcode:"+_spcode);
                 if(_spcode==null ||_spcode.equals(""))
                {
                	_spcode = SMSIsmgInfo.getIsmg_spCode(_ismgid);//�õ��ط����
                	_corpid = mo.getMO_corpID(_feecpn);//ͨ���ֻ�����ȡ��corpid
                	String gameid = mo.getGameID(_vcpid,_servername);
                	_spcode+=_corpid;
                	_spcode+=gameid;
                	System.out.println("�ط���:"+_spcode);
                 }
      */
                 ////////////////
                 
                 
                 
                 //���뷢�Ͷ�����
                 //
                 /*
        		String _corpid = "00";
        		int    _vcpid   = 1;
        		String _ismgid = "01";
        		String _spcode = "05511";
        		String _destcpn = "055112";
        		String _feecpn = "12345678123";
        		String _serverid = "-XLJT";
        		String _servername = "JT";
        		String _infofee = "50";
        		String _servercode = "-XLJT";
        		String _feetype = "03";
        		String _content = "fdsaga";
        		int _mediatype = 15;
        		String _linkid = "12345678900987654321";
        		int  _feecpntype = 1;
        		*/
        	
        		//
        		SMSMT mt = new SMSMT();
        		cost.lookupInfofeeByServerID_IOD(_serverid);
        		_infofee = cost.getCost_infoFee();
        		_servercode = cost.getCost_serverCode_IOD();
        		_feetype = cost.getCost_feeType();
        		_servername = cost.getCost_serverName();
        		
                 mt.setMT_vcpID(_vcpid);
                 mt.setMT_ismgID(_ismgid);
                 mt.setMT_spCode(_spcode); 
                 mt.setMT_corpID(_corpid);
                 mt.setMT_destCpn(_destcpn);
                 mt.setMT_feeCpn(_feecpn); 
                 mt.setMT_serverID(_serverid);
                 mt.setMT_serverName(_servername);
    	         mt.setMT_infoFee(_infofee);
		         mt.setMT_feeCode(_servercode);
		         mt.setMT_feeType(_feetype);
		         mt.setMT_sendContent(_content);
		         mt.setMT_mediaType(_mediatype);
		         mt.setMT_sendTime(FormatSysTime.getCurrentTimeA());   
		         mt.setMT_linkID(_linkid);
		         mt.setMT_cpnType(_feecpntype);
		         mt.setMT_submitMsgID(msgId);
		         
		         /////////////////
		         /*
		         System.out.println("////////////////");
		            System.out.println("////////////////");
		               System.out.println("////////////////");
		                  System.out.println("////////////////");
		                  System.out.println("_serverid:" + _serverid);
		         System.out.println("cpntype is:" + mt.cpnType);
		         System.out.println("linkid is:" + mt.linkID);
		         System.out.println("content is:" + mt.sendContent);
		         System.out.println("msgId is:" + mt.submitMsgID);
		            System.out.println("////////////////");
		               System.out.println("////////////////");
		                  System.out.println("////////////////");
		                     System.out.println("////////////////");
		         */
		         //////////////////
		         if(msgId.startsWith("auto")){
        					 mt.setMT_feeType("01");
        					 mt.setMT_serverID("1000");
        					 mt.setMT_infoFee("0");
        					 mt.setMT_serverName("AutoHELP");
        					 mt.setMT_feeCode("HELP");
        				}
             // mt.insertMTLog(); //������־
		         ///////////////////
                 CMPPSend mysms = myFactory.createSMS(_ismgid,mt);
                 switch(_mediatype)
                 {
                 	case 1:
                 			mysms.sendTextSMS(); //�����ı�
                 			break;
                 	case 2:
                 			mysms.sendBinaryPicSMS(); //����ͼƬ
                 			break;
                 	case 3:
                 			mysms.sendBinaryRingSMS();
                 			break;
                 	
                 	default:
                 			mysms.sendTextSMS(); //�����ı�
                 			break;
                }
               //�ж��û��Ƿ���²����д���
               System.out.println("*************************mt.feeType:"+mt.feeType);
               System.out.println("*************************s_feetype:"+_feetype);
               System.out.println("*************************_feetype:"+_feetype);
               ////////////////////////
               /*
               if(_feetype.equals("03")&& !s_feetype.equals("-1")) //��ʼ���ư���
               {
               	
               		 String strTime = FormatSysTime.getCurrentTimeA();
               		 month.setMonth_vcpID(_vcpid);
               		 month.setMonth_cpn(_feecpn);
               		 month.setMonth_ismgID(_ismgid);
               		 month.setMonth_corpID(_corpid);
               		 month.setMonth_spCode(_spcode);
               		 month.setMonth_serverID(_serverid);
               		 month.setMonth_serverName(_servername);
               		 month.setMonth_feeCode(_servercode);
               		 month.setMonth_infoFee(Integer.parseInt(_infofee));
               		 month.setMonth_beginTime(strTime);
               		 month.setMonth_backTime(strTime);
               		 month.setMonth_lastSendTime(strTime);
               		 month.setMonth_sendAmounts(1);
               		 month.setMonth_sendFlag(1);
               		 
               		 boolean newFalg =month.insertOneMonth();//�������
               		 
               		 if(newFalg)//��������û����¾Ϳ�ʼ���Ͱ��·���
               		 { 
               		 	
               		 	//���������Ϣ����
               		 	if(_ismgid.equals("01"))
               		 	{
               		 		System.out.println("���������Ϣ����");
               		 		SMSMonthQX qx = new SMSMonthQX();
               		 		qx.setQX_cpn(_feecpn);
               		 		qx.setQX_serverID(_serverid);
               		 		qx.setQX_actionCode(_serverid);
               		 		qx.setQX_qxTime(strTime);
               		 		qx.setQX_ismgID(_ismgid);
               		 		qx.insertOneQX();
               		 	}
               		 	
               		 	//
               		 	 mt.setMT_sendContent("���»���");
               		 	 int card_flag = 1;//
               		 	 //��������ж��Ƹ�ҵ��
               		 	 if(!month.alreadySendThisMonth(_feecpn,_serverid,FormatSysTime.getCurrentTime("yyyy-MM")))
               		 	 {
               		 	 	successFlag = card.getCardDetail(_feecpn,_serverid,Integer.parseInt(_infofee));
                        	if(!successFlag) //����ۿ�ɹ��ͷ��Ͱ��»���
                         	{
                         		card_flag = 0;//�ǿ���
               		 			if(mt.mediaType==1)
               		 			{
               		 				
               		 				System.out.println("���Ͱ��»���.....");
               		 				{
               		 					mysms.sendTextSMCSMS();//���Ϸ��Ͱ��»���
               		 				}
               		 			}
               		 			else
               		 			{
               		 				//ͼƬ�����İ��´���
               		 			}
               		 			mt.insertMCLog(card_flag);//���»���
               		 		}	
               		 	}// end if
               		 	 month.insertMonthSendlog(card_flag);//������־
               		}
               		
               
               }
             */
               
                 //end if 
               /*
               else if(s_feetype.equals("-1")) //�˶�����
               {
               		//ȡ������
               		 month.setMonth_serverID(_serverid);
               		 month.setMonth_cpn(_feecpn);
               		 month.deleteMonthBycpnServerid();
               }
                 */	
            }//end if
           
            
         }//end try 
        catch (Exception e) 
        {
            System.out.println("Error handling a client: " + e);
            e.printStackTrace();
        }
          
    }
 /*   
    public static void main(String[] args){
    	try{
			SMSIsmgInfo info = new SMSIsmgInfo("config.ini");
			info.loadParam();
			info.printParam();
			SMSActiveTest sdc = new SMSActiveTest();
			new Thread(sdc).start();
			SMSRecive sr = new SMSRecive();
			new Thread(sr).start();
		}catch(Exception e){
			System.out.println(">>>>>>ƽ̨����");
	    	e.printStackTrace();
		}
    	VCPServer vcpserver = new VCPServer(8001);
    	vcpserver.start();
    	//VCPConnectionHandler vcphanlder = new VCPConnectionHandler();
    }
 */   
}
