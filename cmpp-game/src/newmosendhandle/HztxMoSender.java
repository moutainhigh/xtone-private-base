package newmosendhandle;
/*
//华中天讯
*/
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class HztxMoSender extends MoSender{
	String company = "";
	String compUrl = "";
	String sendStat = "";
	MoCounter moCounter = null;
	static int sendSecond = 0;
	private ArrayList mosList;
	public HztxMoSender(String company){
		this.company = company;
		
	}
	public void getCompanyUrl(){
		CompanyUrl companyUrl = new CompanyUrl("config.ini",this.company);
		companyUrl.loadParam();
		this.compUrl = companyUrl.getCompanyUrl();
		System.out.println("hztx url is:" + this.compUrl);
	}
	//public void sendMo(){
	public void run(){
		while(true){
				try{
					moCounter = new MoCounter();
					getCompanyUrl();
					getMosList();
				}catch(Exception e){
					//moCounter.mydb.dbclose();	
					//System.out.println("sjnz exception");
					//e.printStackTrace();
					try{
						System.out.println("hztx exception");
						e.printStackTrace();
						moCounter.mydb.dbclose();	
					}catch(Exception e1){
							e1.printStackTrace();
					}finally{
						moCounter.mydb.dbclose();	
					}
				}
				System.out.println("" + this.sendSecond);
				///////////////////////
				if(0 < this.mosList.size() && this.mosList.size()< 6 && this.sendSecond < 3){
					System.out.println(this.company + "上行数量不足，等待第"+ this.sendSecond);
					this.sendSecond++;
				}
				else if(this.mosList.size() > 0){
					System.out.println("the second is:" + this.sendSecond);
					String mosLog = "";
					System.out.println(this.company + "发送时间到，开始发送。。。");
					System.out.println("the url is:" + this.compUrl);
					////////////
					String moXmlStr = MosXmlOrg();
					System.out.println(moXmlStr);
					try{
						 SendMsgHttp proxy = new SendMsgHttp(compUrl);
						 proxy.setBody(moXmlStr.getBytes("UTF-8"));
						 String status = "-1";
						 
						status = proxy.exec();
						System.out.println("the state is:" + status);
						 if (status == null || !status.equals("0")) {
			               status = "-2";//proxy.exec();
						 }
						 System.out.println();
						 this.sendStat = status;
						 moCounter.setMosList(this.mosList);
						 moCounter.updateSendStat(this.sendStat);
						 this.sendSecond = 0;
						 mosLog += this.compUrl + "\n";
						 mosLog += "mosLog:" + moXmlStr +"\n";
						 mosLog += "sendStat:" + this.sendStat;
						 this.writeLog(mosLog);//写入日志
					}catch(Exception e){
						this.sendStat = "-1";
						moCounter.updateSendStat(this.sendStat);
						e.printStackTrace();
						this.writeLog(e.toString());
					}
					finally{
						moCounter.mydb.dbclose();
					}
					//////////////
				}
				else{
					System.out.println(this.company + "没有数据。。。");
					this.sendSecond = 0;
				}
				///////////////////////
				moCounter.mydb.dbclose();
				try{
					System.out.println("Hztx start to rest......");
					Thread.currentThread().sleep(1000 * 3);	
				}catch(Exception e){
						e.printStackTrace();
				}
		}
		//////////////////////////
		
		
	}
	public void getMosList(){
		
		moCounter.setCompany(this.company);
		moCounter.checkMo();
		//moNum = moCounter.getMoNum();
		this.setMosList(moCounter.getMosList());
		
	}
	private void writeLog(String logStr){
		moCounter.mydb.dbclose();
		Logger myLogger = Logger.getLogger("MsgSendLogger");
		Logger mySonLogger = Logger.getLogger("myLogger.mySonLogger"); 
		PropertyConfigurator.configure("hztxlog4j.properties"); 
		//logStr = this.compUrl + "\n" + logStr;
		myLogger.info(logStr);
	}
	public String MosXmlOrg(){//组织整个上行信息的xml包
		String mosXml = "";
		String moXmlHead = "<?xml version=\"1.0\"  encoding=\"UTF-8\"?>";
		   moXmlHead += "<pwd_message_list version=\"3.0\">";
		   moXmlHead += "<message_type>SMS_MO</message_type>";
		   moXmlHead += "<data>";

		   String moXmlHeadEnd = "</data></pwd_message_list>";
		   for(int i = 0;i < this.mosList.size();i++){
			   mosXml += moXml((Mo)this.mosList.get(i));
		   }
		   mosXml = moXmlHead + mosXml + moXmlHeadEnd;

		   //System.out.println(mosXml);
		return mosXml;
	}
	private String moXml(Mo mo){//组织单个信息xml包
		StringBuffer moBuffer = new StringBuffer();
		String moMsg = moFliter(mo.getMoMsg());
		moBuffer.append("<message>");
		   moBuffer.append("<msgid>" + mo.getMsgId() + "</msgid>");
		   moBuffer.append("<mobile>"+ mo.getMobile() + "</mobile>");
		   moBuffer.append("<mobiletype>" + mo.getMobileType() +"</mobiletype>");
		   moBuffer.append("<userType>" + mo.getUserType() +"</userType>");
		   moBuffer.append("<gwid>hnyd</gwid>");
		   moBuffer.append("<spcode>" + mo.getSpCode() +"</spcode>");
		   moBuffer.append("<serviceType>"+ mo.getServiceType() +"</serviceType>");
		   moBuffer.append("<linkid>"+ mo.getLinkId() +"</linkid>");
		   moBuffer.append("<momsg><![CDATA[" + moMsg +"]]></momsg>");
		   moBuffer.append("<feeflag>" + mo.getFeeFlag() +"</feeflag>");
		   moBuffer.append("<cpproductID>" + mo.getCpProductId() +"</cpproductID>");
		   moBuffer.append("</message>");
		  return moBuffer.toString();
	}
	/*
	*用于替代掉不需要的字符
	*/
	private String moFliter(String moMsg){
		// = "";
		String tempMo = moMsg;
		tempMo = tempMo.replace("<", "");
		tempMo = tempMo.replace(">", "");
		tempMo = tempMo.replace("/", "");
		return tempMo;
	}
	public void setMosList(ArrayList mosList){
		this.mosList = mosList;
	}
	public String getState(){
		return this.sendStat;
	}
	public static void main(String[] args){
		//while(true){
			SjnzMoSender sjnz = new SjnzMoSender("sjnz");
			new Thread(sjnz).start();	
			//try{
			//	Thread.currentThread().sleep(2000);
			//}catch(Exception e){
			//		e.printStackTrace();	
			//}
		//}
		
	}
}
