package com.system.server;

import java.util.List;

import com.google.gson.JsonObject;
import com.system.dao.UserDao;
import com.system.dao.UserLotteryDao;
import com.system.model.UserLotteryModel;
import com.system.model.UserModel;
import com.system.remodel.ReAccountModel;
import com.system.util.StringUtil;

import net.sf.json.JSONArray;

public class AccountServer
{
	public String handleAccount(String nameOrEmail)
	{
		ReAccountModel  model = new ReAccountModel();
		
		UserModel user = new UserDao().getUser(nameOrEmail);
		
		if(user==null)
		{
			return StringUtil.getJsonFormObject(model);
		}
		
		model.setNAME(user.getName());
		model.setEMAIL(user.getEmail());
		model.setUUID(user.getUuid());
		
		List<UserLotteryModel> list = new UserLotteryDao().loadUserLotteryByUserId(user.getId());
		
		JSONArray joArray = new JSONArray();
		
		int lastActivityId = 0;
		JsonObject jo = null;
		String lotteryList = "";
		
		for(UserLotteryModel ulModel : list)
		{
			if(ulModel.getActivityId()!=lastActivityId)
			{
				if(!StringUtil.isNullOrEmpty(lotteryList))
					lotteryList = lotteryList.substring(0,lotteryList.length()-1);
				
				jo.addProperty("LOTTERY_LIST", lotteryList);
				joArray.add(jo);
				
				lastActivityId = ulModel.getActivityId();
				jo = new JsonObject();
				lotteryList = ulModel.getExchangeCode() + "," + ulModel.getPwdCode() + "," + ulModel.getExpireTime() + "|";
				jo.addProperty("ACTIVITY_DATE", ulModel.getActStartDate() + "-" + ulModel.getActEndDate());
				jo.addProperty("ACTIVITY_TIME", ulModel.getAddTime());
			}
			else
			{
				lotteryList += ulModel.getExchangeCode() + "," + ulModel.getPwdCode() + "," + ulModel.getExpireTime() + "|";
			}
		}
		
		if(!StringUtil.isNullOrEmpty(lotteryList))
			lotteryList = lotteryList.substring(0,lotteryList.length()-1);
		
		if(jo!=null)
		{
			jo.addProperty("LOTTERY_LIST", lotteryList);
			joArray.add(jo);
		}
		
		model.setDATA(joArray);
		
		return StringUtil.getJsonFormObject(model);
	}
}
