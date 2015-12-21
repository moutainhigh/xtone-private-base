package com.system.server.xy;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.system.dao.TestUserDao;
import com.system.dao.xy.UserDao;
import com.system.model.xy.XyUserModel;
import com.system.util.StringUtil;

public class UserServer
{
	Logger log = Logger.getLogger(UserServer.class);
	
	public Map<String, Object> loadUserData(String startDate,String endDate,String appKey,String channelKey,int pageIndex)
	{
		return new UserDao().loadUserData(startDate, endDate, appKey, channelKey,pageIndex);
	}
	
	public List<XyUserModel> loadUserTodayData(String appKey,String channelKey)
	{
		String tableName = StringUtil.getMonthFormat();
		String startDate = StringUtil.getDefaultDate();
		return new UserDao().loadUserTodayData(tableName, startDate, appKey, channelKey);
	}
	
	public Map<String, Object> loadQdUserData(String startDate,String endDate,int userId,int pageIndex)
	{
		return new UserDao().loadQdUserData(startDate, endDate, userId,pageIndex);
	}
	
	public boolean updateQdData(int id,int showDataRows)
	{
		return new UserDao().updateQdData(id,showDataRows);
	}
	/**
	 * 该方法用于更新amount
	 * test.userDAO
	 */
	public boolean updateQdData(int id,double showDataFee)
	{
		return new TestUserDao().updateQdData(id, showDataFee);
	}
	
	public boolean analyUserToSummer(String startDate,String endDate)
	{
		log.info("start analy user to summer ["+ startDate +"," + endDate + "]");
		UserDao dao = new UserDao();
		dao.deleteFromSummer(startDate, endDate);
		return dao.analyUserToSummer(StringUtil.getMonthFormat(startDate), startDate, endDate);
	}
	
	
	public void startAnalyUser()
	{
		String startDate = StringUtil.getPreDayOfMonth();
		analyUserToSummer(startDate, startDate);
		log.info("finish analy user to summer");
	}
	
	public void updateQdUserData()
	{
		String startDate = StringUtil.getPreDayOfMonth();
		updateQdUserSummer(startDate,startDate);
		log.info("finish update Qd user data");
	}
	
	public void updateQdUserSummer(String startDate,String endDate)
	{
		log.info("start update qd user ["+ startDate +"," + endDate + "]");
		UserDao dao = new UserDao();
		dao.updateQdShowData(startDate, endDate);
		dao.updateQdShowDataStatus(startDate, endDate);
	}
	
	public void analyQdShowDataWithHour()
	{
		Calendar ca = Calendar.getInstance();
		
		ca.add(Calendar.HOUR, -1);
		
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		
		String startDate = StringUtil.getDateFormat(ca.getTime());
		String tableName = StringUtil.getMonthFormat(ca.getTime());
		
		UserDao dao = new UserDao();
		
		List<XyUserModel> list = dao.queryQdShowDataWithHour(tableName, startDate,hour);
		
		dao.analyUserList(list,startDate);
	}
}
