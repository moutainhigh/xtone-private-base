package com.system.server;

import com.system.dao.ActivityDao;
import com.system.dao.UserDao;
import com.system.dao.UserLotteryDao;
import com.system.model.ActivityModel;
import com.system.model.UserModel;
import com.system.remodel.ReActivityModel;
import com.system.util.StringUtil;

public class ActivityServer
{
	public String handleActivity(String email)
	{
		String curDate = StringUtil.getDefaultDate();
		
		ReActivityModel model = new ReActivityModel();
		
		model.setDESCRIPTION("就是没有活动呗");
		
		model.setSTATUS(0);
		
		ActivityDao dao = new ActivityDao();
		
		ActivityModel activity = null;
		
		UserDao userDao = new UserDao();
		
		UserModel user = null;
		
		//如果当前EMAIL为空或者是用户不存在的话的话，呆需要查找普通的开启活动就行了
		if(StringUtil.isNullOrEmpty(email))
		{
			activity = dao.getActivityDao(0, curDate);
			
			if(activity==null)
			{
				return StringUtil.getJsonFormObject(model);
			}
			else
			{
				model.setDESCRIPTION(activity.getDescription());
				model.setSTATUS(1);
				return StringUtil.getJsonFormObject(model);
			}
		}
		
		user = userDao.getUser(email);
		
		if(user==null)
		{
			return StringUtil.getJsonFormObject(model);
		}
		
		activity = dao.getActivityDao(user.getFlag(), curDate);
		
		if(activity!=null)
		{
			model.setDESCRIPTION(activity.getDescription());
			model.setSTATUS(1);
			
			//特殊用户只要检查到有活动，就放出去呗
			if(user.getFlag()==1)
				return StringUtil.getJsonFormObject(model);
			
			//普通用户，如果检查到已经参与过活动，就通知客户端这个用户已经参与过活动了
			if(user.getFlag()==0)
			{
				if(new UserLotteryDao().isUserAddActivity(user.getId(), activity.getId()))
				{
					model.setDESCRIPTION(activity.getDescription());
					model.setSTATUS(2);
				}
				else
					return StringUtil.getJsonFormObject(model);
			}
		}
		
		return StringUtil.getJsonFormObject(model);
	}
}
