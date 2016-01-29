package com.system.server;

import java.util.List;

import com.system.dao.SpTroneApiDao;
import com.system.model.SpTroneApiModel;

public class SpTroneApiServer
{
	public List<SpTroneApiModel> loadSpTroneApi()
	{
		return new SpTroneApiDao().loadSpTroneApi();
	}
}
