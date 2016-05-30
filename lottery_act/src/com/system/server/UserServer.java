package com.system.server;

import java.util.UUID;

import com.system.dao.UserDao;
import com.system.model.UserModel;
import com.system.remodel.ReLoginModel;
import com.system.remodel.ReRegistModel;
import com.system.util.StringUtil;

public class UserServer 
{

	public String handleUserRegist(UserModel userModel)
	{
		ReRegistModel model = new ReRegistModel();
		model.setDESCRIPTION("ERROR");
		
		if(StringUtil.isNullOrEmpty(userModel.getEmail()))
		{
			model.setDESCRIPTION("邮箱为空！");
			return StringUtil.getJsonFormObject(model);
		}
		
		if(StringUtil.isNullOrEmpty(userModel.getName()))
		{
			model.setDESCRIPTION("用户名为空！");
			return StringUtil.getJsonFormObject(model);
		}
		
		if(StringUtil.isNullOrEmpty(userModel.getPwd()))
		{
			model.setDESCRIPTION("密码为空！");
			return StringUtil.getJsonFormObject(model);
		}
				
		UserDao dao = new UserDao();
		
		if(!dao.isEmailExist(userModel.getEmail()))
		{
			model.setDESCRIPTION("对不起，邮箱不在注册列表");
			return StringUtil.getJsonFormObject(model);
		}
		
		if(dao.isRegistFinish(userModel.getEmail()))
		{
			model.setDESCRIPTION("用户已经注册");
			return StringUtil.getJsonFormObject(model);
		}
		
		if(dao.isNameExist(userModel.getName()))
		{
			model.setDESCRIPTION("用户名已经被注册");
			return StringUtil.getJsonFormObject(model);
		}
		
		userModel.setUuid(UUID.randomUUID().toString());
		
		dao.updateRegistUser(userModel);
		
		model.setSTATUS(1);
		
		model.setUUID(userModel.getUuid());
		
		model.setDESCRIPTION("");
		
		return StringUtil.getJsonFormObject(model);
	}
	
	public String handleUserLogin(UserModel userModel)
	{
		ReLoginModel model = new ReLoginModel();
		
		model.setSTATUS(0);
		
		if(StringUtil.isNullOrEmpty(userModel.getName()))
		{
			model.setDESCRIPTION("用户名为空");
			return StringUtil.getJsonFormObject(model);
		}
		
		if(StringUtil.isNullOrEmpty(userModel.getPwd()))
		{
			model.setDESCRIPTION("用户密码为空");
			return StringUtil.getJsonFormObject(model);
		}
		
		UserDao dao = new UserDao();
		
		UserModel user = dao.getUser(userModel.getName());
		
		//数据库根本没有这个用户名或是EMAIL
		if(user==null)
		{
			model.setDESCRIPTION("登录失败");
			return StringUtil.getJsonFormObject(model);
		}
		
		if(StringUtil.isNullOrEmpty(user.getUuid()))
		{
			model.setDESCRIPTION("用户还没有注册");
			return StringUtil.getJsonFormObject(model);
		}
		
		user = dao.login(userModel);
		
		if(user==null)
		{
			model.setDESCRIPTION("登录失败");
			return StringUtil.getJsonFormObject(model);
		}
		
		dao.updateLoginTime(user.getId());
		
		model.setEMAIL(user.getEmail());
		model.setNAME(user.getName());
		model.setSTATUS(1);
		model.setUUID(user.getUuid());
		model.setDESCRIPTION("");
		
		return StringUtil.getJsonFormObject(model);
	}
	
}
