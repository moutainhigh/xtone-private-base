package com.system.server;

import java.util.ArrayList;
import java.util.List;

import com.system.dao.ActivityDao;
import com.system.dao.GiftDao;
import com.system.dao.UserDao;
import com.system.dao.UserLotteryDao;
import com.system.model.ActivityModel;
import com.system.model.LotteryTicketModel;
import com.system.model.UserModel;
import com.system.remodel.ReGiftModel;
import com.system.util.StringUtil;

public class GiftServer
{
	public String handleGift(String nameOrEmail)
	{
		ReGiftModel model = new ReGiftModel();
		
		model.setSTATUS(0);
		model.setDESCRIPTION("莫名，我就失败了~");
		
		if(StringUtil.isNullOrEmpty(nameOrEmail))
		{
			model.setDESCRIPTION("用户或邮箱不存在");
			return StringUtil.getJsonFormObject(model);
		}
		
		UserModel user = new UserDao().getUser(nameOrEmail);
		
		if(user==null)
		{
			model.setDESCRIPTION("用户不存在");
			return StringUtil.getJsonFormObject(model);
		}
		
		ActivityModel activity = new ActivityDao().getActivityDao(user.getFlag(), StringUtil.getDefaultDateTime());
		
		if(activity==null)
		{
			model.setDESCRIPTION("活动已经下线");
			return StringUtil.getJsonFormObject(model);
		}
		
		GiftDao  dao = new GiftDao();
		
		if(user.getFlag()==0)
		{
			if(new UserLotteryDao().isUserAddActivity(user.getId(),activity.getId()))
			{
				model.setDESCRIPTION("用户已参与过此次活动");
				return StringUtil.getJsonFormObject(model);
			}
		}
		
		List<LotteryTicketModel> list = dao.getUnUsedTicked(activity.getTicketCount());
		
		List<Integer> idList = new ArrayList<Integer>();
		
		String ticketList = "";
		
		for(LotteryTicketModel ticket : list)
		{
			idList.add(ticket.getId());
			ticketList += ticket.getExchangeCode() + "," + ticket.getPwdCode() + "," + ticket.getExpireTime() + "|";
		}
		
		dao.updateTicketStatus(idList, 1);
		
		if(StringUtil.isNullOrEmpty(ticketList))
			ticketList = ticketList.substring(0, ticketList.length()-1);
		
		//保存数据到数据库
		new UserLotteryDao().addUserLotteryCode(user.getId(),activity.getId(),idList);
		
		model.setSTATUS(1);
		model.setDESCRIPTION("");
		model.setLOTTERY_LIST(ticketList);
		
		return StringUtil.getJsonFormObject(model);
		
	}
}
