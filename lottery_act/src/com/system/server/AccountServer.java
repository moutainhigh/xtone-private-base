package com.system.server;

import java.util.List;

import com.system.dao.UserDao;
import com.system.dao.UserLotteryDao;
import com.system.model.UserLotteryModel;
import com.system.model.UserModel;
import com.system.remodel.ReAccountModel;
import com.system.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AccountServer
{
	public String handleAccount(String nameOrEmail)
	{
		ReAccountModel  model = new ReAccountModel();
		
		model.setSTATUS(0);
		
		model.setDESCRIPTION("不知你为什么失败");
		
		if(StringUtil.isNullOrEmpty(nameOrEmail))
		{
			model.setDESCRIPTION("用户名或邮件为空");
			return StringUtil.getJsonFormObject(model);
		}
		
		UserModel user = new UserDao().getUser(nameOrEmail);
		
		if(user==null)
		{
			model.setDESCRIPTION("用户不存在");
			return StringUtil.getJsonFormObject(model);
		}
		
		model.setSTATUS(1);
		model.setDESCRIPTION("");
		
		model.setNAME(user.getName());
		model.setEMAIL(user.getEmail());
		model.setUUID(user.getUuid());
		
		List<UserLotteryModel> list = new UserLotteryDao().loadUserLotteryByUserId(user.getId());
		
		JSONArray joArray = new JSONArray();
		
		int lastActivityId = 0;
		JSONObject jo = null;
		String lotteryList = "";
		
		for(UserLotteryModel ulModel : list)
		{
			if(lastActivityId==0)
			{
				lastActivityId = ulModel.getActivityId();
				jo = new JSONObject();
				lotteryList = ulModel.getExchangeCode() + "," + ulModel.getPwdCode() + "," + ulModel.getExpireTime() + "|";
				jo.accumulate("ACTIVITY_DATE", ulModel.getActStartDate() + "-" + ulModel.getActEndDate());
				jo.accumulate("ACTIVITY_TIME", ulModel.getAddTime());
			}
			else
			{
				if(ulModel.getActivityId()!=lastActivityId)
				{
					if(!StringUtil.isNullOrEmpty(lotteryList))
						lotteryList = lotteryList.substring(0,lotteryList.length()-1);
					
					jo.accumulate("LOTTERY_LIST", lotteryList);
					joArray.add(jo);
					
					lastActivityId = ulModel.getActivityId();
					jo = new JSONObject();
					lotteryList = ulModel.getExchangeCode() + "," + ulModel.getPwdCode() + "," + ulModel.getExpireTime() + "|";
					jo.accumulate("ACTIVITY_DATE", ulModel.getActStartDate() + "-" + ulModel.getActEndDate());
					jo.accumulate("ACTIVITY_TIME", ulModel.getAddTime());
				}
				else
				{
					lotteryList += ulModel.getExchangeCode() + "," + ulModel.getPwdCode() + "," + ulModel.getExpireTime() + "|";
				}
			}
		}
		
		if(!StringUtil.isNullOrEmpty(lotteryList))
			lotteryList = lotteryList.substring(0,lotteryList.length()-1);
		
		if(jo!=null)
		{
			jo.accumulate("LOTTERY_LIST", lotteryList);
			joArray.add(jo);
		}
		
		model.setDATA(joArray);
		
		return StringUtil.getJsonFormObject(model);
	}
}
