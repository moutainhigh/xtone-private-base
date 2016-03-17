package com.system.server;

import com.system.cache.CpDataCache;
import com.system.cache.LocateCache;
import com.system.cache.SpDataCache;
import com.system.constant.Constant;
import com.system.model.ApiOrderModel;
import com.system.model.BaseResponseModel;
import com.system.model.ProvinceModel;
import com.system.model.SpTroneApiModel;
import com.system.model.SpTroneModel;
import com.system.util.PhoneUtil;
import com.system.util.StringUtil;

import net.sf.json.JSONObject;

public class RequestServerV1
{
	public String handlCpQuery(ApiOrderModel model)
	{
		BaseResponseModel response = new BaseResponseModel();
		response.setStatus(Constant.CP_CP_TRONE_NOT_EXIST);
		model.setStatus(response.getStatus());
		
		int troneId = CpDataCache.getTroneIdByTroneOrderId(model.getTroneOrderId());
		
		if(troneId<0)
		{
			saveRequest(model);
			return StringUtil.getJsonFormObject(response);
		}
		
		model.setTroneId(troneId);
		
		SpTroneApiModel spTroneApiModel = SpDataCache.loadSpTroneApiByTroneId(troneId);
		
		SpTroneModel spTroneModel = SpDataCache.loadSpTroneByTroneId(troneId);
		
		if(spTroneApiModel==null)
		{
			saveRequest(model);
			return StringUtil.getJsonFormObject(response);
		}
		
		model.setApiOrderId(spTroneApiModel.getId());
		
		String[] apiFields = spTroneApiModel.getApiFiles().split(",");
		
		if(apiFields!=null && apiFields.length>=0)
		{
			if(!isFieldFill(apiFields,model))
			{
				response.setStatus(Constant.CP_BASE_PARAMS_ERROR);
				model.setStatus(response.getStatus());
				saveRequest(model);
				return StringUtil.getJsonFormObject(response);
			}
		}
		
		//地区匹配
		if(!isLocateMatch(spTroneApiModel.getLocateMatch(), model,spTroneModel))
		{
			response.setStatus(Constant.CP_BASE_PARAMS_AREA_NOT_MATCH);
			model.setStatus(response.getStatus());
			saveRequest(model);
			return StringUtil.getJsonFormObject(response);
		}
		
		//先把基本数据写入数据库
		saveRequest(model);
		
		response.setOrderNum(StringUtil.getMonthFormat() +  model.getId());
		
		//转到我们自己服务器去请求
		String result = NetServer.sendBaseApiOrder(model);
		
		JSONObject jo = null;
		JSONObject command = null;
		JSONObject apiJson = null;
		ApiOrderModel resultModel = null;
		
		try
		{
			jo = JSONObject.fromObject(result);
			command = jo.getJSONObject("Command");
			apiJson = jo.getJSONObject("Request");
			resultModel = (ApiOrderModel)JSONObject.toBean(apiJson, ApiOrderModel.class);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(resultModel!=null)
		{
			model = resultModel;
			response.setResultJson(command);
		}
		else
		{
			response.setStatus(Constant.CP_SP_TRONE_ERROR);
			model.setStatus(response.getStatus());
			updateRequest(model);
			return StringUtil.getJsonFormObject(response);
		}
		
		if(model.getStatus()==Constant.CP_SP_TRONE_ERROR || command == null)
		{
			response.setStatus(Constant.CP_SP_TRONE_ERROR);
			model.setStatus(response.getStatus());
			updateRequest(model);
			return StringUtil.getJsonFormObject(response);
		}
		
		//把从我们服务器得回的结果加入到表里
		try
		{
			model.setPort(command.getString("port"));
			model.setMsg(command.getString("msg"));
		}
		catch(Exception ex){}
		
		//response.setStatus(Constant.CP_SP_TRONE_SUC);
		//model.setStatus(response.getStatus());
		
		response.setStatus(model.getStatus());
		
		response.setResultJson(command);
		
		updateRequest(model);
		
		return StringUtil.getJsonFormObject(response);
	}
	
	private void saveRequest(ApiOrderModel model)
	{
		new RecordServer().recordVisitModel(model);
	}
	
	private void updateRequest(ApiOrderModel model)
	{
		new RecordServer().udpateVisistModel(model);
	}
	
	protected boolean isLocateMatch(int locateMatchType,ApiOrderModel model,SpTroneModel spTroneModel)
	{
		//在这里匹配地区
		//地区匹配,默认0不匹配，1手机号匹配，2IP地区匹配，3手机和IP地区都必须匹配
		switch(locateMatchType)
		{
			case 0:
				break;
				
			case 1:
				return isPhoneLocateMatch(model,spTroneModel);
				
			case 2:
				break;
				
			case 3:
				break;
				
			default:
				break;
		}
		return true;
	}
	
	/**
	 * 这个函数只用于处理这种情况：只用于识别得了号码的用户，并且是不符合地区的才返回FALSE，其它情况一律返回TRUE
	 * @param model
	 * @param spTroneModel
	 * @return
	 */
	protected boolean isPhoneLocateMatch(ApiOrderModel model,SpTroneModel spTroneModel)
	{
		if(StringUtil.isNullOrEmpty(model.getImsi()) && StringUtil.isNullOrEmpty(model.getMobile()))
			return true;
		
		String phonePrefix = null;
		
		if(!StringUtil.isNullOrEmpty(model.getMobile()) && model.getMobile().length()>=7)
		{
			phonePrefix = model.getMobile().substring(0, 7);
		}
		else
		{
			phonePrefix = PhoneUtil.getFakePhonePreByImsi(model.getImsi());
		}
		
		if(StringUtil.isNullOrEmpty(phonePrefix))
			return true;
		
		int cityId = LocateCache.getCityIdByPhone(phonePrefix);
		
		if(cityId<0)
			return true;
		
		ProvinceModel provinceModel = LocateCache.getProvinceByCityId(cityId);
		
		if(provinceModel==null)
			return true;
		
		if(StringUtil.isNullOrEmpty(spTroneModel.getProvinces()))
		{
			return true;
		}
		
		String[] strProvinces = spTroneModel.getProvinces().split(",");
		
		for(String province : strProvinces)
		{
			if(Integer.valueOf(province)==provinceModel.getId())
				return true;
		}
		
		return false;
	}
	
	protected boolean isFieldFill(String[] apiFields,ApiOrderModel model)
	{
		int fieldId = 0;
		
		for(String id : apiFields)
		{
			fieldId = StringUtil.getInteger(id, -1);
			
			if(fieldId<0)
				return false;
			
			//API必须参数,0=IMEI,1=IMSI,2=PHONE,3=IP,4=PACKAGENAME,5=ANDROIDVERSION,6=NETTYPE,7=CLIENTIP,8=LAC,9=CID
			
			if(fieldId==0)
			{
				if(StringUtil.isNullOrEmpty(model.getImei()))
				{
					return false;
				}
			}
			else if(fieldId==1)
			{
				if(StringUtil.isNullOrEmpty(model.getImsi()))
				{
					return false;
				}
			}
			else if(fieldId==2)
			{
				if(StringUtil.isNullOrEmpty(model.getMobile()))
				{
					return false;
				}
			}
			else if(fieldId==3)
			{
				if(StringUtil.isNullOrEmpty(model.getIp()))
				{
					return false;
				}
			}
			else if(fieldId==4)
			{
				if(StringUtil.isNullOrEmpty(model.getPackageName()))
				{
					return false;
				}
			}
			else if(fieldId==5)
			{
				if(StringUtil.isNullOrEmpty(model.getSdkVersion()))
				{
					return false;
				}
			}
			else if(fieldId==6)
			{
				if(StringUtil.isNullOrEmpty(model.getNetType()))
				{
					return false;
				}
			}
			else if(fieldId==7)
			{
				if(StringUtil.isNullOrEmpty(model.getClientIp()))
				{
					return false;
				}
			}
			else if(fieldId==8)
			{
				if(model.getLac()<=0)
				{
					return false;
				}
			}
			else if(fieldId==9)
			{
				if(model.getCid()<=0)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	
}
