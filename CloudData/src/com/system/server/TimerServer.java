
package com.system.server;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class TimerServer
{
	Logger log = Logger.getLogger(TimerServer.class);

	public void startTimer()
	{
		startSynDataToCloud();
	}

	// 每隔一小时扫描daily_log.tbl_mr_xxxx，把更新的数据传到大数据中心
	private void startSynDataToCloud()
	{
		Calendar ca = Calendar.getInstance();

		int curMins = ca.get(Calendar.MINUTE);

		if (curMins >= 5)
		{
			ca.add(Calendar.HOUR_OF_DAY, 1);
		}

		ca.set(Calendar.MINUTE, 5);

		// ca.set(Calendar.HOUR_OF_DAY, 21);
		// ca.set(Calendar.MINUTE, 15);
		// ca.set(Calendar.SECOND, 0);

		long firstTime = ca.getTimeInMillis();

		// 一小时
		long periodTime = 3600000;

		// long periodTime = 120000;

		long delayMils = firstTime - System.currentTimeMillis();

		log.info("垫付数据同步准备开启:" + delayMils);
		log.info("周期时间是:" + periodTime);
		Timer timer = new Timer();
		timer.schedule(new AnalyDataTimerTask1(), delayMils, periodTime);
		log.info("已经启动了垫付数据同步定时任务...");
	}
	
	public static void main(String[] args)
	{
		new TimerServer().startTimer();
	}

	//每隔一小时扫描daily_log.tbl_mr_xxxx，把更新的数据传到大数据中心
	private class AnalyDataTimerTask1 extends TimerTask
	{
		@Override
		public void run()
		{
			new HtDFMrDataSynServer().startSynData();
		}
	}
}
