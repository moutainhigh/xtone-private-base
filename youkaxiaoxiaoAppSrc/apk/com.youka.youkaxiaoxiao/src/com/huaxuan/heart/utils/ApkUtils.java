package com.huaxuan.heart.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class ApkUtils {

	/**
	 * 
	 * @Title: startActivityForPackage
	 * @Description: TODO(通过)
	 * @param @param context
	 * @param @param packageName 包名
	 * @return void 返回类型
	 * @throws
	 */
	public static void startActivityForPackage(Context context,
			String packageName) {
		try {
			final PackageManager pm = context.getPackageManager();
			Intent intent = pm.getLaunchIntentForPackage(packageName);

			if (intent != null) {
				context.startActivity(intent);
			}
		} catch (Exception ex) {
		}
	}

	/**
	 * 判断是不是有这个应用
	 * 
	 * @param packageName
	 * @param context
	 * @return
	 */
	public static boolean checkBrowser(String packageName, Context context) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager()
					.getApplicationInfo(packageName,
							PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	public static void startWeb(Context ac, String url) {
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		ac.startActivity(intent);
	}

	/**
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = null;
		if (context != null) {
			try {
				cm = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (cm != null) {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String getFormatCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

}
