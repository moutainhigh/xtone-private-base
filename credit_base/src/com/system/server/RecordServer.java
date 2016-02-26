package com.system.server;

import com.system.dao.RecordDao;
import com.system.model.ApiOrderModel;

public class RecordServer
{
	public void recordVisitModel(ApiOrderModel model)
	{
		new RecordDao().addRecord(model);
	}
	
	public void udpateVisistModel(ApiOrderModel model)
	{
		new RecordDao().updateRecord(model);
	}
}
