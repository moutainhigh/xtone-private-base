package com.thirdpay.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.common.util.ConfigManager;
import org.common.util.ConnectionService;
import org.common.util.GenerateIdService;
import org.common.util.ThreadPool;

import com.thirdpay.domain.ForwardsyncBean;
import com.thirdpay.domain.PayInfoBean;
import com.thirdpay.domain.PayOperateBean;
import com.thirdpay.utils.Canv;



public class testInsert {


	public static void main(String[] args) {
		
//		while(true){
//			ThreadPool.mThreadPool
//					.execute(new PayInfoBean(0, "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", 0));
//		}
//		ThreadPool.mThreadPool
//		.execute(new PayInfoBean(0, "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", "cbl", 0));

		
//		PreparedStatement ps = null;
//		Connection con = null;
//		con = ConnectionService.getInstance().getConnectionForLocal();
//		try {
//			ps = con.prepareStatement("SELECT * FROM tbl_thirdpay_cp_information WHERE appKey=" + "'" + "zgt" + "'");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ThreadPool.mThreadPool.execute(new ForwardsyncBean(0, "123456", "3000", "0", "url", "200", "zgt", "appkey"));
		
		
		Long key = GenerateIdService.getInstance()
				.generateNew(Integer.parseInt(ConfigManager.getConfigData("server.id").trim()), "clicks", 1);
		String keyy = key +"";
		System.out.println(keyy);
	}

}
