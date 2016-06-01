package com.bird.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.bird.bean.CherryBean;

/**
 * 
 * 
 * @author zgt
 *
 */
public class FileUtils {
	
	
	public static void main(String[] args) {
		
//		for (int i = 0; i < 5; i++) {
//			System.out.println(writeCherryNum("132"+i, "5"));
//		}
//		
		//System.out.println(updateCherryNum("14", ""));
		for (int i = 0; i < 4; i++) {
			saveCherryByUid("zgt"+i, 100*i);
		}
		
		
	}
	

	/**
	 * 
	 */
	private static final String FILE_PATH = FileUtils.class.getResource("").getPath()+"/"+"Cherry.properties";
	private static final String FILE_PATH_JSON = FileUtils.class.getResource("").getPath()+"/"+"Cherry.json";

//	public static String readCherryNum(String uid) {
//
//		try {
//			Properties pps = new Properties();
//			pps.load(new FileInputStream(FILE_PATH));
//			String value=pps.getProperty(uid);
//			return value;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//
//	}
//	
//	
//	public static String writeCherryNum(String uid,String cherryNum) {
//
//		try {
//			Properties pps = new Properties();
//			pps.setProperty(uid, cherryNum);
//			OutputStream fos = new FileOutputStream(FILE_PATH,true);          
//			pps.store(fos, "save-"+uid);
//			return "ok";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	public static String getCherryByUid(String uid){
		List<CherryBean> list =  readCherrys();
		for (CherryBean b : list) {
			if(uid.equals(b.getUid())){
				return b.getCherryNmun()+"";
			}
		}
		return "-1";
	}
	
	public static void saveCherryByUid(String uid,int cherryNmun){
		try {
			
			CherryBean bean =new CherryBean(uid, cherryNmun);
			List<CherryBean> list = readCherrys();
			FileWriter writer = new FileWriter(FILE_PATH_JSON);
			if(list!=null&&list.size()>0){
				
				for (int i = 0; i < list.size(); i++) {
					CherryBean b =list.get(i); 
					if(uid.equals(b.getUid())){
						list.remove(i);
					}
				}
				
				list.add(bean);
				String json = JSON.toJSONString(list);
				
				writer.write(json);
				writer.close();
				return ;
			}else {
				List<CherryBean> mlist = new ArrayList<>();
				mlist.add(bean);
				String json = JSON.toJSONString(mlist);
				writer.write(json);
				writer.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static List<CherryBean> readCherrys(){
		
		try {
			File file = new File(FILE_PATH_JSON);
			if(!file.exists()){
				file.createNewFile();
			}
			
			System.out.println(file.getAbsolutePath());
			
			FileInputStream in = new FileInputStream(FILE_PATH_JSON);
			StringBuilder builder =new StringBuilder();
			byte [] buf = new byte[1024];
			int len = 0;
			while((len=in.read(buf))!=-1){
				builder.append(new String(buf, 0, len));
			}
			in.close();
			if(!builder.toString().equals("")){
				List<CherryBean> list = JSON.parseArray(builder.toString(), CherryBean.class);
				return list;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static String updateCherryNum(String uid,String cherryNum) {

		
		
		try {
			File file = new File(FILE_PATH);
			if(!file.exists()){
				file.createNewFile();
			}
			
			if(uid==null||"".equals(uid)){
				return "-2";
			}
			
			Properties pps = new Properties();
			pps.load(new FileInputStream(FILE_PATH));
			System.out.println(file.getAbsolutePath());
			String value = pps.getProperty(uid);
			if(value!=null&&!"".equals(value)){
				if(cherryNum==null||"".equals(cherryNum)){
					return value;
				}
			}
			
			
			if(uid!=null&&cherryNum!=null&&!"".equals(cherryNum)){
				pps.setProperty(uid, cherryNum);
				OutputStream fos = new FileOutputStream(FILE_PATH);          
				pps.store(fos, "save-"+uid);
				
				return cherryNum;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-2";
	}
	
	
	
	

}
