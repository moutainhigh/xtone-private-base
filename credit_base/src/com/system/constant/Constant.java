package com.system.constant;

import com.system.util.ConfigManager;

public class Constant
{
	public static final int CP_CP_TRONE_NOT_EXIST = 1001;
	public static final int CP_CP_TRONE_STATUS_SUSPEND = 1002;
	public static final int CP_AREA_NOT_MATCH = 1006;
//	public static final int CP_GET_TRONE_SUC = 1007;
	public static final int CP_BASE_PARAMS_ERROR = 1008;
	public static final int CP_BASE_PARAMS_AREA_NOT_MATCH = 1009;
	public static final int CP_SP_TRONE_ERROR = 1010;//数据没取成功
	public static final int CP_SP_TRONE_SUC = 1011;//数据取得成功
	
	public static final String BASE_TRONE_URL = ConfigManager.getConfigData("BASE_TRONE_URL", "http://h1.n8wan.com:9191/API/jj%s.ashx");
}
